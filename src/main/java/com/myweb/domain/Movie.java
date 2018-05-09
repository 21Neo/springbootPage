package com.myweb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Movie implements Serializable{
    private static final long serialVersionUID = -7642422705016864320L;
    private Integer id;
    private String name;
    private Double price;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date actionTime;

    public Movie() {
    }

    public Movie(Integer id, String name, Double price, Date actionTime) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.actionTime = actionTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getActionTime() {
        return actionTime;
    }

    public void setActionTime(Date actionTime) {
        this.actionTime = actionTime;
    }
}
