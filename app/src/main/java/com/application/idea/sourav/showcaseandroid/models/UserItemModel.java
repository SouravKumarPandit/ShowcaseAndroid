package com.application.idea.sourav.showcaseandroid.models;

public class UserItemModel {

    private String username;
    private String designation;
    private int transId;
    private boolean isSelected = false;

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTransId() {
        return transId;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }

    public String getUsername() {
      return username;
    }

    public void setSelected(boolean selected) {
      isSelected = selected;
    }


    public boolean isSelected() {
      return isSelected;
    }
}