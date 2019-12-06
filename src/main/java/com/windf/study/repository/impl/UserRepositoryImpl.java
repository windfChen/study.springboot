package com.windf.study.repository.impl;

import com.windf.study.domain.User;
import com.windf.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    private final PlatformTransactionManager platformTransactionManager;

    private final AtomicLong atomicLong = new AtomicLong();

    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate,
                              PlatformTransactionManager platformTransactionManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Override
    public boolean save(User user) {
        jdbcTemplate.execute("INSERT INTO t_user(name) VALUES (?);",
                new PreparedStatementCallback<Boolean>() {

                    @Nullable
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement preparedStatement) throws SQLException, DataAccessException {
                        preparedStatement.setString(1, user.getName());
                        return preparedStatement.executeUpdate() > 0;
                    }
                });

        return true;
    }

    @Override

    public Collection<User> findAll() {
        List<User> users = jdbcTemplate.query(
                "select id, name from t_user",
                new BeanPropertyRowMapper<User>(User.class));
        return users;
    }
}
