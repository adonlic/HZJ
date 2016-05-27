package hr.fer.ppj.projekt.hzj.camera.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.WindowManager;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import hr.fer.ppj.projekt.hzj.R;

public class CameraActivity extends AppCompatActivity
        implements CameraBridgeViewBase.CvCameraViewListener2 {
    // Used for logging success or failure messages
    private static final String TAG = "OCVSample::Activity";

    // Loads camera view of OpenCV for us to use. This lets us see using OpenCV
    private CameraBridgeViewBase mOpenCvCameraView;


    // These variables are used (at the moment) to fix camera orientation from 270degree to 0degree
    Mat mRgba;
    Mat mRgbaF;
    Mat mRgbaT;
    Mat gray;
    Mat grayT;
    Mat grayF;

    Mat mRgb;
    Mat hornTempl;
    Mat zeroTempl;
    Mat difference;
    private int framesTaken;
    Mat background;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

    public CameraActivity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {


        /*
        //get background from Calibration
        background = new Mat();
        double[][][] temp = (double[][][]) getIntent().getExtras().getSerializable("background");

        for(int i=0; i<temp.length; i++){
            for(int j=0; j<temp[i].length; j++){
                background.put(i, j, temp[i][j]);
            }
        }
*/
        Log.i(TAG, "called onCreate");
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_camera);

        mOpenCvCameraView = (JavaCameraView) findViewById(R.id.camera_viewer);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);

        Log.i(TAG, "Trying to load OpenCV library");
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }


        /*
        try {
            hornTempl = Utils.loadResource(getApplicationContext(), R.drawable.horns, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
            zeroTempl = Utils.loadResource(getApplicationContext(), R.drawable.zero, Imgcodecs.CV_LOAD_IMAGE_UNCHANGED);
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Image not loaded!", Toast.LENGTH_SHORT).show();
        }
        */

        /*
        // this makes negatives
        Mat temp = new Mat(hornTempl.rows(), hornTempl.cols(), hornTempl.type());
        temp.setTo(new Scalar(255,255,255));
        Core.subtract(temp, hornTempl,hornTempl);

        temp = new Mat(zeroTempl.rows(), zeroTempl.cols(), zeroTempl.type());
        temp.setTo(new Scalar(255,255,255));
        Core.subtract(temp, zeroTempl,zeroTempl);
*/
        difference = new Mat();
        framesTaken = 0;



        mOpenCvCameraView.setCvCameraViewListener(this);
        //Toast.makeText(getApplicationContext(),"successfully loaded template(s)",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPause()
    {
        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }
    public void onDestroy() {
        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {

        mRgba = new Mat(height, width, CvType.CV_8UC4);

    }

    public void onCameraViewStopped() {
        mRgba.release();
        //gray.release();
    }

    public Core.MinMaxLocResult compare(Mat img, Mat templ){
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        Imgproc.matchTemplate(img, templ, result, Imgproc.TM_CCOEFF);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        return Core.minMaxLoc(result);

    }


    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {

        mRgba = inputFrame.rgba();

        mRgb = new Mat();
        Imgproc.cvtColor(mRgba, mRgb, Imgproc.COLOR_RGBA2RGB);
        framesTaken++;
        //20 = number of frames it ignores before taking background
        // ORIGINALLY: had a calibration button where it took 255 frames and made an average frame as background
        // (because hands are shaky)
        if(framesTaken < 20){
            Imgproc.putText(mRgba,"calibrating...", new Point(300, 300), Core.FONT_HERSHEY_COMPLEX,1.0,new Scalar(255));
            return mRgba;
        }
        if(framesTaken==20) {
            background = mRgb.clone();
            return mRgba;
        }


        Core.absdiff(background,mRgb,difference);
        Imgproc.threshold(difference,difference,15,255,Imgproc.THRESH_BINARY);
        //Mat temp = new Mat(difference.rows(), difference.cols(), difference.type());
        //temp.setTo(new Scalar(255,255,255));
        //Core.subtract(temp, difference,difference);

        Mat diffT = difference.t();
        Core.flip(difference.t(), diffT, 1);
        Imgproc.resize(diffT, diffT, difference.size());

        return diffT;

        /*
        Core.MinMaxLocResult hornResult = compare(difference, hornTempl);
        Core.MinMaxLocResult zeroResult = compare(difference, zeroTempl);

        Point matchLoc;
        String found;

        //if(hornResult.minVal > -0.5 || zeroResult.minVal > -0.5) return mRgba;

        if(hornResult.minVal < zeroResult.minVal){
            matchLoc = hornResult.minLoc;
            found = "horn";
        }
        else{
            matchLoc = zeroResult.minLoc;
            found = "zero";
        }

        Imgproc.rectangle(difference, matchLoc, new Point(matchLoc.x + hornTempl.cols(),
                matchLoc.y + hornTempl.rows()), new Scalar(0, 255, 0));

        Imgproc.putText(difference,found, matchLoc,Core.FONT_HERSHEY_COMPLEX,1.0,new Scalar(255));

        return difference;
*/

        /*
        //GRAYSCALE

        gray = inputFrame.gray();
        mRgba = inputFrame.rgba();
        mRgb = new Mat();

        Core.flip(grayF, gray, 1);
        Core.flip(mRgbaF, mRgba, 1);

        Imgproc.cvtColor(mRgbaF, mRgb, Imgproc.COLOR_RGBA2RGB);

        Core.MinMaxLocResult hornResult = compare(mRgb, hornTempl);
        Core.MinMaxLocResult zeroResult = compare(mRgb, zeroTempl);

        Point matchLoc;
        String found;

        //if(hornResult.minVal > -0.5 || zeroResult.minVal > -0.5) return mRgba;

        if(hornResult.minVal < zeroResult.minVal){
            matchLoc = hornResult.minLoc;
            found = "horn";
        }
        else{
            matchLoc = zeroResult.minLoc;
            found = "zero";
        }

        Imgproc.rectangle(mRgb, matchLoc, new Point(matchLoc.x + hornTempl.cols(),
                matchLoc.y + hornTempl.rows()), new Scalar(0, 255, 0));

        Imgproc.putText(mRgb,found, matchLoc,Core.FONT_HERSHEY_COMPLEX,1.0,new Scalar(255));

        return mRgb;



        */
/*
//COLOUR
        mRgba = inputFrame.rgba();
        // Rotate mRgba 90 degrees
        Core.transpose(mRgba, mRgbaT);
        Imgproc.resize(mRgbaT, mRgbaF, mRgbaF.size(), 0,0, 0);
        Core.flip(mRgbaF, mRgba, 1 );

        Core.MinMaxLocResult hornResult = compare(mRgba, hornTempl);
        Core.MinMaxLocResult zeroResult = compare(mRgba, zeroTempl);
        Point matchLoc;
        String found;
        Mat template;
        if(hornResult.minVal < zeroResult.minVal){
            matchLoc = hornResult.minLoc;
            found = "horn";
            template = hornTempl;
        }
        else{
            matchLoc = zeroResult.minLoc;
            found = "zero";
            template = zeroTempl;
        }

        Imgproc.rectangle(mRgba, matchLoc, new Point(matchLoc.x + template.cols(),
                matchLoc.y + template.rows()), new Scalar(0, 255, 0));
        Imgproc.putText(mRgba,found, matchLoc,Core.FONT_HERSHEY_COMPLEX,1.0,new Scalar(255));

        return mRgba; // This function must return
*/
    }
}
