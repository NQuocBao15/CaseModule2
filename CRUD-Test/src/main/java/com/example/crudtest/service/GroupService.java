package com.example.crudtest.service;

import com.example.crudtest.dao.GroupDAO;
import com.example.crudtest.model.Group;

import java.util.ArrayList;
import java.util.List;

public class GroupService {
    public static List<Group> getGroup() {
        return new GroupDAO().getGroup();
    }
}
