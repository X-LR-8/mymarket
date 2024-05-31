package com.example.mymarket.controller;

import com.example.mymarket.dto.ItemDto;
import com.example.mymarket.service.MarketService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.val;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/market")
public class MarketController {
    private final MarketService marketService;

    @Autowired
    public MarketController(MarketService marketService) {
        this.marketService = marketService;
    }

    @GetMapping("/{page}")
    @ResponseBody
    public ResponseEntity<?> getpageItems(@PathVariable("page") Integer oldpage){
        try {
            int page=oldpage-1;
            val list=marketService.getItemsByPage(page);
            return ResponseEntity.status(HttpStatus.OK).body(list);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getItem(@RequestParam("name") String name) {
        try {

            val res = marketService.getItem(name);
            System.out.println("movida getitem 41 xazi");
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/total")
    @ResponseBody
    public ResponseEntity<?> totalItem() {
        try {
            val total=marketService.getQuantity();
            return ResponseEntity.status(HttpStatus.OK).body(total);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/check")
    @ResponseBody
    public ResponseEntity<?> checkItem(@RequestParam("name") String name) {
        try {
            boolean result = marketService.checkItem(name);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping
    @ResponseBody
    public ResponseEntity<?> addItem(@RequestBody ItemDto itemDto){
        try {
            val res = marketService.addItem(itemDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(res);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
