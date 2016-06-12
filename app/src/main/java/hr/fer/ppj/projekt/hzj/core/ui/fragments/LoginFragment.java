package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.cache.UserCache;
import hr.fer.ppj.projekt.hzj.core.controllers.UserController;
import hr.fer.ppj.projekt.hzj.core.models.business.User;
import hr.fer.ppj.projekt.hzj.core.services.HZJService;
import hr.fer.ppj.projekt.hzj.core.ui.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, ILogin {
    private EditText username;
    private EditText password;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        this.username = (EditText) view.findViewById(R.id.username);
        this.password = (EditText) view.findViewById(R.id.password);
        Button login = (Button) view.findViewById(R.id.login);

        login.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        // we have only 'login' button
        if (v.getId() == R.id.login) {
            if (!username.getText().toString().isEmpty()
                    && !password.getText().toString().isEmpty())
                UserController.tryToLogin(this,
                        username.getText().toString(), password.getText().toString());
            else
                Toast
                        .makeText(getActivity(), "Niste popunili sva polja!", Toast.LENGTH_SHORT)
                        .show();
        }
    }

    @Override
    public void setLoginResult(User user) {
        // if user exists, save it to local cache
        if (user != null) {
            Toast
                    .makeText(getActivity(),
                            "Dobrodošli natrag " + UserCache.getUser().getName() + "!",
                            Toast.LENGTH_SHORT)
                    .show();
            Intent mainActivity = new Intent(getActivity(), MainActivity.class);
            startActivity(mainActivity);
            getActivity().finish();
        }
        else
            Toast
                    .makeText(getActivity(), "Korisnik sa ovim informacijama ne postoji!", Toast.LENGTH_SHORT)
                    .show();
    }
}
