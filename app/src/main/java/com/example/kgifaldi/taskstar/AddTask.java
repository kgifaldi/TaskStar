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
import android.support.design.widget.FloatingActionButton;
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
    ArrayList<String> children_selected = new ArrayList<String>();
    private static Parent this_parent = new Parent();
    private View FAB;
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

        for (String[] element : tasks_list) {
            PublicData.all_tasks.add(new TaskClass(element[0], element[1], element[2]));
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


        int txtSz = 35;

        ll = (LinearLayout) findViewById(R.id.parentchild_list2);

        // some variables used to format xml elements
        int cardHeight = 300;
        int tempId; // tempId used when generating new id for each CardView
        // add Children cards to child_login:

        children_array_list = PublicData.children_list;

        for (Child each_child : children_array_list) {

            // set lp to linear layouts params to pass to cards
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lp_txt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 40, 0, 0);

            // instantiate card view and set some values
            CardView tmp = new CardView(this);
            tmp.setBackgroundColor(getResources().getColor(R.color.cardview_light_background));


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
            NameText.setPadding(350, 55, 0, 0);
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
            String child_id = each_child.getId();
            setCardListener(tempId, child_id);
            iv.add(tempId);
            enterReveal(childImg);


            // Set FAB listener
            setButtonListener(R.id.AddChildFAB);


        }
    }


    void setButtonListener(int button_id){
        FloatingActionButton add_task = (FloatingActionButton) findViewById(button_id);

        add_task.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // this button will submit all children selected to the database


                TaskNametext = (EditText)findViewById(R.id.TaskName);
                System.out.println(TaskNametext.getText());
                TaskRewardtext = (EditText) findViewById(R.id.TaskReward);
                System.out.println(TaskRewardtext.getText());

                int id = 1;
                for (TaskClass each_task: PublicData.all_tasks){
                    id += 1;
                }

                TaskClass new_task = new TaskClass(Integer.toString(id), TaskNametext.getText().toString(), TaskRewardtext.getText().toString());

                PublicData.all_tasks.add(new_task);

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

                // Get all the children that have been selected
                String string_of_children_selected = "";
                for (String each_child_id : children_selected){
                    string_of_children_selected.concat(" " + each_child_id + " ");
                }

                ArrayList<TaskClass> temp_tasklist = null;
                System.out.println("The public data children list is: ");

                System.out.print(PublicData.children_list);

                System.out.println("First child in PublicData: " + PublicData.children_list.get(0).getUsername());

                for(Child child : PublicData.children_list){
                    for (String child_selected : children_selected){
                        System.out.println("Processing child selected: " + child_selected);
                        if (child_selected.trim().equals(child.getId().trim())){
                            temp_tasklist = child.getTaskList();

                            System.out.println("Adding task = " + new_task.getName());
                            temp_tasklist.add(new_task);

                            child.setTaskList(temp_tasklist);
                        }
                    }

                }
                finish();

            }

        });
    }

    void setCardListener(int cardId, final String child_id) {

        View card = (View) findViewById(cardId);

        System.out.println("Child id on outside is " + child_id);


        card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                float alph = v.getAlpha();

                // card is not selected: change to selected
                if (alph == (float) 0.9) {
                    v.setBackgroundColor(getResources().getColor(R.color.primaryText));
                    v.setAlpha((float) 1.0);
                    System.out.println("Child id on inside is: " + child_id);
                    children_selected.add(child_id);
                } else { // card is selected: change to not selected
                    v.setBackgroundColor(getResources().getColor(R.color.text_icons));
                    v.setAlpha((float) 0.9);
                    children_selected.remove(child_id);
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
