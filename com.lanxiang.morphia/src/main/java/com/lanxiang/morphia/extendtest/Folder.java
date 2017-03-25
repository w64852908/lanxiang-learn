package com.lanxiang.morphia.extendtest;

import org.mongodb.morphia.annotations.Entity;

import java.util.Set;

/**
 * Created by lanxiang on 2017/2/23.
 */

@Entity(value = "Folder")
public class Folder extends BasicFolder {

    private Set<Integer> authority;

    public Set<Integer> getAuthority() {
        return authority;
    }

    public void setAuthority(Set<Integer> authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "class=" + this.getClass() +
                ", id=" + id +
                ", name=" + name +
                ", level=" + level +
                ", type=" + type +
                ", authority=" + authority +
                '}';
    }
}
