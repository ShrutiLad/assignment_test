package com.ravionics.service;

import com.ravionics.model.Logintest;

public interface ValidUserService {

    public Logintest findValidUserByUsername(String username);

    public void saveValidUser(Logintest validUser);
}