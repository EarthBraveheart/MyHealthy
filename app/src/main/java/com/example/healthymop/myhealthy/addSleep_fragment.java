package com.example.healthymop.myhealthy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

public class addSleep_fragment extends Fragment{
    SQLiteDatabase myDB;
    Sleep sleep;
    String status;

    @Override
    public void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        myDB = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);
        myDB.execSQL("CREATE TABLE IF NOT EXISTS sleep (id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR(10), toBedTime VARCHAR(5), awakeTime VARCHAR(5))");

        Bundle bundle = getArguments();
        try {
            sleep = (Sleep) bundle.getSerializable("sleep object");
            status = "edit";
        }
        catch (NullPointerException e){
            if (sleep == null){
                status = "new";
            }
            else {
                Log.d("sleep", "null pointer : " + e.getMessage());
            }
        }
        Log.d("sleep", "status : " + status);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_sleep, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (status.equals("edit")){
            setValue();
        }
        initBackBtn();
        initAddBtn();
    }

    public void setValue(){
        EditText date = getView().findViewById(R.id.add_sleep_date);
        EditText toBedTime = getView().findViewById(R.id.add_sleep_toBedTime);
        EditText awakeTime = getView().findViewById(R.id.add_sleep_awakeTime);
        date.setText(sleep.getDate());
        toBedTime.setText(sleep.getToBedTime());
        awakeTime.setText(sleep.getAwakeTime());
        Button addBtn = getView().findViewById(R.id.add_sleep_add_button);
        addBtn.setText("Edit");
    }

    public void initBackBtn(){
        Button backBtn = getView().findViewById(R.id.add_sleep_back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
    }

    public void initAddBtn(){
        Button addBtn = getView().findViewById(R.id.add_sleep_add_button);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText date = getView().findViewById(R.id.add_sleep_date);
                EditText toBedTime = getView().findViewById(R.id.add_sleep_toBedTime);
                EditText awakeTime = getView().findViewById(R.id.add_sleep_awakeTime);
                String dateStr = date.getText().toString();
                String toBedTimeStr = toBedTime.getText().toString();
                String awakeTimeStr = awakeTime.getText().toString();
                ContentValues row = new ContentValues();
                row.put("date", dateStr);
                row.put("toBedTime", toBedTimeStr);
                row.put("awakeTime", awakeTimeStr);
                if (status.equals("new")){
                    myDB.insert("sleep", null, row);
                }
                else if (status.equals("edit")){
                    myDB.update("sleep", row, "id="+sleep.getId(), null);
                }
                Toast.makeText(getContext(), "add new sleep time already", Toast.LENGTH_SHORT).show();
                getFragmentManager().popBackStack();
            }
        });
    }
}
