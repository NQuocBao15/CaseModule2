package com.example.crudtest.model;

public class Contact {
    private Integer id;
    private String name;
    private String mobile;
    private String email;
    private String company;
    private String title;
    private Group groups;
    private String avatar;

    public Contact() {
    }

    public Contact(Integer id, String name, String mobile, String email, String company, String title, Group groups, String avatar) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.company = company;
        this.title = title;
        this.groups = groups;
        this.avatar = avatar;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Group getGroups() {
        return groups;
    }

    public void setGroups(Group groups) {
        this.groups = groups;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
