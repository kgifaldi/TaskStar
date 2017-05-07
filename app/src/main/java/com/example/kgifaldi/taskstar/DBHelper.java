package com.example.kgifaldi.taskstar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 * Created by MaggieThomann on 5/5/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "task_star";
    private static final int DATABASE_VERSION = 1;

    // Parent Table
    public static final String TABLE_PARENT = "parents";
    public static final String PARENT_ID = "_id";
    public static final String PARENT_USER_NAME = "parent_user_name";
    public static final String CHILDREN_IDS = "children_ids";
    public static final String PARENT_IMAGE_SRC = "image_src";

    // Child Table
    public static final String TABLE_CHILDREN = "children";
    public static final String CHILDS_PARENT = "childs_parent";
    public static final String CHILD_ID = "_id";
    public static final String CHILD_USER_NAME = "child_user_name";
    public static final String CHILD_REWARD_BALANCE = "child_reward_balance";
    public static final String REWARDS_PURCHASED_LIST = "rewards_purchased_list";
    public static final String REWARDS_AVAILABLE_LIST = "rewards_available_list";
    public static final String TASK_LIST = "task_list";
    public static final String CHILD_IMAGE_SRC = "image_src";

    // Task Table
    public static final String TABLE_TASK = "task";
    public static final String TASK_ID = "_id";
    public static final String TASK_DESCRIPTION = "task_description";
    public static final String COINS_WORTH = "coins_worth";
    public static final String WEEKLY_OCCURENCE = "weekly_occurence";

    // Reward Table
    public static final String TABLE_REWARD = "reward";
    public static final String REWARD_ID = "_id";
    public static final String REWARD_DESCRIPTION = "reward_description";
    public static final String PRICE = "price";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        // PARENT TABLE
        db.execSQL("CREATE TABLE " + TABLE_PARENT + " ("
                + PARENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PARENT_USER_NAME + " TEXT, "
                + CHILDREN_IDS + " TEXT, "
                + PARENT_IMAGE_SRC + " TEXT "
                + ");");

        // CHILD TABLE
        db.execSQL("CREATE TABLE " + TABLE_CHILDREN + " ("
                + CHILD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CHILDS_PARENT + " TEXT, "
                + CHILD_USER_NAME + " TEXT, "
                + CHILD_REWARD_BALANCE + " TEXT, "
                + REWARDS_PURCHASED_LIST + " TEXT, "
                + REWARDS_AVAILABLE_LIST + " TEXT, "
                + TASK_LIST + " TEXT, "
                + CHILD_IMAGE_SRC + " TEXT "
                + ");");


        // TASK TABLE
        db.execSQL("CREATE TABLE " + TABLE_TASK + " ("
                + TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TASK_DESCRIPTION + " TEXT, "
                + COINS_WORTH + " TEXT, "
                + WEEKLY_OCCURENCE + " TEXT "
                + ");");

        // REWARD TABLE
        db.execSQL("CREATE TABLE " + TABLE_REWARD + " ("
                + REWARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + REWARD_DESCRIPTION + " TEXT, "
                + PRICE + " TEXT"
                + ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists " + TABLE_PARENT );
        db.execSQL("DROP TABLE if exists " + TABLE_TASK );
        db.execSQL("DROP TABLE if exists " + TABLE_CHILDREN );
        db.execSQL("DROP TABLE if exists " + TABLE_REWARD );
        onCreate(db);
    }

    public void insertData(String tblname, ContentValues contentValues) {
        SQLiteDatabase db = getWritableDatabase();

        long ret = db.insert(tblname, null, contentValues );

        if (ret > -1) {
            System.out.println("Successfully inserted");
        } else {
            System.out.println("Insert Unsuccessful");
        }

        db.close();
    }

    public void deleteData(String tblname, String clause, int _id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(tblname, clause, new String[]{Integer.toString(_id)});
        db.close();
    }


    public Cursor getAllEntries(String tblname, String[] columns) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblname, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor getSelectEntries(String tblname, String[] columns, String where, String[] args) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(tblname, columns, where, args, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public String[] getTableFields(String tblname) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor dbCursor = db.query(tblname, null, null, null, null, null, null);
        String[] columnNames = dbCursor.getColumnNames();
        return columnNames;
    }
    public ArrayList<Child> get_children_from_db(String parent_id) {
        Log.d("DBHelper", "get_children_drom_db called");
        Log.d("DBHelper", ("called with id " + parent_id));
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CHILDREN, new String[]{});

        ArrayList<Child> parents_children = new ArrayList<Child>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    Log.d("DBHelper", "Parent ID " + cursor.getString(cursor.getColumnIndex(CHILDS_PARENT)).trim());
                    if (cursor.getString(cursor.getColumnIndex(CHILDS_PARENT)).trim().equals(parent_id.trim())){
                        String [] child_info = new String [8];

                        Log.d("DBHelper", ("Retrieving child " + (cursor.getString(cursor.getColumnIndex(CHILD_USER_NAME))) + " from the database"));

                        // Construct the string from the cursor
                        child_info[0] = (cursor.getString(cursor.getColumnIndex(CHILDS_PARENT)));
                        child_info[1] = (cursor.getString(cursor.getColumnIndex(CHILD_ID)));
                        child_info[2] = (cursor.getString(cursor.getColumnIndex(CHILD_USER_NAME)));
                        child_info[3] = (cursor.getString(cursor.getColumnIndex(CHILD_REWARD_BALANCE)));
                        child_info[4] = (cursor.getString(cursor.getColumnIndex(REWARDS_PURCHASED_LIST)));
                        child_info[5] = (cursor.getString(cursor.getColumnIndex(REWARDS_AVAILABLE_LIST)));
                        child_info[6] = (cursor.getString(cursor.getColumnIndex(TASK_LIST)));
                        child_info[7] = (cursor.getString(cursor.getColumnIndex(CHILD_IMAGE_SRC)));

                        // Construct the object and add it to the array
                        Child child = new Child(child_info);
                        parents_children.add(child);
                    }
                    else{
                        continue;
                    }


                }while (cursor.moveToNext());
            }
        }

        cursor.close();

        return parents_children;
    }

    /*
    public ArrayList<Child> get_children() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CHILDREN, new String[]{});

        ArrayList<Team> all_info = new ArrayList<Team>();

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {
                    // Create a string to store the team info
                    String [] team_info = new String [9];

                    // Construct the string from the cursor
                    team_info[0] = (cursor.getString(cursor.getColumnIndex(COL_NAME)));
                    team_info[1] = (cursor.getString(cursor.getColumnIndex(COL_LOGO)));
                    team_info[2] = (cursor.getString(cursor.getColumnIndex(COL_DATE)));
                    team_info[3] = (cursor.getString(cursor.getColumnIndex(COL_TIME)));
                    team_info[4] = (cursor.getString(cursor.getColumnIndex(COL_LOCATION)));
                    team_info[5] = (cursor.getString(cursor.getColumnIndex(COL_NICKNAME)));
                    team_info[6] = (cursor.getString(cursor.getColumnIndex(COL_RECORD)));
                    team_info[7] = (cursor.getString(cursor.getColumnIndex(COL_SCORE)));
                    team_info[8] = (cursor.getString(cursor.getColumnIndex(COL_ID)));

                    // Construct the object and add it to the array
                    Team team = new Team(team_info);
                    all_info.add(team);

                }while (cursor.moveToNext());
            }
        }

        cursor.close();

        return all_info;
    }

    public long get_team_id(String team_name) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TEAM +
                " WHERE " + COL_NAME + "=" + "'" + team_name + "'", null);

        // Create a string to store the team info
        long team_id = 0;

        if (cursor != null ) {
            if  (cursor.moveToFirst()) {
                do {

                    // Construct the string from the cursor
                    team_id = (cursor.getLong(cursor.getColumnIndex(COL_ID)));


                }while (cursor.moveToNext());
            }
        }

        cursor.close();

        return team_id;
    }
    */



}