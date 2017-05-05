package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class ParentLogin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parent_login);

        setListener(R.id.parentLoginButton);
    }

    void setListener(int buttonId){

        Button login = (Button) findViewById(buttonId);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Initializing csv reading
                int resID = getApplicationContext().getResources().getIdentifier("parents", "raw", getApplicationContext().getPackageName());

                // Read in the csv file
                MyCsvFileReader parent_csv = new MyCsvFileReader(getApplicationContext());
                ArrayList<String[]> parent_list = parent_csv.readCsvFile(resID);

                // Initialize a parent
                


                Intent intent = new Intent(ParentLogin.this, ParentMain.class);
                startActivity(intent);
            }
        });
    }
}
