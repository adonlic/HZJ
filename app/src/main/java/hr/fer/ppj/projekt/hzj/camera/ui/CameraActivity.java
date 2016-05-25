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
    JavaCameraView cameraView;

    BaseLoaderCallback baseLoaderCallback;

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

    public CameraActivity() {
        Log.i(TAG, "Instantiated new " + this.getClass());
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (cameraView != null)
            cameraView.disableView();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (cameraView != null)
            cameraView.disableView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // OpenCVLoader.initDebug();
        // OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, baseLoaderCallback);
        // baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);

        Log.i(TAG, "Trying to load OpenCV library");
        if (!OpenCVLoader.initDebug()) {
            Log.d(TAG, "Internal OpenCV library not found. Using OpenCV Manager for initialization");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0, this, baseLoaderCallback);
        } else {
            Log.d(TAG, "OpenCV library found inside package. Using it!");
            baseLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        cameraView = (JavaCameraView) findViewById(R.id.camera_viewer);


        baseLoaderCallback = new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                switch (status) {
                    case LoaderCallbackInterface.SUCCESS:
                    {
                        // OpenCV loaded...
                        // Log.i(TAG, "OpenCV loaded successfully");
                        cameraView.enableView();
                    } break;
                    default:
                    {
                        super.onManagerConnected(status);
                    } break;
                }
            }
        };

        cameraView.setVisibility(SurfaceView.VISIBLE);
        cameraView.setCvCameraViewListener(this);

        difference = new Mat();
        framesTaken = 0;
    }

    // implementation of OPENCV 'CvCameraViewListener2' methods
    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);
        // mRgbaF = new Mat(height, width, CvType.CV_8UC4);
        // mRgbaT = new Mat(width, width, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }

    public Core.MinMaxLocResult compare(Mat img, Mat templ){
        int result_cols = img.cols() - templ.cols() + 1;
        int result_rows = img.rows() - templ.rows() + 1;
        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);

        Imgproc.matchTemplate(img, templ, result, Imgproc.TM_CCOEFF);
        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
        return Core.minMaxLoc(result);

    }

    @Override
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
    }
}
