package org.openlan2.shop_bin_idik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SizeDto {
    private Long id;
    private String sizeName;
}
