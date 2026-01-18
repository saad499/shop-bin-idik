package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.constant.Role;
import org.openlan2.shop_bin_idik.dto.CommercantDto;
import org.openlan2.shop_bin_idik.entities.Commercant;
import org.openlan2.shop_bin_idik.exception.DuplicateFieldException;
import org.openlan2.shop_bin_idik.repository.CommercantRepository;
import org.openlan2.shop_bin_idik.repository.UserRepository;
import org.openlan2.shop_bin_idik.service.CommercantService;
import org.openlan2.shop_bin_idik.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommercantServiceImpl implements CommercantService {

    private final CommercantRepository commercantRepository;
    private final UserRepository userRepository;

    @Override
    public List<CommercantDto> getAllCommercants() {
        List<Commercant> commercants = commercantRepository.findAll();
        return commercants.stream()
                .map(commercant -> CommercantDto.builder()
                        .logo(commercant.getLogo())
                        .nameMagazin(commercant.getBusinessName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CommercantDto> searchMagazinByName(String nameMagazin) {
        List<Commercant> commercants = commercantRepository.findByBusinessNameContainingIgnoreCase(nameMagazin);
        return commercants.stream()
                .map(commercant -> CommercantDto.builder()
                        .logo(commercant.getLogo())
                        .nameMagazin(commercant.getBusinessName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Commercant saveCommercant(Commercant commercant) {
        List<String> duplicateFields = new ArrayList<>();

        // Validate business name
        if (commercant.getBusinessName() != null && commercantRepository.existsByBusinessName(commercant.getBusinessName())) {
            duplicateFields.add("nom commercial");
        }

        // Validate phone number - assuming you have this field in Commercant
        if (commercant.getTelephone() != null && commercantRepository.existsByTelephone(commercant.getTelephone())) {
            duplicateFields.add("telephone");
        }

        // Validate username
        if (commercant.getUser().getUsername() != null && userRepository.existsByUsername(commercant.getUser().getUsername())) {
            duplicateFields.add("username");
        }

        // Throw exception if any duplicates found
        if (!duplicateFields.isEmpty()) {
            throw new DuplicateFieldException(duplicateFields);
        }

        // Hash the password before saving
        String hashedPassword = PasswordUtil.hashPassword(commercant.getUser().getPassword());
        commercant.getUser().setPassword(hashedPassword);

        // Set role and creation date for the user
        commercant.getUser().setRole(Role.COMMERCANT);
        if (commercant.getUser().getDateCreated() == null) {
            commercant.getUser().setDateCreated(LocalDateTime.now());
        }

        return commercantRepository.save(commercant);
    }
}
