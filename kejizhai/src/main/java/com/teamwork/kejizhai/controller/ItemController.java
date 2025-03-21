package com.teamwork.kejizhai.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;  // 添加这个导入用于Map.of()

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;  // 添加这个导入
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;  // 添加这个导入

import com.teamwork.kejizhai.bean.Items;
import com.teamwork.kejizhai.services.ItemService;
import com.teamwork.kejizhai.config.FileStorageConfig;

@RestController  // 标记这是一个REST风格的Controller
@RequestMapping("/api/items")  
@CrossOrigin(origins = "*") // 添加跨域支持
public class ItemController {

    @Autowired  
    private ItemService itemService;
    @GetMapping("/{iid}")  
    public ResponseEntity<?> getItem(@PathVariable String iid) {
        try {
            Items items = itemService.getItemById(iid);
            if (items==null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(items);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("获取商品信息失败: " + e.getMessage());
        }
    }
    @GetMapping  // 处理 GET /api/items 请求
    public ResponseEntity<?> getAllItems() {
        try {
            List<Items> items = itemService.getAllItems();
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
    public ResponseEntity<?> deleteItem(@PathVariable String iid) {
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
    @GetMapping("/{Iname}")
    public ResponseEntity<?> getItemByName(@PathVariable String Iname) {
        try {
            Items items = itemService.getItemByName(Iname);
            return ResponseEntity.ok(items);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("获取商品信息失败: " + e.getMessage());
        }
    }
 
    @PutMapping("/{iid}/status")  // 处理 PUT /api/items/{iid}/status 请求
    public ResponseEntity<?> setItemStatus(@PathVariable String iid) {
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

    @Autowired
    private FileStorageConfig fileStorageConfig;

    @PostMapping("/upload-image")
    public ResponseEntity<?> uploadItemImage(@RequestParam("file") MultipartFile file, 
                                             @RequestParam("iid") String iid) {
        try {
            // 存储文件并获取文件名和哈希值
            String fileInfo = fileStorageConfig.storeFile(file);
            String[] parts = fileInfo.split(":");
            String fileName = parts[0];
            String fileHash = parts[1];
            
            // 更新商品图片信息
            Items item = itemService.getItemById(iid);
            if (item != null) {
                item.setIimage("/uploads/" + fileName);
                item.setImageHash(fileHash); // 假设Items类中有imageHash字段
                itemService.updateItem(item);
                
                return ResponseEntity.ok(Map.of(
                    "fileName", fileName,
                    "fileHash", fileHash,
                    "url", "/uploads/" + fileName
                ));
            } else {
                return ResponseEntity.badRequest().body("商品不存在");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("上传图片失败: " + e.getMessage());
        }
    }
}
