package com.dlince.library.organizations.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("pages")
    @Expose
    private String pages;

    @SerializedName("year")
    @Expose
    private String year;

    @SerializedName("types_id")
    @Expose
    private Integer types_id;

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("modified")
    @Expose
    private String modified;

    @SerializedName("editorial_id")
    @Expose
    private Integer editorial_id;

    public Article(){

    }

    public Article(Integer id, String title, String pages, String year, Integer types_id, Integer status, String created, String modified, Integer editorial_id) {
        this.id = id;
        this.title = title;
        this.pages = pages;
        this.year = year;
        this.types_id = types_id;
        this.status = status;
        this.created = created;
        this.modified = modified;
        this.editorial_id = editorial_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getTypes_id() {
        return types_id;
    }

    public void setTypes_id(Integer types_id) {
        this.types_id = types_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public Integer getEditorial_id() {
        return editorial_id;
    }

    public void setEditorial_id(Integer editorial_id) {
        this.editorial_id = editorial_id;
    }
}
