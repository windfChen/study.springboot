package com.windf.study.repository;

import com.windf.study.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final ConcurrentHashMap<Long, User> repository = new ConcurrentHashMap<>();

    private final AtomicLong atomicLong = new AtomicLong();

    public boolean save(User user) {
       user.setId(atomicLong.incrementAndGet());
       return repository.put(user.getId(), user) == null;
    }

    public Collection<User> findAll() {
        return repository.values();
    }
}
