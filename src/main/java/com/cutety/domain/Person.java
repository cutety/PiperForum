package com.cutety.domain;

/**
 * Description:
 * Created by cutety on 2019/11/23,7:41 下午.
 **/
public class Person extends BaseDomain {
    private Integer id;
    private String name;
    private String gender;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
