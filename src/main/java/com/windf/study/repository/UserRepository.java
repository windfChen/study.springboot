package com.windf.study.repository;

import com.windf.study.domain.User;

import java.util.Collection;

public interface UserRepository {
    boolean save(User user);

    Collection<User> findAll();
}
