package org.openlan2.shop_bin_idik.service;

import org.openlan2.shop_bin_idik.dto.CommercantDto;

import java.util.List;

public interface CommercantService {
    List<CommercantDto> getAllCommercants();
    List<CommercantDto> searchMagazinByName(String nameMagazin);
}
