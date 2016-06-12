package hr.fer.ppj.projekt.hzj.core.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import hr.fer.ppj.projekt.hzj.R;
import hr.fer.ppj.projekt.hzj.core.cache.UserCache;
import hr.fer.ppj.projekt.hzj.core.helpers.UserHelper;
import hr.fer.ppj.projekt.hzj.core.ui.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener, IRegister {
    private EditText username;
    private EditText password;
    private EditText name;
    private EditText surname;

    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        this.username = (EditText) view.findViewById(R.id.username);
        this.password = (EditText) view.findViewById(R.id.password);
        this.name = (EditText) view.findViewById(R.id.name);
        this.surname = (EditText) view.findViewById(R.id.surname);
        Button register = (Button) view.findViewById(R.id.register);

        register.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        // we have only 'login' button
        if (v.getId() == R.id.register) {
            if (!username.getText().toString().isEmpty()
                    && !password.getText().toString().isEmpty()
                    && !name.getText().toString().isEmpty()
                    && !surname.getText().toString().isEmpty())
                UserHelper.tryToRegisterNew(this,
                        username.getText().toString(), password.getText().toString(),
                        name.getText().toString(), surname.getText().toString());
            else
                Toast
                        .makeText(getActivity(), "Niste popunili sva polja!", Toast.LENGTH_SHORT)
                        .show();
        }
    }

    @Override
    public void setRegistrationResult(boolean successful) {
        if (successful) {
            Toast
                    .makeText(getActivity(),
                            "Hvala na registraciji " + UserCache.getUser().getName() + "!",
                            Toast.LENGTH_SHORT)
                    .show();
            Intent mainActivity = new Intent(getActivity(), MainActivity.class);
            startActivity(mainActivity);
            getActivity().finish();
        }
        else
            Toast
                    .makeText(getActivity(),
                            "Korisnik sa ovim korisničim imenom već postoji!", Toast.LENGTH_SHORT)
                    .show();
    }
}
