package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.dto.SendReclamationRequest;
import org.openlan2.shop_bin_idik.dto.SupportDto;
import org.openlan2.shop_bin_idik.entities.Support;
import org.openlan2.shop_bin_idik.repository.SupportRepository;
import org.openlan2.shop_bin_idik.service.SupportService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupportServiceImpl implements SupportService {

    private final SupportRepository supportRepository;

    @Override
    public SupportDto sendReclamation(SendReclamationRequest request) {
        if (request.getTypeReclamation() == null || request.getTypeReclamation().trim().isEmpty()) {
            throw new RuntimeException("Type of reclamation is required");
        }
        
        if (request.getMessage() == null || request.getMessage().trim().isEmpty()) {
            throw new RuntimeException("Message is required");
        }

        Support support = Support.builder()
            .typeReclamation(request.getTypeReclamation())
            .message(request.getMessage())
            .dateCreated(LocalDateTime.now())
            .build();

        Support savedSupport = supportRepository.save(support);
        return mapToDto(savedSupport);
    }

    @Override
    public List<SupportDto> getAllReclamations() {
        List<Support> supports = supportRepository.findAllByOrderByDateCreatedDesc();
        return supports.stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    @Override
    public Page<SupportDto> getAllReclamations(Pageable pageable) {
        Page<Support> supports = supportRepository.findAllByOrderByDateCreatedDesc(pageable);
        return supports.map(this::mapToDto);
    }

    @Override
    public SupportDto getReclamationById(Long id) {
        Support support = supportRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Reclamation not found"));
        return mapToDto(support);
    }

    @Override
    public List<SupportDto> getReclamationsByType(String typeReclamation) {
        List<Support> supports = supportRepository.findByTypeReclamationContainingIgnoreCase(typeReclamation);
        return supports.stream()
            .map(this::mapToDto)
            .collect(Collectors.toList());
    }

    private SupportDto mapToDto(Support support) {
        return SupportDto.builder()
            .id(support.getId())
            .typeReclamation(support.getTypeReclamation())
            .message(support.getMessage())
            .dateCreated(support.getDateCreated())
            .build();
    }
}
