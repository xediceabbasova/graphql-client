package com.company.catalogservice.controller;

import com.company.catalogservice.dto.Item;
import com.company.catalogservice.dto.ItemRequestDTO;
import com.company.catalogservice.service.CatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/products")
    public List<Item> viewProducts() {
        return catalogService.viewProducts();
    }

    @GetMapping("/products/category")
    public List<Item> viewProductsByCategory(@RequestParam String category) {
        return catalogService.viewProductsByCategory(category);
    }

    @PostMapping("/shipment")
    public Item receiveNewShipment(@RequestBody ItemRequestDTO requestDTO) {
        return catalogService.receiveNewShipment(requestDTO);
    }
}
