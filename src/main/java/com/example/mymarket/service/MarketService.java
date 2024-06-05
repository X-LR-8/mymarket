package com.example.mymarket.service;
import com.example.mymarket.dto.ItemDtoList;
import com.example.mymarket.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.val;
import com.example.mymarket.dto.ItemDto;
import com.example.mymarket.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MarketService {
    private static final String PHOTO_PREFIX = "-photo";
    private final ItemRepository repo;
    private final DtoConverter converter;
    @Autowired
    public MarketService(ItemRepository repo, DtoConverter converter){
        this.repo=repo;
        this.converter=converter;
    }
    public ItemDto getItem(String name){
        val item=repo.findByName(name).orElseThrow(() -> new EntityNotFoundException("Item was not found"));
        return converter.convert(item);
    }
    public boolean checkItem(String name) {
        try {
            val item = repo.findByName(name)
                    .orElseThrow(() -> new Exception("Item not found with name: " + name));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
    public ItemDtoList getItemsByPage(int pageNum, String sortinf) {
        String[] sortarr=sortinf.split("_");
        String itemType=sortarr[0];
        String sortType=sortarr[1];
        Sort sort=null;
        if(itemType.equals("Price")){
            if(sortType.equals("Ascending")){
                sort=Sort.by("price").ascending();
            }else if(sortType.equals("Descending")){
                sort=Sort.by("price").descending();
            }
        }
        if(itemType.equals("Date")){
            if(sortType.equals("Ascending")){
                sort=Sort.by("submittionTime").ascending();
            }else if(sortType.equals("Descending")){
                sort=Sort.by("submittionTime").descending();
            }
        }
        Pageable pageable = PageRequest.of(pageNum, 6,sort);
        val item = repo.findAll(pageable).stream().toList();
        return converter.convert(item);
    }
    public ItemDto addItem(ItemDto itemDto){
        repo.save(converter.convert(itemDto));
        return itemDto;
    }
    public Long getQuantity(){
        return repo.count();
    }
    public ItemDto addPhotoToItem(String name) {
        val item = repo.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Item entity with name " + name + " not found"));
        item.setPhoto(name+PHOTO_PREFIX);
        return converter.convert(repo.save(item));
    }
}
