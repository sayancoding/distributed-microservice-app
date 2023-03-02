package com.arrowsModule.inventoryservice;

import com.arrowsModule.inventoryservice.model.Inventory;
import com.arrowsModule.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
//		return args ->{
//			Inventory inventory_1 = Inventory.builder()
//					.skuCode("mobile_iphone12")
//					.quantity(20)
//					.build();
//			Inventory inventory_2 = Inventory.builder()
//					.skuCode("laptop_lenovo_neo")
//					.quantity(0)
//					.build();
//			inventoryRepository.save(inventory_1);
//			inventoryRepository.save(inventory_2);
//		};
//	}
}
