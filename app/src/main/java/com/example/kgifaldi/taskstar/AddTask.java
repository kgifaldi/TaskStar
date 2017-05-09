package com.example.kgifaldi.taskstar;

import android.animation.Animator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.amulyakhare.textdrawable.TextDrawable;

import java.io.PushbackInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

/**
 * Created by Luigi on 4/8/17.
 */

public class AddTask extends Activity {
    LinearLayout ll;
    String FLAG = "plus";
    View but;
    ScrollView scrollView; // ImageView imageView
    Vector<Integer> iv = new Vector<>();
    private EditText TaskNametext;
    private EditText TaskRewardtext;
    private String frequency;
    private static Parent this_parent = new Parent();
    // DATABASE HELPER ----------------------------------------------
    DBHelper dbHelper;
    // ----------------------------------------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtaskview);
     //   Intent parent_main_intent = getIntent();
     //   this_parent = (Parent) parent_main_intent.getSerializableExtra("parent");


        //final String parent_id = this_parent.getId();
        // DATABASE HELPER ----------------------------------------------
        dbHelper = new DBHelper(this.getApplicationContext());
        // ----------------------------------------------------------------------

        final String parent_id = ParentMain.pid;

        int resID = getApplicationContext().getResources().getIdentifier("tasks", "raw", getApplicationContext().getPackageName());

        MyCsvFileReader tasks_csv = new MyCsvFileReader(getApplicationContext());
        ArrayList<String[]> tasks_list = tasks_csv.readCsvFileBySemiColon(resID);

        for (String[] element : tasks_list){
            PublicData.all_tasks.add(new TaskClass(element[0], element[1]));
        }

        ArrayList<Child> children_array_list;
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);

        scrollView = (ScrollView) findViewById(R.id.ScrollView02);
        but = (View) findViewById(R.id.AddChildFAB);

        final ToggleButton jt = (ToggleButton) findViewById(R.id.today_toggle);
        final ToggleButton rt = (ToggleButton) findViewById(R.id.repeat_toggle);
        final LinearLayout wl = (LinearLayout) findViewById(R.id.weekly_list);
        jt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!jt.isChecked()) {
                    jt.setChecked(false);
                    rt.setChecked(true);
                    jt.setBackgroundTintList(getResources().getColorStateList(R.color.primaryText));
                    rt.setBackgroundTintList(getResources().getColorStateList(R.color.text_icons));
                    wl.setVisibility(View.INVISIBLE);
                }
            }
        });

        rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rt.isChecked()) {
                    rt.setChecked(false);
                    jt.setChecked(true);
                    jt.setBackgroundTintList(getResources().getColorStateList(R.color.text_icons));
                    rt.setBackgroundTintList(getResources().getColorStateList(R.color.primaryText));
                    wl.setVisibility(View.VISIBLE);
                }
            }
        });


        int txtSz = 40;

        ll = (LinearLayout) findViewById(R.id.parentchild_list2);

        // some variables used to format xml elements
        int cardHeight = 300;
        int tempId; // tempId used when generating new id for each CardView
        // add Children cards to child_login:

        children_array_list = PublicData.children_list;

        for(Child each_child : children_array_list) {

            // set lp to linear layouts params to pass to cards
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lp_txt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 50, 0, 0);

            // instantiate card view and set some values
            CardView tmp = new CardView(this);
            tmp.setBackgroundColor(getResources().getColor(R.color.text_icons));


            // give card unique id for future referencing:
            tempId = View.generateViewId();
            tmp.setId(tempId);
            tmp.setLayoutParams(lp);
            tmp.setClickable(true);
            tmp.setMaxCardElevation(25);
            tmp.setCardElevation(15);
            tmp.setMinimumHeight(cardHeight);


            tmp.setTransitionName(getString(R.string.transition_string));

            // add ripple effect to card!
            ColorStateList csl = ColorStateList.valueOf(getResources().getColor(R.color.secondaryText));
            RippleDrawable d = new RippleDrawable(csl, null, null);
            tmp.setForeground(d);

            // initialize ImageView to place into Child Card
            // TODO: dynamically select child photo, rn generic photo is used
            // ImageView childImg = new ImageView(this);
            //childImg.setLayoutParams(lp);

            // childImg.setImageResource(R.drawable.genchild);
            //childImg.setMaxWidth(cardHeight/2);
            //childImg.setMinimumHeight(cardHeight/2);
            //childImg.setMaxHeight(cardHeight/2);
            //childImg.setAdjustViewBounds(true);


            // TODO: formatting image and text with each child card...

            // initialize TextView to place into Child Card
            TextView NameText = new TextView(this);
            NameText.setLayoutParams(lp);
            NameText.setText(each_child.getUsername().trim());
            NameText.setTextSize(txtSz);
            NameText.setPadding(450, 65, 0, 0);
            NameText.setTextColor(getResources().getColor(R.color.colorSecondary));
            int randomColor = MaterialColorPalette.getRandomColor("500");


            String letter = (NameText.getText().charAt(0) + "");
            TextDrawable drbl = TextDrawable.builder().buildRound(letter, randomColor);
            ImageView childImg = new ImageView(this);

            childImg.setImageDrawable(drbl);
            int width = 350;
            int height = 350;
            childImg.setPadding(50, 50, 50, 50);
            LinearLayout.LayoutParams prms = new LinearLayout.LayoutParams(width, height);
            childImg.setLayoutParams(prms);


            // finally, add text and image to cardView and add cardView to linear layout within child_login xml file
            tmp.addView(childImg);
            tmp.addView(NameText);

            tmp.setAlpha((float) 0.9);

            ll.addView(tmp);

            // add onClickListener to CardViews
            setCardListener(tempId);
            iv.add(tempId);
            enterReveal(childImg);
        }

        final View FAB = findViewById(R.id.AddChildFAB);
        FAB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                // this button will submit all children selected to the database


                TaskNametext = (EditText)findViewById(R.id.TaskName);
                System.out.println(TaskNametext.getText());
                TaskRewardtext = (EditText) findViewById(R.id.TaskReward);
                System.out.println(TaskRewardtext.getText());

                PublicData.all_tasks.add(new TaskClass(TaskNametext.getText().toString(), TaskRewardtext.getText().toString()));

                if(((LinearLayout)findViewById(R.id.weekly_list)).getVisibility() == View.INVISIBLE){
                    frequency = "once";
                } else {
                    frequency = "";
                    if (((CheckBox)findViewById(R.id.Mon_cb)).isChecked()){
                        frequency += "1";
                    } else {
                        frequency += "0";
                    }
                    if (((CheckBox)findViewById(R.id.Tue_cb)).isChecked()){
                        frequency += "1";
                    } else {
                        frequency += "0";
                    }
                    if (((CheckBox)findViewById(R.id.Wed_cb)).isChecked()){
                        frequency += "1";
                    } else {
                        frequency += "0";
                    }
                    if (((CheckBox)findViewById(R.id.Thu_cb)).isChecked()){
                        frequency += "1";
                    }else {
                        frequency += "0";
                    }
                    if (((CheckBox)findViewById(R.id.Fri_cb)).isChecked()){
                        frequency += "1";
                    } else {
                        frequency += "0";
                    }
                    if (((CheckBox)findViewById(R.id.Sat_cb)).isChecked()){
                        frequency += "1";
                    } else {
                        frequency += "0";
                    }
                    if (((CheckBox)findViewById(R.id.Sun_cb)).isChecked()){
                        frequency += "1";
                    } else {
                        frequency += "0";
                    }
                }
                System.out.println(frequency);

                // get list of all children slected
                ArrayList<String> childrenSelected = new ArrayList<String>();
                for(int vi = 0; vi < iv.size(); vi++) {
                    if (findViewById(iv.get(vi)).getAlpha() > 0.95) {
                       // childrenSelected.add(ChildLogin.children[vi]);

                    }
                    childrenSelected.add(ChildLogin.children[vi]);



                }

                String[] temp_tasklist;

                ArrayList<Child> children_fromdb = dbHelper.get_children_from_db(parent_id);
                for(Child child : children_fromdb){
                    if(childrenSelected.contains(child.getUsername())){
                        temp_tasklist = child.getTaskList();
                        temp_tasklist[(temp_tasklist.length)+1] = TaskNametext.getText().toString();
                    }

                }

                ContentValues contentValuesChild = new ContentValues();
                //dbHelper.deleteData(dbHelper.TABLE_CHILDREN, null, );
                for(Child child: children_fromdb){
                    contentValuesChild.put(dbHelper.CHILD_ID, child.getId());
                    contentValuesChild.put(dbHelper.CHILDS_PARENT, child.getParentId());
                    contentValuesChild.put(dbHelper.CHILD_USER_NAME, child.getUsername());
                    contentValuesChild.put(dbHelper.CHILD_REWARD_BALANCE, child.getRewardBalance());
                    String [] rewardsPurchased = child.getRewardsPurchased();
                    StringBuilder buffer = new StringBuilder();
                    for (String each : rewardsPurchased)
                        buffer.append(",").append(each);
                    String rewardsPurchasedString = buffer.deleteCharAt(0).toString();

                    String [] rewardsAvailable = child.getRewardsAvailable();
                    StringBuilder buffer_new = new StringBuilder();
                    for (String each : rewardsAvailable)
                        buffer.append(",").append(each);
                    String rewardsAvailableString = buffer_new.deleteCharAt(0).toString();

                    contentValuesChild.put(dbHelper.REWARDS_PURCHASED_LIST, rewardsPurchasedString);
                    contentValuesChild.put(dbHelper.REWARDS_AVAILABLE_LIST, rewardsAvailableString);

                    String [] tasks = child.getTaskList();
                    StringBuilder buffer_tasks = new StringBuilder();
                    for (String each : tasks)
                        buffer.append(",").append(each);
                    String tasksString = buffer_new.deleteCharAt(0).toString();

                    contentValuesChild.put(dbHelper.TASK_LIST, tasksString);
                    contentValuesChild.put(dbHelper.TASK_LIST, child.getImageSrc());

                    dbHelper.insertData(dbHelper.TABLE_CHILDREN, contentValuesChild);

                }

                ContentValues contentValuesTask = new ContentValues();

                contentValuesTask.put(dbHelper.TASK_DESCRIPTION, TaskNametext.getText().toString());
                contentValuesTask.put(dbHelper.COINS_WORTH, TaskRewardtext.getText().toString());
                contentValuesTask.put(dbHelper.WEEKLY_OCCURENCE, frequency);

                dbHelper.insertData(dbHelper.TABLE_TASK, contentValuesChild);

                // TODO: push this ArryayList of children to database:
                // TODO: push task name and task reward to each child in this array

                // start new intent
/*
                Intent intent = new Intent(AddTask.this, ParentMain.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("parent", (Serializable) this_parent);
                intent.putExtras(bundle);
                startActivity(intent);
                */
                finish();


                /* commenting this out: changing FAB from selecting all children -> submitting children
                int alreadySel = 0;
                int sz = iv.size();
                System.out.println("SIZE, HERRRRRRRREE, APLHA OF FIRSSTTTTT:");
                System.out.println(sz);

                for(int vi = 0; vi < iv.size(); vi++){
                    if(findViewById(iv.get(vi)).getAlpha() == (float)1.0)
                        alreadySel++;
                    findViewById(iv.get(vi)).setBackgroundColor(getResources().getColor(R.color.primaryText));
                    findViewById(iv.get(vi)).setAlpha((float) 1.0);

                }
                if(alreadySel == sz){
                    for(int vi = 0; vi < iv.size(); vi++){
                        findViewById(iv.get(vi)).setBackgroundColor(getResources().getColor(R.color.text_icons));
                        findViewById(iv.get(vi)).setAlpha((float) .9);
                    }

                }
            */
            }
        });

    }

    void setCardListener(int cardId) {

        View card = (View) findViewById(cardId);


        card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                float alph = v.getAlpha();

                // card is not selected: change to selected
                if (alph == (float) 0.9) {
                    v.setBackgroundColor(getResources().getColor(R.color.primaryText));
                    v.setAlpha((float) 1.0);
                } else { // card is selected: change to not selected
                    v.setBackgroundColor(getResources().getColor(R.color.text_icons));
                    v.setAlpha((float) 0.9);
                }

            }

        });

    }


    void enterReveal(final View v) {

        v.post(new Runnable() {
            @Override
            public void run() {

                int cx = v.getMeasuredWidth() / 2;
                int cy = v.getMeasuredHeight() / 2;

                int finRad = Math.max(v.getWidth(), v.getHeight()) / 2;

                Animator anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0, finRad);
                v.setVisibility(View.VISIBLE);
                anim.setDuration(500);
                anim.start();

            }
        });
    }

}
