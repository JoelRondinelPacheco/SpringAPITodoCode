package com.joel.spring.dtos.sales;

import com.joel.spring.dtos.products.ProductInfoDTO;
import com.joel.spring.dtos.users.UserPersonalInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SaleInfoDTO {
    private String saleId;
    private Double totalPrice;
    private UserPersonalInfoDTO client;
    private List<ProductInfoDTO> products;

    public SaleInfoDTO(String saleId, Double totalPrice, UserPersonalInfoDTO client) {
        this.saleId = saleId;
        this.totalPrice = totalPrice;
        this.client = client;
    }
}
