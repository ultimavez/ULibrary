package com.dlince.library.organizations.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crud {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("user_id")
    @Expose
    private Integer user_id;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("entity")
    @Expose
    private String entity;

    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("batch")
    @Expose
    private Integer batch;

    public Crud(){

    }
    public Crud(Integer id, Integer user_id, String type, String entity, String created, Integer batch) {
        this.id = id;
        this.user_id = user_id;
        this.type = type;
        this.entity = entity;
        this.created = created;
        this.batch = batch;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getBatch() {
        return batch;
    }

    public void setBatch(Integer batch) {
        this.batch = batch;
    }
}