package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Luigi on 4/8/17.
 */

public class ParentViewRewards extends Activity {
    String[] rewards = {"Reward1", "Reward2", "Reward3", "Reward4", "Reward5"};

    /*
    TODO: delete above array

    1) get parentId from seceureLogin.parendId
    2) access childId from ParentMain.childId
    3) use ParentMain.ChildId to access specific childs rewards from database {get childId rewards from database}
     */
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.parentsviewrewards);
            // grab existing linear layout (within child_login.xml) so that we can add our Views to it later
            ll = (LinearLayout) findViewById(R.id.rewards_list);
            // some variables used to format xml elements
            int cardHeight = 250;
            int txtSz = 40;
            int tempId; // tempId used when generating new id for each CardView
            // add Children cards to child_login:
            for(int i = 0; i < rewards.length; i++) {

                // set lp to linear layouts params to pass to cards
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams lp_txt = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(0, 40, 0, 0);

                // instantiate card view and set some values
                CardView tmp = new CardView(this);
                // give card unique id for future referencing:
                tempId = View.generateViewId();
                tmp.setId(tempId);
                tmp.setLayoutParams(lp);
                tmp.setMaxCardElevation(25);
                tmp.setCardElevation(15);
                tmp.setMinimumHeight(cardHeight);

                // add ripple effect to card!
                ColorStateList csl = ColorStateList.valueOf(getResources().getColor(R.color.secondaryText));
                RippleDrawable d = new RippleDrawable(csl, null, null);
                tmp.setForeground(d);
                TextView NameText = new TextView(this);
                NameText.setLayoutParams(lp);
                NameText.setText(rewards[i]);
                NameText.setTextSize(txtSz);
                NameText.setPadding(0, (cardHeight - txtSz)/4, 0, 0);
                NameText.setTextColor(getResources().getColor(R.color.primaryText));

                // finally, add text and image to cardView and add cardView to linear layout within child_login xml file
                tmp.addView(NameText);
                ll.addView(tmp);


            }
        }
}
