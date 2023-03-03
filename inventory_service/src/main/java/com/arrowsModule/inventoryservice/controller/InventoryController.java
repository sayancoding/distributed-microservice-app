package com.arrowsModule.inventoryservice.controller;

import com.arrowsModule.inventoryservice.dto.InventoryResponse;
import com.arrowsModule.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin("*")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inStock")
    private List<InventoryResponse> isCheck(@RequestParam List<String> skuCode){
        return inventoryService.isStock(skuCode);
    }
    @GetMapping("/{skuCode}")
    private InventoryResponse findInventory(@PathVariable String skuCode){
        return inventoryService.findBySkuCode(skuCode);
    }
}
