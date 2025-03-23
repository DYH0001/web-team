package com.teamwork.kejizhai.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teamwork.kejizhai.bean.shoppingcart;
import com.teamwork.kejizhai.services.shoppingcartService;

@RestController
@RequestMapping("/api/shoppingcart")
public class shoppingcartController{
    @Autowired
    private shoppingcartService shoppingcartService;

    /**
     * 添加商品到购物车
     * @param shoppingcart 购物车对象
     * @return 添加结果
     */
    @PostMapping("/add")
    public ResponseEntity<?> addToCart(@RequestBody shoppingcart shoppingcart) {
        try {
            boolean result = shoppingcartService.addshoppingcart(shoppingcart);
            if (result) {
                return ResponseEntity.ok().body("商品已成功添加到购物车");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加购物车失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("添加购物车失败: " + e.getMessage());
        }
    }

    /**
     * 获取用户购物车列表
     * @param uid 用户ID
     * @return 购物车列表
     */
    @GetMapping("/user/{uid}")
    public ResponseEntity<?> getUserCart(@PathVariable("uid") int uid) {
        try {
            List<shoppingcart> cartItems = shoppingcartService.getshoppingcart(uid);
            return ResponseEntity.ok().body(cartItems);
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("获取购物车失败: " + e.getMessage());
        }
    }

    /**
     * 删除购物车项目
     * @param scid 购物车项目ID
     * @return 删除结果
     */
    @DeleteMapping("/delete/{scid}")
    public ResponseEntity<?> deleteCartItem(@PathVariable("scid") int scid) {
        try {
            boolean result = shoppingcartService.deleteShoppingcart(scid);
            if (result) {
                return ResponseEntity.ok().body("购物车项目已成功删除");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("购物车项目不存在或删除失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("删除购物车项目失败: " + e.getMessage());
        }
    }

    /**
     * 计算购物车总价值
     * @param uid 用户ID
     * @param nums 商品数量（可选参数，目前未使用）
     * @return 购物车总价值
     */
    @GetMapping("/value/{uid}/{nums}")
    public ResponseEntity<?> getCartValue(@PathVariable("uid") int uid, @PathVariable("nums") int nums) {
        double totalValue = shoppingcartService.cartValue(uid, nums);
        return ResponseEntity.ok().body(totalValue);
    }
}
