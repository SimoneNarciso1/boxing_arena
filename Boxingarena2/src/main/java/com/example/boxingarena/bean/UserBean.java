package com.example.boxingarena.bean;

public class UserBean {


    private int id;
    private String username;
    private String password;
    private String role;

    private String checkPassword;



    public UserBean(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public UserBean() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCheckPassword( String checkPassword){
        this.checkPassword = checkPassword;
    }

    public String getCheckPassword() {
        return checkPassword;
    }


    public void checkField(String username, String password) throws IllegalArgumentException {
        if (username.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Please fill all the fields");
        }
    }


    public static void checkIfPassIsEqual(String password, String checkPassword) throws IllegalArgumentException {
        if (!password.equals(checkPassword)) {
            throw new IllegalArgumentException("Passwords do not match");
        }
    }


}
