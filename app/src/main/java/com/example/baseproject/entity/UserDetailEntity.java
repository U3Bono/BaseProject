package com.example.baseproject.entity;

public class UserDetailEntity {
    /**
     * user_id : 871169340
     * user_account : null
     * user_phone : 12345678910
     * user_ic : null
     */

    private Integer user_id;
    private String user_account;
    private String user_phone;
    private String user_ic;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_ic() {
        return user_ic;
    }

    public void setUser_ic(String user_ic) {
        this.user_ic = user_ic;
    }
}
