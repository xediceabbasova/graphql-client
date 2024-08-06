package com.company.catalogservice.client;

import com.company.catalogservice.dto.Item;
import com.company.catalogservice.dto.ItemRequestDTO;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventoryClient {

    private final HttpGraphQlClient graphQlClient;

    public InventoryClient(HttpGraphQlClient graphQlClient) {
        this.graphQlClient = graphQlClient;
    }

    public List<Item> viewProducts() {

        String graphQLQuery = "query GetProducts {\n" +
                "    getProducts {\n" +
                "        name\n" +
                "        price\n" +
                "    }\n" +
                "}";

        return graphQlClient.document(graphQLQuery)
                .retrieve("getProducts")
                .toEntityList(Item.class).block();
    }

    public List<Item> viewProductsByCategory(String category) {

        String graphQLQuery = String.format("query GetProductsByCategory {\n" +
                "    getProductsByCategory(category: \"%s\") {\n" +
                "        name\n" +
                "        category\n" +
                "        price\n" +
                "        stock\n" +
                "    }\n" +
                "}\n", category);

        return graphQlClient.document(graphQLQuery)
                .retrieve("getProductsByCategory")
                .toEntityList(Item.class).block();
    }

    public Item receiveNewShipment(ItemRequestDTO itemRequest) {

        String graphQlQuery = String.format("mutation ReceiveNewShipment {\n" +
                "    receiveNewShipment(id: \"%s\", quantity: %d) {\n" +
                "        name\n" +
                "        price\n" +
                "        stock\n" +
                "    }\n" +
                "}\n", itemRequest.id(), itemRequest.qty());

        return graphQlClient.document(graphQlQuery)
                .retrieve("receiveNewShipment")
                .toEntity(Item.class).block();
    }
}
