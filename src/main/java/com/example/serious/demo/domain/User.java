package com.example.serious.demo.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashSet;

public class User {
    private int id;
    private  String name;
    private String password;
    private String mobilePhone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        HashSet<User> set = new HashSet<>();

        if (!(o instanceof User)) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(getId(), user.getId())
                .append(getName(), user.getName())
                .append(getPassword(), user.getPassword())
                .append(getMobilePhone(), user.getMobilePhone())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .append(getName())
                .append(getPassword())
                .append(getMobilePhone())
                .toHashCode();
    }
}
