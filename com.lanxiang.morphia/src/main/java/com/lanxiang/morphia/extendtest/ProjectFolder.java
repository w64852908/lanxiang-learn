package com.lanxiang.morphia.extendtest;


import org.mongodb.morphia.annotations.Entity;

/**
 * Created by lanxiang on 2017/2/23.
 */

@Entity(value = "Folder")
public class ProjectFolder extends BasicFolder {

    private Integer progress;

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "class=" + this.getClass() +
                ", id=" + id +
                ", name=" + name +
                ", level=" + level +
                ", type=" + type +
                ", progress=" + progress +
                '}';
    }
}
