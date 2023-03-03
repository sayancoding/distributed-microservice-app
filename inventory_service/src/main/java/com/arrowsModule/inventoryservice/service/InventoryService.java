package com.arrowsModule.inventoryservice.service;

import com.arrowsModule.inventoryservice.dto.InventoryRequest;
import com.arrowsModule.inventoryservice.dto.InventoryResponse;
import com.arrowsModule.inventoryservice.model.Inventory;
import com.arrowsModule.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public String saveInventory(InventoryRequest inventoryRequest){
        Inventory inventory = Inventory.builder()
                .skuCode(inventoryRequest.getSkuCode())
                .quantity(inventoryRequest.getQuantity())
                .build();
        inventoryRepository.save(inventory);
        return "inventory-data has updated";
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map( inventory ->
                InventoryResponse.builder().skuCode(inventory.getSkuCode()).isStock(inventory.getQuantity()>0)
                        .build()
                ).toList();
    }

    public InventoryResponse findBySkuCode(String skuCode){
        Inventory inventory = inventoryRepository.findBySkuCode(skuCode).get();
        if(inventory != null){
            return InventoryResponse.builder()
                    .skuCode(inventory.getSkuCode())
                    .isStock(inventory.getQuantity() > 0)
                    .build();
        }
        return null;
    }
}
