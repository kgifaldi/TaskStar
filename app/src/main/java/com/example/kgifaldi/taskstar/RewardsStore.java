package com.example.kgifaldi.taskstar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

import com.example.kgifaldi.taskstar.R;

import static com.example.kgifaldi.taskstar.R.id.textV;

public class RewardsStore extends Activity {

    GridView gridView;

    static final String[] numbers = new String[] {
            "Water Park", "TV", "Computer", "Desert", "Everest",
            "Forest", "Giddy", "Hiking trip", "Food", "Pet Dog",
            "New computer", "Gameboy", "XBox", "Nintendo", "Oprah",
            "Prince of a Country", "Quickness", "Road to nowhere", "Station", "T-ball trophy",
            "University scholarship", "Vices", "Work", "Xavier", "Yolo", "Zebra"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.rewards_store);

        gridView = (GridView) findViewById(R.id.gridView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, numbers);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                v.setTransitionName("profile");
                v.setId(textV);

                //Toast.makeText(getApplicationContext(),
                        //((TextView) v).getText(), Toast.LENGTH_SHORT).show();
                    TextView txtV = (TextView) findViewById(textV);
                    Intent intent = new Intent(RewardsStore.this, Reward.class);
                    //intent.putExtra(Reward.EXTRA_CONTACT, contact);
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(RewardsStore.this, findViewById(textV) , "profile");
                    //ActivityOptionsCompat options = ActivityOptionsCompa.makeSceneTransition
                    //ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, (View)findViewById(R.id.textV), "profile");
                    startActivity(intent, options.toBundle());
                    //supportFinishAfterTransition();
            }
        });

    }

}