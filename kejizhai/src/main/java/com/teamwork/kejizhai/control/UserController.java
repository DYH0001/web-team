package com.teamwork.kejizhai.control;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teamwork.kejizhai.bean.Address;
import com.teamwork.kejizhai.bean.Users;
import com.teamwork.kejizhai.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user 登录用户信息
     * @return 登录结果
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        try {
            boolean success = userService.login(user);
            if (success) {
                return ResponseEntity.ok("登录成功");
            } else {
                return ResponseEntity.badRequest().body("用户名或密码错误");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("登录失败: " + e.getMessage());
        }
    }

    /**
     * 用户注册
     * @param user 注册用户信息
     *  @return 注册结果
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user) {
        try {
            Users registeredUser = userService.signin(user);
            if (registeredUser != null) {
                return ResponseEntity.ok(registeredUser);
            } else {
                return ResponseEntity.badRequest().body("注册失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("注册失败: " + e.getMessage());
        }
    }

    /**
     * 修改密码
     * @param user 用户信息
     * @param newPassword 新密码
     * @return 修改结果
     */
    @PutMapping("/{uid}/password")
    public ResponseEntity<?> changePassword(@RequestBody Users user, @RequestParam String newPassword) {
        try {
            boolean success = userService.changepsw(user, newPassword);
            if (success) {
                return ResponseEntity.ok("密码修改成功");
            } else {
                return ResponseEntity.badRequest().body("密码修改失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("密码修改失败: " + e.getMessage());
        }
    }

    /**
     * 更新用户信息
     * @param user 更新的用户信息
     * @return 更新结果
     */
    @PutMapping("/{uid}")
    public ResponseEntity<?> updateUserInfo(@RequestBody Users user) {
        try {
            boolean success = userService.updateuserinfo(user);
            if (success) {
                return ResponseEntity.ok("用户信息更新成功");
            } else {
                return ResponseEntity.badRequest().body("用户信息更新失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("更新失败: " + e.getMessage());
        }
    }

    /**
     * 更新用户地址
     * @param uid 用户ID
     * @param address 新地址信息
     * @return 更新结果
     */
    @PutMapping("/{uid}/address")
    public ResponseEntity<?> updateAddress(@PathVariable String uid, @RequestBody Address address) {
        try {
            Users user = userService.getUserById(uid);
            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            boolean success = userService.updateAddress(user, address);
            if (success) {
                return ResponseEntity.ok("地址更新成功");
            } else {
                return ResponseEntity.badRequest().body("地址更新失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("更新地址失败: " + e.getMessage());
        }
    }

    /**
     * 获取用户信息
     * @param uid 用户ID
     * @return 用户信息
     */
    @GetMapping("/{uid}")
    public ResponseEntity<?> getUserInfo(@PathVariable String uid) {
        try {
            Users user = userService.getUserById(uid);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("获取用户信息失败: " + e.getMessage());
        }
    }

    /**
     * 获取所有用户列表
     * @return 用户列表
     */
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        try {
            List<Users> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("获取用户列表失败: " + e.getMessage());
        }
    }

    /**
     * 删除用户
     * @param uid 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/{uid}")
    public ResponseEntity<?> deleteUser(@PathVariable String uid) {
        try {
            boolean success = userService.deleteUser(uid);
            if (success) {
                return ResponseEntity.ok("用户删除成功");
            } else {
                return ResponseEntity.badRequest().body("用户删除失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("删除用户失败: " + e.getMessage());
        }
    }

    /**
     * 验证手机号
     * @param uid 用户ID
     * @param phone 手机号
     * @return 验证结果
     */
    @PostMapping("/{uid}/verify-phone")
    public ResponseEntity<?> verifyPhone(@PathVariable String uid, @RequestParam String phone) {
        try {
            boolean success = userService.verifyPhone(uid, phone);
            if (success) {
                return ResponseEntity.ok("手机号验证成功");
            } else {
                return ResponseEntity.badRequest().body("手机号验证失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("手机号验证失败: " + e.getMessage());
        }
    }

    /**
     * 验证邮箱
     * @param uid 用户ID
     * @param email 邮箱
     * @return 验证结果
     */
    @PostMapping("/{uid}/verify-email")
    public ResponseEntity<?> verifyEmail(@PathVariable String uid, @RequestParam String email) {
        try {
            boolean success = userService.verifyEmail(uid, email);
            if (success) {
                return ResponseEntity.ok("邮箱验证成功");
            } else {
                return ResponseEntity.badRequest().body("邮箱验证失败");
            }
        } catch (SQLException e) {
            return ResponseEntity.badRequest().body("邮箱验证失败: " + e.getMessage());
        }
    }
}
