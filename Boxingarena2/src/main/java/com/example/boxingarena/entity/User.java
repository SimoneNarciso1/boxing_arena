package com.example.boxingarena.entity;

public class User {

    //region properties
    private int idEntity;
    private String usernameEntity;
    private String passwordEntity;
    private String roleEntity;
    //endregion

    //region constructor

    public User(int idEntity, String usernameEntity, String roleEntity) {
        this.idEntity = idEntity;
        this.usernameEntity = usernameEntity;
        this.roleEntity = roleEntity;
    }

    public User(int idEntity, String usernameEntity) {
        this.idEntity = idEntity;
        this.usernameEntity = usernameEntity;
    }

    //endregion

    //region getter setter
    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }

    public String getUsernameEntity() {
        return usernameEntity;
    }

    public void setUsernameEntity(String usernameEntity) {
        this.usernameEntity = usernameEntity;
    }

    public String getPasswordEntity() {
        return passwordEntity;
    }

    public void setPasswordEntity(String passwordEntity) {
        this.passwordEntity = passwordEntity;
    }

    public String getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(String roleEntity) {
        this.roleEntity = roleEntity;
    }
    //endregion

}
