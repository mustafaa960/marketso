package com.miq.sms.models.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public  class UsersVo {

    private IntegerProperty id;
    private StringProperty UserName;
    private StringProperty UserFullName;
    private StringProperty password;
    private UsersType usersType;

    public UsersVo() {
        this.id = new SimpleIntegerProperty();
        this.UserName=new SimpleStringProperty();
        this.UserFullName=new SimpleStringProperty();
        this.password=new SimpleStringProperty();
        //this.usersType=getUsersType();
    }
    

    public int getId() {
        return this.id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }
     public IntegerProperty IdProperty() {
        return this.id;
    }

    public String getUserName() {
        return this.UserName.get();
    }

    public void setUserName(String UserName) {
        this.UserName.set(UserName);
    }
    public StringProperty UserNameProperty() {
        return this.UserName;
    }
    public String getUserFullName() {
        return this.UserFullName.get();
    }

    public void setUserFullName(String UserName) {
        this.UserFullName.set(UserName);
    }
    public StringProperty UserFullNameProperty() {
        return this.UserFullName;
    }

    public String getPassword() {
        return this.password.get();
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
    public StringProperty PasswordProperty() {
        return this.password;
    }

    public UsersType getUsersType() {
        return this.usersType;
    }

    public void setUsersType(UsersType usersType) {
        this.usersType = usersType;
    }

}
