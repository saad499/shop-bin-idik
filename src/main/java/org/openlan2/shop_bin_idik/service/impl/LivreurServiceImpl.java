package org.openlan2.shop_bin_idik.service.impl;

import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.constant.Role;
import org.openlan2.shop_bin_idik.entities.Livreur;
import org.openlan2.shop_bin_idik.exception.DuplicateFieldException;
import org.openlan2.shop_bin_idik.repository.LivreurRepository;
import org.openlan2.shop_bin_idik.repository.UserRepository;
import org.openlan2.shop_bin_idik.service.LivreurService;
import org.openlan2.shop_bin_idik.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LivreurServiceImpl implements LivreurService {

    private final LivreurRepository livreurRepository;
    private final UserRepository userRepository;

    @Override
    public Livreur saveLivreur(Livreur livreur) {
        List<String> duplicateFields = new ArrayList<>();

        // Check for nomComplet and prenomLivreur combination
        if (livreur.getNomComplet() != null && livreur.getPrenomLivreur() != null) {
            if (livreurRepository.existsByNomCompletAndPrenomLivreur(livreur.getNomComplet(), livreur.getPrenomLivreur())) {
                duplicateFields.add("nom complet");
                duplicateFields.add("prénom livreur");
            }
        } else {
            // Check individual fields if combination not provided
            if (livreur.getNomComplet() != null && livreurRepository.existsByNomComplet(livreur.getNomComplet())) {
                duplicateFields.add("nom complet");
            }

            if (livreur.getPrenomLivreur() != null && livreurRepository.existsByPrenomLivreur(livreur.getPrenomLivreur())) {
                duplicateFields.add("prénom livreur");
            }
        }

        // Validate phone number
        if (livreur.getTelephone() != null && livreurRepository.existsByTelephone(livreur.getTelephone())) {
            duplicateFields.add("telephone");
        }

        // Validate numero permis
        if (livreur.getNumeroPermis() != null && livreurRepository.existsByNumeroPermis(livreur.getNumeroPermis())) {
            duplicateFields.add("numéro de permis");
        }

        // Validate username
        if (livreur.getUser().getUsername() != null && userRepository.existsByUsername(livreur.getUser().getUsername())) {
            duplicateFields.add("username");
        }

        // Throw exception if any duplicates found
        if (!duplicateFields.isEmpty()) {
            throw new DuplicateFieldException(duplicateFields);
        }

        // Hash the password before saving
        String hashedPassword = PasswordUtil.hashPassword(livreur.getUser().getPassword());
        livreur.getUser().setPassword(hashedPassword);

        // Set role and creation date for the user
        livreur.getUser().setRole(Role.LIVREUR);
        if (livreur.getUser().getDateCreated() == null) {
            livreur.getUser().setDateCreated(LocalDateTime.now());
        }

        return livreurRepository.save(livreur);
    }
}
