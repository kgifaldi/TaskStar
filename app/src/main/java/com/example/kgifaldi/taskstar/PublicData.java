package com.example.kgifaldi.taskstar;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by kgifaldi on 5/7/17.
 */

public class PublicData extends Application {

    public static Parent parent_obj = null;
    public static ArrayList<Child> children_list;
    public static Child child_obj = null;
    // this may be unecssesary
    public static ArrayList<Child> new_children = new ArrayList<>();
    public static String password;

    //public static ArrayList<Child>


}
