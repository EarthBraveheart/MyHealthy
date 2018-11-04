package com.example.healthymop.myhealthy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.healthymop.myhealthy.Weight.Weight_fragment;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class Menu_fragment extends Fragment{
    @Override
    public void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        _menu.add("BMI");
        _menu.add("Weight");
        _menu.add("Sleep");
        _menu.add("Sign out");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    ArrayList<String> _menu = new ArrayList<>();
    FirebaseAuth fnAuth;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fnAuth = FirebaseAuth.getInstance();


        ListView menuList = getView().findViewById(R.id.menu_list);
        final ArrayAdapter<String> menuAdapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_list_item_1, _menu
        );
        menuList.setAdapter(menuAdapter);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                if (_menu.get(i).equals("BMI")){
                    Log.d("MENU", "Goto BMI");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Bmi_fragment()).addToBackStack(null).commit();
                }
                else if (_menu.get(i).equals("Weight")){
                    Log.d("MENU", "Goto Weight");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Weight_fragment()).addToBackStack(null).commit();
                }
                else if (_menu.get(i).equals("Sleep")){
                    Log.d("MENU", "Goto Sleep");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Sleep_fragment()).addToBackStack(null).commit();
                }
                else if (_menu.get(i).equals("Sign out")){
                    fnAuth.signOut();
                    Log.d("MENU", "SIGN OUT");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Login_fragment()).addToBackStack(null).commit();
                }
            }
        });
    }


}
