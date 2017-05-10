package com.example.kgifaldi.taskstar;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.amulyakhare.textdrawable.TextDrawable;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Luigi on 4/8/17.
 */

public class AddReward extends Activity {

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
    // DATABASE HELPER ----------------------------------------------
    DBHelper dbHelper;
    // ----------------------------------------------------------------------



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrewardview);

        Intent parent_main_intent = getIntent();
        this_parent = (Parent) parent_main_intent.getSerializableExtra("parent");

        setButtonListener(R.id.AddRewardFAB);

        int txtSz = 35;

        ll = (LinearLayout) findViewById(R.id.parentchild_list2);

        // some variables used to format xml elements
        int cardHeight = 300;
        int tempId; // tempId used when generating new id for each CardView
        // add Children cards to child_login:

        ArrayList<Child> children_array_list = PublicData.children_list;

        for(Child each_child : children_array_list) {

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
        }



    }

    void setButtonListener(int button_id){
        FloatingActionButton add_reward = (FloatingActionButton) findViewById(button_id);

        add_reward.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Get all the children that have been selected
                String string_of_children_selected = "";
                for (String each_child_id : children_selected){
                    string_of_children_selected = string_of_children_selected + each_child_id + " ";
                }

                // Get the name of the reward
                EditText reward_name_edit_text = (EditText) findViewById(R.id.RewardName);
                String reward_name = reward_name_edit_text.getText().toString();

                // Get the price of the reward
                EditText reward_price_edit_text = (EditText) findViewById(R.id.RewardCost);
                String reward_price = reward_price_edit_text.getText().toString();

                // Load the current rewards
                int resID = getApplicationContext().getResources().getIdentifier("rewards", "raw", getApplicationContext().getPackageName());


                // Read in the csv file
                MyCsvFileReader reward_csv = new MyCsvFileReader(getApplicationContext());
                ArrayList<String[]> reward_list = reward_csv.readCsvFile(resID);
                int reward_id = 1;
                for (int i = 0; i < reward_list.size(); i++) {
                    reward_id += 1;
                }

                // Put it all into a list so all the info can be passed to the reward
                String [] info = {Integer.toString(reward_id), reward_name, reward_price, string_of_children_selected.trim()};

                // Initialize a reward
                RewardClass new_reward = new RewardClass(info);

                ArrayList<Child> selected_childs = new ArrayList<Child>();

                for (Child c : PublicData.children_list){
                    if (children_selected.contains(c.getId()) ){
                        selected_childs.add(c);
                        System.out.println(c.getId());
                    }
                }

                PublicData.parent_obj.add_reward(new_reward, selected_childs);


                Toast t = new Toast(getApplicationContext());
                t.makeText(getApplicationContext(), "Reward added!", Toast.LENGTH_SHORT).show();

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
