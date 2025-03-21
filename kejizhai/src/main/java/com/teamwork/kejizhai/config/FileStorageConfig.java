package com.teamwork.kejizhai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Configuration
public class FileStorageConfig {

    @Value("${file.upload-dir:d:/workplace/web/kejizhai/kejizhai/src/main/resources/resources/uploads}")
    private String uploadDir;
    
    private Path fileStoragePath;
    
    @Bean
    public void init() {
        this.fileStoragePath = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("无法创建文件上传目录", e);
        }
    }
    
    public String storeFile(MultipartFile file) {
        try {
            // 生成文件名
            String originalFileName = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFileName != null && originalFileName.contains(".")) {
                fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            }
            
            // 生成唯一文件名
            String newFileName = UUID.randomUUID().toString() + fileExtension;
            
            // 计算文件的MD5哈希值
            String fileHash = calculateMD5(file);
            
            // 保存文件
            Path targetLocation = this.fileStoragePath.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            return newFileName + ":" + fileHash;
        } catch (IOException | NoSuchAlgorithmException ex) {
            throw new RuntimeException("无法存储文件", ex);
        }
    }
    
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStoragePath.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("文件未找到: " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("文件未找到: " + fileName, ex);
        }
    }
    
    // 计算文件的MD5哈希值
    private String calculateMD5(MultipartFile file) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(file.getBytes());
        byte[] digest = md.digest();
        
        // 将字节数组转换为十六进制字符串
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }
}