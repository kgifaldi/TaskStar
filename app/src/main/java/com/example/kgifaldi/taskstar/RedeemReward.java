package com.example.kgifaldi.taskstar;

import android.animation.Animator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by kgifaldi on 4/10/17.
 */

public class RedeemReward extends Activity {

    LinearLayout ll;
    String FLAG = "plus";
    View but;
    ScrollView scrollView; // ImageView imageView
    Vector<Integer> iv = new Vector<>();

    DBHelper dbHelper;
    final ArrayList<String> selected = new ArrayList<String>();

    @Override

    /* TODO:

        1) String[] rewardIDs = {get this from database}
        2) String[] rewardNames = {get this from database}
        3) String[] rewardVal = {get this from database}

     */

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.redeem_reward);
        final String[] rewardsDescriptions = PublicData.selected_child.getRewardsPurchased();
        //System.out.println(rewardsDescriptions[1]);

        //TODO
        /*
        dbHelper = new DBHelper(this.getApplicationContext());
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 2);

        int resID = getApplicationContext().getResources().getIdentifier("rewards", "raw", getApplicationContext().getPackageName());


        // Read in the csv file
        MyCsvFileReader rewards_csv = new MyCsvFileReader(getApplicationContext());
        ArrayList<String[]> rewards_list = rewards_csv.readCsvFile(resID);

        ContentValues contentValues = new ContentValues();


        for(String[] rew : rewards_list){
            String[] temp_rew = rew[0].split(";");
            contentValues.put(dbHelper.REWARD_ID , temp_rew[0]);
            contentValues.put(dbHelper.REWARD_DESCRIPTION,  temp_rew[1]);
            contentValues.put(dbHelper.PRICE,  temp_rew[2]);
            dbHelper.insertData(dbHelper.TABLE_REWARD, contentValues);
        }


        ArrayList<ArrayList<String>> rewardsinfo = dbHelper.get_Rewards();

        ArrayList<String> rew_prz_from_db = rewardsinfo.get(1);
        ArrayList<String> rew_desc_from_db = rewardsinfo.get(0);
        ArrayList<String> rewardsValues = new ArrayList<String>();

        System.out.print(rewardsDescriptions.length);
        System.out.print(rew_desc_from_db.size());
        System.out.println("----");

        int counter2;
        for (String rewd1 : rewardsDescriptions){
            counter2 = 0;
            System.out.println("rewd1");
            System.out.println(rewd1);
            for (String rewd2 : rew_desc_from_db){
                System.out.println("rewd2");
                System.out.println(rewd2);
                if (rewd1.contains(rewd2.trim())){
                    rewardsValues.add(rew_prz_from_db.get(counter2));
                    break;
                }
                counter2++;
            }
        }

        */
        //String rewardVal = "100";

        scrollView = (ScrollView) findViewById(R.id.ScrollView02);
        but = (View) findViewById(R.id.AddChildFAB);


        int txtSz = 40;

        ll = (LinearLayout) findViewById(R.id.parentchild_list2);

        // some variables used to format xml elements
        int cardHeight = 300;
        int tempId; // tempId used when generating new id for each CardView
        // add Children cards to child_login:

        //System.out.print(rewardsDescriptions.length);
        //System.out.print(rewardsValues.size());

        for (int i = 0; i < rewardsDescriptions.length; i++) {

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
            // TODO: formatting image and text with each child card...
            // initialize TextView to place into Child Card
            TextView NameText = new TextView(this);
            NameText.setLayoutParams(lp);
            NameText.setText(rewardsDescriptions[i]);
            NameText.setTextSize(txtSz);
            NameText.setPadding(450, 65, 0, 0);
            NameText.setTextColor(getResources().getColor(R.color.colorSecondary));
            int randomColor = MaterialColorPalette.getRandomColor("500");


            String letter = (NameText.getText().charAt(0) + "");
            //System.out.println("HERE? bef");
            //letter = rewardsValues.get(i);
            letter = "1";
            //System.out.println("HERE? aft");
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
            setCardListener(tempId, NameText.getText().toString());
            iv.add(tempId);
            enterReveal(childImg);
        }

        final View FAB = findViewById(R.id.AddChildFAB);
        FAB.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String[] newRewards = new String[rewardsDescriptions.length - selected.size()];

                int counter = 0;
                //System.out.println("Resulting Rewards");
                for(String reward : rewardsDescriptions){
                    if (selected.contains(reward)){
                        continue;
                    } else {
                        //System.out.println(reward);
                        newRewards[counter] = reward;
                    }
                    counter++;
                }
                PublicData.selected_child.setRewardsPurchased(newRewards);
                /*
                Intent intent;
                intent = new Intent(RedeemReward.this, ChildMain.class);
                startActivity(intent);
                */
                finish();
            }
        });

        /*TODO:

        iterate through rewardNames and Vals
        create each checkbox element for each reward

         */

    }

    void setCardListener(int cardId, final String rewName) {

        View card = (View) findViewById(cardId);


        card.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                float alph = v.getAlpha();

                // card is not selected: change to selected
                if (alph == (float) 0.9) {
                    v.setBackgroundColor(getResources().getColor(R.color.primaryText));
                    v.setAlpha((float) 1.0);
                    selected.add(rewName);
                    //System.out.println(rewName);
                } else { // card is selected: change to not selected
                    v.setBackgroundColor(getResources().getColor(R.color.text_icons));
                    v.setAlpha((float) 0.9);
                    selected.remove(rewName);
                    //System.out.println(rewName);
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