package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.util.jar.Attributes;

/**
 * Created by kgifaldi on 4/5/17.
 */

public class ChildLogin extends Activity {
    String[] children = {"Sophie", "Emma", "Noah", "Lucas", "Emily"};

    LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_login);

        // find existing linear layout
        ll = (LinearLayout) findViewById(R.id.child_list);

        int cardHeight = 500;
        int txtSz = 40;
        // add Children cards to child_login:
        for(int i = 0; i < children.length; i++) {

            // set lp to linear layouts params to pass to cards
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            LinearLayout.LayoutParams lp_txt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 40, 0, 0);

            // instantiate card view and set some values
            CardView tmp = new CardView(this);
            tmp.setLayoutParams(lp);
            tmp.setClickable(true);
            tmp.setMaxCardElevation(25);
            tmp.setCardElevation(15);
            tmp.setMinimumHeight(cardHeight);

            // add ripple effect to card!
            ColorStateList csl = ColorStateList.valueOf(getResources().getColor(R.color.secondaryText)); // TODO: change color !
            RippleDrawable d = new RippleDrawable(csl, null, null);
            tmp.setForeground(d);

            // initialize ImageView to place into Child Card
            // TODO: dynamically select child photo, rn generic photo is used
            ImageView childImg = new ImageView(this);
            childImg.setLayoutParams(lp);

            childImg.setImageResource(R.drawable.genchild);
            childImg.setMaxWidth(cardHeight/2);
            childImg.setMinimumHeight(cardHeight/2);
            childImg.setMaxHeight(cardHeight/2);
            childImg.setAdjustViewBounds(true);


            // add align top right to layout params for image
          // TODO MAYBE lp.addRule(ImageView.)

            // add toRightOf to layout params for Text (text to right of image)


            // initialize TextView to place into Child Card
            TextView NameText = new TextView(this);
            NameText.setLayoutParams(lp);
            NameText.setText(children[i]);
            NameText.setTextSize(txtSz);
          //  NameText.lay
            // padding top =
            NameText.setPadding(0, (cardHeight - txtSz)/4, 0, 0);
            NameText.setTextColor(getResources().getColor(R.color.primaryText));

            // finally, add text and image to cardView and add cardView to linear layout within child_login xml file
            tmp.addView(childImg);
            tmp.addView(NameText);
            ll.addView(tmp);

        }


    }
}

