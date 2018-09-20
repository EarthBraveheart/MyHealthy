package com.example.healthymop.myhealthy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_fragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Log.d("LOGIN", "On Create");
        initNewaccount();
        initBack();
    }
    void initNewaccount(){
        Button _regBtn = (Button) getView().findViewById(R.id.register_btn);
        _regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText _regEmail = (EditText) getView().findViewById(R.id.reg_email);
                EditText _regPassword = (EditText) getView().findViewById(R.id.reg_password);
                EditText _regRepassword = (EditText) getView().findViewById(R.id.reg_repassword);
                String _regEmailStr = _regEmail.getText().toString();
                String _regPasswordStr = _regPassword.getText().toString();
                String _regRepasswordStr = _regRepassword.getText().toString();

                if (_regEmailStr.isEmpty() || _regPasswordStr.isEmpty() || _regRepasswordStr.isEmpty()){
                    Toast.makeText(getActivity(), "Plase enter your Email, Password, Repassword", Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER", "Plase enter your Email, Password, Repassword");
                }
                else if (_regPasswordStr.length() < 6 || _regRepasswordStr.length() < 6){
                    Toast.makeText(getActivity(), "Password must has more than 6 characters", Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER", "Password < 6 character");
                }
                else if (!_regPasswordStr.equals(_regRepasswordStr)){
                    Toast.makeText(getActivity(), "Password doesn't match with Repassword", Toast.LENGTH_SHORT).show();
                    Log.d("REGISTER", "Password doesn't match");
                }
            }
        });
    }

    void initBack(){
        Button _backBtn = (Button) getView().findViewById(R.id.reg_backBtn);
        _backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Login_fragment()).commit();
                Log.d("REGISTER", "Back to Login");
            }
        });
    }
}
