package org.openlan2.shop_bin_idik.service;

import org.openlan2.shop_bin_idik.dto.SendReclamationRequest;
import org.openlan2.shop_bin_idik.dto.SupportDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SupportService {
    SupportDto sendReclamation(SendReclamationRequest request);
    List<SupportDto> getAllReclamations();
    Page<SupportDto> getAllReclamations(Pageable pageable);
    SupportDto getReclamationById(Long id);
    List<SupportDto> getReclamationsByType(String typeReclamation);
}
