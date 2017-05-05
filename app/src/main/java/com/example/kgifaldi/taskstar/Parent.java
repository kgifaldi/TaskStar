package com.example.kgifaldi.taskstar;

/**
 * Created by Luigi on 5/5/17.
 */

public class Parent {
    String id;
    String username;
    String[] child_ids;
    String image_src;

    Parent(String[] Info){
        setUsername(Info[0]);
        setChild_ids((Info[1].split(" ")));
        setImage_src(Info[2]);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getChild_ids() {
        return child_ids;
    }

    public void setChild_ids(String[] child_ids) {
        this.child_ids = child_ids;
    }

    public String getImage_src() {
        return image_src;
    }

    public void setImage_src(String image_src) {
        this.image_src = image_src;
    }

}
