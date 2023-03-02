package com.arrowsModule.inventoryservice.controller;

import com.arrowsModule.inventoryservice.dto.InventoryResponse;
import com.arrowsModule.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/is/{skuCode}")
    private boolean isCheck(@PathVariable String skuCode){
        return inventoryService.isStock(skuCode);
    }
    @GetMapping("/{skuCode}")
    private InventoryResponse findInventory(@PathVariable String skuCode){
        return inventoryService.findBySkuCode(skuCode);
    }
}
