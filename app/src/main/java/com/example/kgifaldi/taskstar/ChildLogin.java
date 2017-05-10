package com.example.kgifaldi.taskstar;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.jar.Attributes;

public class ChildLogin extends Activity {
    public static String[] children = {"Ben", "Emma", "Ethan", "Nick", "Iris", "Sarah", "Henry"};
    public static int currChild = -1; // current child id of logged in child

    /*
        TODO: delete above:

        1) String[] children = {get this from database}
        2) onclick, store childId in public static variable to access from the childMain activity

    */

    public static String curr_name;
    public static int curr_color;
    LinearLayout ll;

    public void animateIntent(View v){

        Intent intent;
        intent = new Intent(this, ChildMain.class);

        String transitionName = getString(R.string.transition_string);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(ChildLogin.this, v, transitionName);
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }


    void setCardListener(final int cardId, final int color, final String childName, final char letter, final Child child_obj){

        View card = (View) findViewById(cardId);



        card.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                curr_color = color;
                curr_name = childName;
                PublicData.child_obj = child_obj;
                curr_name = childName;

                PublicData.selected_child = child_obj;


               animateIntent(v);
                finish();

            }

        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_login);

        // grab existing linear layout (within child_login.xml) so that we can add our Views to it later
        ll = (LinearLayout) findViewById(R.id.child_list);

        //ArrayList<Child> children_array_list = PublicData.children_list;

        // some variables used to format xml elements
        int cardHeight = 400;
        int txtSz = 35; // TODO TOCHANGE
        int tempId; // tempId used when generating new id for each CardView
        // add Children cards to child_login:

        ArrayList<Child> children_array_list;
        children_array_list = PublicData.children_list;

        for(Child each_child : children_array_list) {

            // set lp to linear layouts params to pass to cards
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lp_txt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 40, 0, 0); // TODO TOCHANGE

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

            // attempting transition with shared elements
            tmp.setTransitionName(getResources().getString(R.string.transition_string));

            // add ripple effect to card!
            ColorStateList csl = ColorStateList.valueOf(getResources().getColor(R.color.secondaryText));
            RippleDrawable d = new RippleDrawable(csl, null, null);
            tmp.setForeground(d);



            // TODO: formatting image and text with each child card...

            // initialize TextView to place into Child Card
            TextView NameText = new TextView(this);
            NameText.setLayoutParams(lp);
            NameText.setText(each_child.getUsername().trim());
            NameText.setTextSize(txtSz);
            NameText.setPadding(350, 55, 0, 0); // TODO TOCHANGE
            NameText.setTextColor(getResources().getColor(R.color.colorSecondary));
            int randomColor = MaterialColorPalette.getRandomColor("500");



            String letter = (NameText.getText().charAt(0)+"");
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
            ll.addView(tmp);
            enterReveal(childImg);
            // add onClickListener to CardViews
            //setCardListener(tempId, randomColor, children[i]);
            setCardListener(tempId, randomColor, each_child.getUsername(), each_child.getUsername().charAt(0), each_child);
            //setCardListener(tempId, randomColor, each_child.getUsername(), each_child);

        }


    }

    void enterReveal(final View v){

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

