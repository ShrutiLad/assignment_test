package com.ravionics.service;

import com.ravionics.model.Logintest;
import com.ravionics.repository.ValidUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service("validUserService")
public class ValidUserServiceImpl implements ValidUserService {
    @Autowired
    private ValidUserRepository validUserRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Logintest findValidUserByUsername(String username)
    {
        return validUserRepository.findByUsername(username);
    }

    @Override
    public void saveValidUser(Logintest validUser) {
        validUser.setPassword(bCryptPasswordEncoder.encode(validUser.getPassword()));
        validUserRepository.save(validUser);
    }

}