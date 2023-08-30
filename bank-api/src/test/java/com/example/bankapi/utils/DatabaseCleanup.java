package com.example.bankapi.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Profile("test")
@Service
public class DatabaseCleanup implements InitializingBean {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<String> tableNames;

    @Override
    public void afterPropertiesSet() {
        tableNames = jdbcTemplate.query("SHOW TABLES", (resultSet, rowNumber) -> resultSet.getString(1));
    }

    @Transactional
    public void execute() {
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY FALSE");
        for (String tableName : tableNames) {
            jdbcTemplate.execute("TRUNCATE TABLE " + tableName);
        }
        jdbcTemplate.execute("SET REFERENTIAL_INTEGRITY TRUE");
    }
}
