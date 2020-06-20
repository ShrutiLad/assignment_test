package com.ravionics.repository;

import com.ravionics.model.Logintest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository("validUserRepository")
public interface ValidUserRepository extends JpaRepository<Logintest, Long> {

    Logintest findByUsername(String username);

}
