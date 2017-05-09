package com.example.kgifaldi.taskstar;

/**
 * Created by Luigi on 5/9/17.
 */

public class TaskClass {
    String task_id;
    String Name;
    String Prize;

    TaskClass (String id, String nm, String pz){
        task_id = id;
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

    public String getTaskId() {
        return task_id;
    }

    public void setTaskId(String id) {
        task_id = id;
    }

}
