package com.example.backstage.repository;

import java.util.List;

import com.example.backstage.entity.Item;

public interface ItemRepository {
    // save item
    int save(Item item);
    // update item
    int update(Item item);
    // find item by id
    Item findById(int id);
    // delete item by id
    int deleteById(int id);
    // find all items
    List<Item> findAll();
    // find items by published
    List<Item> findByPublished(boolean published);
    // find items by name
    List<Item> findByName(String name);
    // delete all items
    int deleteAll();
}
