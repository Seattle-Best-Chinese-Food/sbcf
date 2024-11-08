package com.example.backstage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.backstage.entity.Item;
import com.example.backstage.repository.ItemRepository;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "http://localhost:5174")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    // get all items
    @GetMapping("/all")
    public ResponseEntity<List<Item>> getAllItems() {
        System.out.println("getAllItems-----");
        try {
            List<Item> items = itemRepository.findAll();
            System.out.println("items: " + items);
            if (items.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.ok(items);
            }
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // get item by id
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") int id) {
        try {
            Item item = itemRepository.findById(id);
            if (item == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            System.out.println("error: " + e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // create item
    @PostMapping("/create")
    public ResponseEntity<String> createItem(@RequestBody Item item) {
        try {
            itemRepository.save(new Item(item.getId(), item.getName(), item.getDescription(), item.isPublished()));
            return ResponseEntity.ok("success create");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error create");
        }
    }

    // update item
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateItem(@PathVariable("id") int id, @RequestBody Item item) {
        try {
            Item existItem = itemRepository.findById(id);
            if (existItem == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("item not found");
            }
            existItem.setName(item.getName());
            existItem.setDescription(item.getDescription());
            existItem.setPublished(item.isPublished());
            itemRepository.update(existItem);
            return ResponseEntity.ok("success update");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error update");
        }
    }

    // delete item
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable("id") int id) {
        try {
            int result = itemRepository.deleteById(id);
            if (result == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("item not found");
            }
            return ResponseEntity.ok("success delete");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error delete");
        }
    }

    // delete all items
    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllItems() {
        try {
            int count = itemRepository.deleteAll();
            if (count == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("item not found");
            }
            return ResponseEntity.ok("success delete" + count + "items");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("failed delete all items");
        }
    }

    // find items by published
    @GetMapping("/published")
    public ResponseEntity<List<Item>> findByPublished(@RequestParam("published") boolean published) {
        try {
            List<Item> items = itemRepository.findByPublished(published);
            if (items.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.ok(items);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // find items by name
    @GetMapping("/name")
    public ResponseEntity<List<Item>> findByName(@RequestParam("name") String name) {
        try {
            List<Item> items = itemRepository.findByName(name);
            if (items.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
            } else {
                return ResponseEntity.ok(items);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
