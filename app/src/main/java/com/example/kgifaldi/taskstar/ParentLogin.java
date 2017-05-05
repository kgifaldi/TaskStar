package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;


public class ParentLogin extends Activity {

    private EditText parent_username_text = (EditText) findViewById(R.id.parent_username);
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // DATABASE HELPER ----------------------------------------------
        dbHelper = new DBHelper(this.getApplicationContext());
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);
        // ----------------------------------------------------------------------

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

                // Initialize a blank parent
                Parent parent_obj = new Parent(null);


                // Check to see which parent from the csv file we should load in
                // based on who is logging in
                for (int i = 0; i < parent_list.size(); i++){
                    String [] parent_info = parent_list.get(i);

                    String parent_name_from_csv = parent_info[0];
                    String parent_name_from_screen = parent_username_text.getText().toString();

                    if (parent_name_from_csv == parent_name_from_screen){
                        // We've found the parent!
                        // Create an instance of parent and break
                        parent_obj = new Parent(parent_info);

                        // Put the parent created into the database
                        // Creating content values
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(dbHelper.PARENT_USER_NAME, parent_obj.getUsername());
                        contentValues.put(dbHelper.PARENT_IMAGE_SRC, parent_obj.getImage_src());

                        String [] children_ids = parent_obj.getChild_ids();
                        String children_ids_string = null;
                        for (String id : children_ids){
                            children_ids_string += id;
                        }
                        contentValues.put(dbHelper.CHILDREN_IDS, children_ids_string);

                        dbHelper.insertData(dbHelper.TABLE_PARENT, contentValues);

                        break;
                    }
                }

                Intent parent_main_intent = new Intent(ParentLogin.this, ParentMain.class);
                parent_main_intent.putExtra("Parent", (Serializable) parent_obj);
                startActivity(parent_main_intent);
            }
        });
    }
}
