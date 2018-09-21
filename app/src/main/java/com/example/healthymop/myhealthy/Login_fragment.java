package com.example.healthymop.myhealthy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_fragment extends Fragment{
    FirebaseAuth fbAuth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.d("LOGIN", "On Create");
        initLoginbtn();
        initRegisterbtn();
    }

    void initLoginbtn(){
        fbAuth = FirebaseAuth.getInstance();
        Button _loginBtn = (Button) getView().findViewById(R.id.login_login_btn);
        _loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _user = (EditText) getView().findViewById(R.id.login_user_id);
                EditText _password = (EditText) getView().findViewById(R.id.login_password);
                String _userStr =  _user.getText().toString();
                String _passwordStr =  _password.getText().toString();
                if (_userStr.isEmpty() || _passwordStr.isEmpty()){
                    Toast.makeText(getActivity(), "Please enter your email or password", Toast.LENGTH_SHORT).show();
                    Log.d("LOGIN", "Email or Password is empty");
                }
                else{
                    fbAuth.signInWithEmailAndPassword(_userStr, _passwordStr).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if (fbAuth.getCurrentUser().isEmailVerified()){
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Menu_fragment()).commit();
                            }
                            else{
                                Toast.makeText(getActivity(), "Please verify your email.", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getActivity(), "Please enter your correct email and password.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }


    void initRegisterbtn(){
        TextView _registerBtn = (TextView) getView().findViewById(R.id.login_resiget_btn);
        _registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("LOGIN", "goto register");
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Register_fragment()).commit();
            }
        });
    }
}
