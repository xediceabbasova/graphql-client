package com.company.catalogservice.service;

import com.company.catalogservice.client.InventoryClient;
import com.company.catalogservice.dto.Item;
import com.company.catalogservice.dto.ItemRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogService {

    private final InventoryClient inventoryClient;

    public CatalogService(InventoryClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    public List<Item> viewProducts() {
        return inventoryClient.viewProducts();
    }

    public List<Item> viewProductsByCategory(String category) {
        return inventoryClient.viewProductsByCategory(category);
    }

    public Item receiveNewShipment(ItemRequestDTO itemRequest) {
        return inventoryClient.receiveNewShipment(itemRequest);
    }
}
