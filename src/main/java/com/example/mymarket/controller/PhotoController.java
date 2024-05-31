package com.example.mymarket.controller;

import com.example.mymarket.service.MarketService;
import com.example.mymarket.service.PhotoService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("/photo")
public class PhotoController {

    private final MarketService marketService;
    private final PhotoService photoService;

    @Autowired
    public PhotoController(MarketService marketService, PhotoService photoService) {
        this.marketService = marketService;
        this.photoService = photoService;
    }
    @PostMapping
    public ResponseEntity<?> uploadPhoto(@RequestParam("photo") MultipartFile photo,
                                         @RequestParam("name") String name) {
        try {
            val itemDto = marketService.addPhotoToItem(name);
            photoService.storePhoto(photo, itemDto.getPhoto());
            System.out.println("28xazze movida photocontroller");
            return ResponseEntity.noContent().build();
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllPhotos() {
        try {
            val photos = photoService.getAllPhotos();
            return ResponseEntity.ok(photos);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
