package com.panther.redisson;

import java.io.Serializable;

public class user implements Serializable, Comparable<user> {

    private Integer id;
    private String name;

    public Integer getId() {
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

    @Override
    public int compareTo(user o) {
        return this.getId().compareTo(o.getId());
    }
}
