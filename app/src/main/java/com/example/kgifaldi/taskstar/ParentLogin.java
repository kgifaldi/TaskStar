package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class ParentLogin extends Activity {

    private EditText parent_username_text;
    DBHelper dbHelper;
    public static Parent parent_obj = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Change for Kyle



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


                Log.d("Parent Login", "Login has been clicked");

                parent_username_text = (EditText) findViewById(R.id.parent_username);
                System.out.println(parent_username_text);

                // Initializing csv reading
                int resID = getApplicationContext().getResources().getIdentifier("parents", "raw", getApplicationContext().getPackageName());


                // Read in the csv file
                MyCsvFileReader parent_csv = new MyCsvFileReader(getApplicationContext());
                ArrayList<String[]> parent_list = parent_csv.readCsvFile(resID);



                // Initialize a blank parent
                Parent parent_obj = new Parent();


                // Check to see which parent from the csv file we should load in
                // based on who is logging in
                for (int i = 0; i < parent_list.size(); i++){
                    String [] parent_info = parent_list.get(i);

                    String parent_name_from_csv = parent_info[1];

                    String parent_name_from_screen = parent_username_text.getText().toString();

                    Log.d("Parent Logged In: ", parent_name_from_screen);
                    Log.d("Parent from csv:  ", parent_name_from_csv);

                    if (parent_name_from_csv.trim().equals(parent_name_from_screen.trim())){
                        System.out.println("Parent matches the parent from the csv");
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

                        // Also load the children of that parent into the database
                        ContentValues contentValuesChild = new ContentValues();

                        // Initializing csv reading
                        int res_id_child = getApplicationContext().getResources().getIdentifier("children", "raw", getApplicationContext().getPackageName());


                        // Read in the csv file
                        MyCsvFileReader child_csv = new MyCsvFileReader(getApplicationContext());
                        ArrayList<String[]> child_list = child_csv.readCsvFileBySemiColon(res_id_child);


                        for (String id : children_ids){
                            children_ids_string += id;
                            System.out.println("Processing:  ... " + id);
                            for (String[] csv_list : child_list){
                                System.out.println("     CSV = " + csv_list[1]);
                                String csv_id = csv_list[1];
                                String parent_child_id = id;


                                if (csv_id.trim().equals(parent_child_id.trim())){
                                    Log.d("ParentLogin", "Child in csv matches child of current parent");

                                    // Create the purchased objects from the csv_list
                                    ArrayList<RewardClass> purchased_rewards = new ArrayList<RewardClass>();
                                    for (String each_purchased_reward : csv_list[4].split(",")){
                                        String [] reward_attrs = each_purchased_reward.split(" ");
                                        String [] reward_list_construct = {reward_attrs[1], reward_attrs[0], reward_attrs[2], "empty"};
                                        RewardClass reward_obj = new RewardClass(reward_list_construct);
                                        purchased_rewards.add(reward_obj);
                                    }


                                    // Create the available objects from the csv_list
                                    ArrayList<RewardClass> available_rewards = new ArrayList<RewardClass>();
                                    for (String each_available_reward : csv_list[5].split(",")){
                                        String [] reward_attrs = each_available_reward.split(" ");
                                        String [] reward_list_construct = {reward_attrs[1], reward_attrs[0], reward_attrs[2], "empty"};
                                        RewardClass reward_obj = new RewardClass(reward_list_construct);
                                        available_rewards.add(reward_obj);
                                    }


                                    // Create the available objects from the csv_list
                                    ArrayList<TaskClass> tasks_list = new ArrayList<TaskClass>();
                                    for (String each_task : csv_list[6].split(",")){
                                        String [] task_attrs = each_task.split(" ");
                                        TaskClass task_obj = new TaskClass(task_attrs[1], task_attrs[0], task_attrs[2]);
                                        tasks_list.add(task_obj);
                                    }


                                    String [] simple_child_info = Arrays.copyOfRange(csv_list, 0, 4);

                                    Child child_obj = new Child(simple_child_info, purchased_rewards, available_rewards, tasks_list, csv_list[7]);



                                    contentValuesChild.put(dbHelper.CHILD_ID, child_obj.getId());
                                    contentValuesChild.put(dbHelper.CHILDS_PARENT, child_obj.getParentId());
                                    contentValuesChild.put(dbHelper.CHILD_USER_NAME, child_obj.getUsername());
                                    Log.d("ParentLogin", ("Inserting child " + child_obj.getUsername() + " into the DB."));
                                    contentValuesChild.put(dbHelper.CHILD_REWARD_BALANCE, child_obj.getRewardBalance());
                                    contentValuesChild.put(dbHelper.CHILD_IMAGE_SRC, child_obj.getImageSrc());


                                    ArrayList<RewardClass> rewardsPurchased = child_obj.getRewardsPurchased();
                                    StringBuilder buffer = new StringBuilder();
                                    for (RewardClass each : rewardsPurchased)
                                        buffer.append(",").append(each.getRewardName());
                                    String rewardsPurchasedString = buffer.deleteCharAt(0).toString();

                                    ArrayList<RewardClass> rewardsAvailable = child_obj.getRewardsAvailable();
                                    StringBuilder buffer_new = new StringBuilder();
                                    for (RewardClass each : rewardsAvailable)
                                        buffer_new.append(",").append(each.getRewardName());
                                    String rewardsAvailableString = buffer_new.deleteCharAt(0).toString();
                                    ArrayList<TaskClass> tasks = child_obj.getTaskList();
                                    StringBuilder buffer_tasks = new StringBuilder();
                                    for (TaskClass each : tasks)
                                        buffer_tasks.append(",").append(each.getName());
                                    String tasksString = buffer_tasks.deleteCharAt(0).toString();

                                    contentValuesChild.put(dbHelper.CHILD_IMAGE_SRC, child_obj.getImageSrc());

                                    dbHelper.insertData(dbHelper.TABLE_CHILDREN, contentValuesChild);
                                }
                            }


                        }
                        contentValues.put(dbHelper.CHILDREN_IDS, children_ids_string);

                        dbHelper.insertData(dbHelper.TABLE_PARENT, contentValues);




                        break;
                    }
                }
                PublicData.parent_obj = parent_obj;

                ArrayList<Child> children_array_list = dbHelper.get_children_from_db(parent_obj.getId());
                PublicData.children_list = children_array_list;
                PublicData.password = ((TextView) findViewById(R.id.password_enter)).getText().toString();
                Intent parent_main_intent = new Intent(ParentLogin.this, MainActivity.class);



                //parent_main_intent.putExtra("Parent", (Serializable) parent_obj);
                startActivity(parent_main_intent);
            }
        });
    }
}
