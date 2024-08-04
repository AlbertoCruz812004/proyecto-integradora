package com.allstock.app.services;

public interface UserService {

    boolean findUserByUsername(String username, String password);
}
