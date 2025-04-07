package edu.orgname.microservices.order.service;

import edu.orgname.microservices.order.client.InventoryClient;
import edu.orgname.microservices.order.dto.OrderRequest;
import edu.orgname.microservices.order.model.Order;
import edu.orgname.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {
        boolean isProductInStock = inventoryClient.isInStock(
                orderRequest.skuCode(), orderRequest.quantity());

        if (!isProductInStock) {
            throw new RuntimeException(
                    "Product with skuCode " + orderRequest.skuCode() + " is not in Stock!");
        }

        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());

        orderRepository.save(order);
    }
}
