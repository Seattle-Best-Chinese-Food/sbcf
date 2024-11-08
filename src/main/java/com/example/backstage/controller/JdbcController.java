package com.example.backstage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class JdbcController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/insert")
    public String insert(String type, String name) {
        String sql = "insert into jdbc_test(ds_type, ds_name) values(?, ?)";
        jdbcTemplate.update(sql, type, name);
        return "success insert";
    }
}
