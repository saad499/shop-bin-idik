package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderClientInfoDto {
    private Long numberOrder;
    private String firstname;
    private String lastname;
    private Double totalPrice;
    
}
