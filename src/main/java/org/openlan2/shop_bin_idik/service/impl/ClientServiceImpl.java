package org.openlan2.shop_bin_idik.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openlan2.shop_bin_idik.constant.Role;
import org.openlan2.shop_bin_idik.entities.Client;
import org.openlan2.shop_bin_idik.exception.DuplicateFieldException;
import org.openlan2.shop_bin_idik.repository.ClientRepository;
import org.openlan2.shop_bin_idik.repository.UserRepository;
import org.openlan2.shop_bin_idik.service.ClientService;
import org.openlan2.shop_bin_idik.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final UserRepository userRepository;

    @Override
    public Client saveClient(Client client) {
        List<String> duplicateFields = new ArrayList<>();
        
        // Check for nom and prenom combination
        if (client.getNom() != null && client.getPrenom() != null) {
            if (clientRepository.existsByNomAndPrenom(client.getNom(), client.getPrenom())) {
                duplicateFields.add("nom");
                duplicateFields.add("prénom");
            }
        } else {
            // Check individual fields if combination not provided
            if (client.getNom() != null && clientRepository.existsByNom(client.getNom())) {
                duplicateFields.add("nom");
            }
            
            if (client.getPrenom() != null && clientRepository.existsByPrenom(client.getPrenom())) {
                duplicateFields.add("prénom");
            }
        }
        
        // Validate telephone
        if (client.getTelephone() != null && clientRepository.existsByTelephone(client.getTelephone())) {
            duplicateFields.add("telephone");
        }
        
        // Validate username
        if (client.getUser().getUsername() != null && userRepository.existsByUsername(client.getUser().getUsername())) {
            duplicateFields.add("username");
        }
        
        // Throw exception if any duplicates found
        if (!duplicateFields.isEmpty()) {
            throw new DuplicateFieldException(duplicateFields);
        }

        // Hash the password before saving
        String hashedPassword = PasswordUtil.hashPassword(client.getUser().getPassword());
        client.getUser().setPassword(hashedPassword);
        
        // Set role and creation date for the user
        client.getUser().setRole(Role.CLIENT);
        if (client.getUser().getDateCreated() == null) {
            client.getUser().setDateCreated(LocalDateTime.now());
        }
        
        return clientRepository.save(client);
    }
}
