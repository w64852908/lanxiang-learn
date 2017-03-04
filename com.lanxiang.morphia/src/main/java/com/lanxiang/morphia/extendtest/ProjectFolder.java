package com.lanxiang.morphia.extendtest;


import org.mongodb.morphia.annotations.Entity;

/**
 * Created by lanxiang on 2017/2/23.
 */
@Entity(value = "Folder")
public class ProjectFolder extends Folder {

    private Integer progress;

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }
}
