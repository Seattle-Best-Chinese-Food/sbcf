package com.example.backstage.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.backstage.entity.Item;

@Repository
public class JdbcItemRepository implements ItemRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    // row map to java object
    private RowMapper<Item> rowMapper = new RowMapper<Item>() {
        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item item = new Item();
            item.setId(rs.getInt("id"));
            item.setName(rs.getString("name"));
            item.setDescription(rs.getString("description"));
            item.setPublished(rs.getBoolean("published"));
            return item;
        }
    };

    // save item
    @Override
    public int save(Item item) {
        String sql = "INSERT INTO sbcf_item (id, name, description, published) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, item.getId(), item.getName(), item.getDescription(), item.isPublished());
    }

    // update item
    @Override
    public int update(Item item) {
        String sql = "UPDATE sbcf_item SET name = ?, description = ?, published = ? WHERE id = ?";
        return jdbcTemplate.update(sql, item.getName(), item.getDescription(), item.isPublished(), item.getId());
    }

    // find item by id
    @Override
    public Item findById(int id) {
        String sql = "SELECT * FROM sbcf_item WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    // delete item by id
    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM sbcf_item WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // find all items
    @Override
    public List<Item> findAll() {
        String sql = "SELECT * FROM sbcf_item";
        return jdbcTemplate.query(sql, rowMapper);
    }

    // find items by published
    @Override
    public List<Item> findByPublished(boolean published) {
        String sql = "SELECT * FROM sbcf_item WHERE published = ?";
        return jdbcTemplate.query(sql, rowMapper, published);
    }

    // find items by name
    @Override
    public List<Item> findByName(String name) {
        String sql = "SELECT * FROM sbcf_item WHERE name = ?";
        return jdbcTemplate.query(sql, rowMapper, name);
    }

    // delete all items
    @Override
    public int deleteAll() {
        String sql = "DELETE FROM sbcf_item";
        return jdbcTemplate.update(sql);
    }

}
