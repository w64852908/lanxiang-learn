package com.lanxiang.morphia.extendtest;

import lombok.Data;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

/**
 * Created by lanxiang on 2017/3/9.
 */

@Data
@Entity(value = "Folder")
public abstract class BasicFolder {

    @Id
    @Indexed
    protected ObjectId id;

    protected String name;

    protected Integer level;

    protected Integer type;

    public String getId() {
        if (id == null) {
            return null;
        }
        return this.id.toString();
    }

    public void setId(String id) {
        if (id == null) {
            this.id = null;
        } else {
            this.id = new ObjectId(id);
        }
    }
}
