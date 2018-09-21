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
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


public class Menu_fragment extends Fragment{



    ArrayList<String> _menu = new ArrayList<>();
    FirebaseAuth fnAuth;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fnAuth = FirebaseAuth.getInstance();
        _menu.add("BMI");
        _menu.add("Weight");
        _menu.add("Setup");
        _menu.add("Sign out");

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
                    Toast.makeText(getActivity(), "BMI", Toast.LENGTH_SHORT).show();
                }
                else if (_menu.get(i).equals("Weight")){
                    Log.d("MENU", "Goto Weight");
                    Toast.makeText(getActivity(), "Weight", Toast.LENGTH_SHORT).show();
                }
                else if (_menu.get(i).equals("Sign out")){
                    fnAuth.signOut();
                    Log.d("MENU", "SIGN OUT");
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_view, new Login_fragment()).commit();
                }
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

}
