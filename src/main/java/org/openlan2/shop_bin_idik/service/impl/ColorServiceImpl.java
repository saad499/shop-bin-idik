package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.repository.ColorRepository;
import org.openlan2.shop_bin_idik.service.ColorService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public void deleteColor(Long id) {
        if (!colorRepository.existsById(id)) {
            throw new RuntimeException("Color not found with id: " + id);
        }
        colorRepository.deleteById(id);
    }
}
