package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.repository.SizeRepository;
import org.openlan2.shop_bin_idik.service.SizeService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    @Override
    public void deleteSize(Long id) {
        if (!sizeRepository.existsById(id)) {
            throw new RuntimeException("Size not found with id: " + id);
        }
        sizeRepository.deleteById(id);
    }
}
