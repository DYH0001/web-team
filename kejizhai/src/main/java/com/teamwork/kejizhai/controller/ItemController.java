package com.teamwork.kejizhai.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.services.ItemService;

@RestController  // 标记这是一个REST风格的Controller
@RequestMapping("/api/items")  // 设置该Controller的基础URL路径
public class ItemController {

    @Autowired  // 自动注入ItemService
    private ItemService itemService;
    @GetMapping("/{iid}")  // 处理 GET /api/items/{iid} 请求
    public ResponseEntity<?> getItem(@PathVariable String iid) {
        try {
            List<Items> items = itemService.getItems(iid);
            if (items.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(items.get(0));
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("获取商品信息失败: " + e.getMessage());
        }
    }
    @GetMapping  // 处理 GET /api/items 请求
    public ResponseEntity<?> getAllItems() {
        try {
            List<Items> items = itemService.getItems();
            return ResponseEntity.ok(items);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("获取商品列表失败: " + e.getMessage());
        }
    }
    @PostMapping  // 处理 POST /api/items 请求
    public ResponseEntity<?> addItem(@RequestBody Items item) {
        try {
            boolean success = itemService.addItem(item);
            if (success) {
                return ResponseEntity.ok("商品添加成功");
            } else {
                return ResponseEntity.badRequest().body("商品添加失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("添加商品失败: " + e.getMessage());
        }
    }

    @PutMapping  // 处理 PUT /api/items 请求
    public ResponseEntity<?> updateItem(@RequestBody Items item) {
        try {
            boolean success = itemService.updateItem(item);
            if (success) {
                return ResponseEntity.ok("商品更新成功");
            } else {
                return ResponseEntity.badRequest().body("商品更新失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("更新商品失败: " + e.getMessage());
        }
    }

     @DeleteMapping("/{iid}")  // 处理 DELETE /api/items/{iid} 请求
    public ResponseEntity<?> deleteItem(@PathVariable int iid) {
        try {
            boolean success = itemService.deleteItem(iid);
            if (success) {
                return ResponseEntity.ok("商品删除成功");
            } else {
                return ResponseEntity.badRequest().body("商品删除失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("删除商品失败: " + e.getMessage());
        }
    }
 
    @PutMapping("/{iid}/status")  // 处理 PUT /api/items/{iid}/status 请求
    public ResponseEntity<?> setItemStatus(@PathVariable int iid) {
        try {
            int result = itemService.setIstatus(iid);
            if (result > 0) {
                return ResponseEntity.ok("商品状态更新成功");
            } else {
                return ResponseEntity.badRequest().body("商品状态更新失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("更新商品状态失败: " + e.getMessage());
        }
    }
}
