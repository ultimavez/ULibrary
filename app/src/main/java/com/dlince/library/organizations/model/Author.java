package com.dlince.library.organizations.model;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("names")
    @Expose
    private String names;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("birth")
    @Expose
    private String birth;

    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("pweb")
    @Expose
    private String pweb;

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("modified")
    @Expose
    private String modified;

    public Author() {
    }

    public Author(Integer id, String names, String email, String gender, String birth, String address, String pweb,
                  Integer status
    ) {
        this.id = id;
        this.names = names;
        this.status = status;
        this.email =email;
        this.gender =gender;
        this.birth=birth;
        this.address=address;
        this.pweb=pweb;

    }

    public Integer getId() {
        return id;
    }

    public String getNames() {
        return names;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getBirth() {
        return birth;
    }

    public String getAddress() {
        return address;
    }

    public String getPweb() {
        return pweb;
    }

    public Integer getStatus() {
        return status;
    }

    public String getCreated() {
        return created;
    }

    public String getModified() {
        return modified;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPweb(String pweb) {
        this.pweb = pweb;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }
}