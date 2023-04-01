package com.driver;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private int numberOfParticipants;

    private List<User> userList;

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public Group(String name, int numberOfParticipants) {
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
    }


    public Group(String name, int numberOfParticipants, List<User> userList) {
        this.name = name;
        this.numberOfParticipants = numberOfParticipants;
        this.userList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

    public void setNumberOfParticipants(int numberOfParticipants) {
        this.numberOfParticipants = numberOfParticipants;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
