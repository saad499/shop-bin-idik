package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.CommercantDto;
import org.openlan2.shop_bin_idik.entities.Commercant;
import org.openlan2.shop_bin_idik.repository.CommercantRepository;
import org.openlan2.shop_bin_idik.service.CommercantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommercantServiceImpl implements CommercantService {

    private final CommercantRepository commercantRepository;

    @Override
    public List<CommercantDto> getAllCommercants() {
        List<Commercant> commercants = commercantRepository.findAll();
        return commercants.stream()
                .map(commercant -> CommercantDto.builder()
                        .logo(commercant.getLogo())
                        .nameMagazin(commercant.getNameMagazin())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CommercantDto> searchMagazinByName(String nameMagazin) {
        List<Commercant> commercants = commercantRepository.findByNameMagazinContainingIgnoreCase(nameMagazin);
        return commercants.stream()
                .map(commercant -> CommercantDto.builder()
                        .logo(commercant.getLogo())
                        .nameMagazin(commercant.getNameMagazin())
                        .build())
                .collect(Collectors.toList());
    }
}
