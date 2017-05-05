package com.example.kgifaldi.taskstar;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MaggieThomann on 5/5/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    public SQLiteDatabase database;
    private static String DATABASE_NAME = "task_star";
    private static final int DATABASE_VERSION = 1;

    // Parent Table
    private static final String PARENT_NAME = "parents";
    private static final String PARENT_ID = "_id";
    private static final String PARENT_USER_NAME = "parent_user_name";
    private static final String CHILDREN_IDS = "children_ids";
    public static String PARENT_IMAGE_SRC = "image_src";
    public static String PARENT_COL_URI = "image_uri";

    // Child Table
    private static final String CHILD_NAME = "children";
    private static final String CHILD_ID = "_id";
    private static final String CHILD_USER_NAME = "child_user_name";
    private static final String CHILD_REWARD_BALANCE = "child_reward_balance";
    private static final String REWARDS_PURCHASED_LIST = "rewards_purchased_list";
    private static final String REWARDS_AVAILABLE_LIST = "rewards_available_list";
    private static final String TASK_LIST = "task_list";
    public static String CHILD_IMAGE_SRC = "image_src";
    public static String CHILD_COL_URI = "image_uri";

    // Task Table
    private static final String TASK_NAME = "task";
    private static final String TASK_ID = "_id";
    private static final String TASK_DESCRIPTION = "task_description";
    private static final String COINS_WORTH = "coins_worth";
    private static final String WEEKLY_OCCURENCE = "weekly_occurence";

    // Reward Table
    private static final String REWARD_NAME = "reward";
    private static final String REWARD_ID = "_id";
    private static final String REWARD_DESCRIPTION = "reward_description";
    private static final String PRICE = "price";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
