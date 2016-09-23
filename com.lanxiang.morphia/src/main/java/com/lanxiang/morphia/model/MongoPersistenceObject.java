package com.lanxiang.morphia.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Version;

/**
 * Created by lanxiang on 16/9/6.
 */
@Entity
public class MongoPersistenceObject {

    @Id
    @Indexed
    private ObjectId id;

    @Version
    private Long version;

    public String getId() {
        if (id == null) {
            return null;
        }
        return id.toString();
    }

    public void setId(String id) {
        if (id == null) {
            this.id = null;
        } else {
            this.id = new ObjectId(id);
        }
    }

    public ObjectId getObjectId() {
        return id;
    }

    public void setObjectId(ObjectId objectId){
        this.id = objectId;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
