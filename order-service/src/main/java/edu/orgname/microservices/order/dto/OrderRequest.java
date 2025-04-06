package edu.orgname.microservices.order.dto;

import java.math.BigDecimal;

public record OrderRequest(
        String id,
        String orderNumber,
        String skuCode,
        BigDecimal price,
        Integer quantity
        ) {
}
