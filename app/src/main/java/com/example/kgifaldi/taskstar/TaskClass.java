package com.example.kgifaldi.taskstar;

/**
 * Created by Luigi on 5/9/17.
 */

public class TaskClass {
    String Name;
    String Prize;

    TaskClass (String nm, String pz){
        Name = nm;
        Prize = pz;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrize() {
        return Prize;
    }

    public void setPrize(String prize) {
        Prize = prize;
    }
}
