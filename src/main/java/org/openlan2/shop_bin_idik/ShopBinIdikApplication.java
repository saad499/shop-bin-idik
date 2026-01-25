package org.openlan2.shop_bin_idik;

import org.openlan2.shop_bin_idik.constant.Role;
import org.openlan2.shop_bin_idik.constant.StatusOrder;
import org.openlan2.shop_bin_idik.constant.StatusProduct;
import org.openlan2.shop_bin_idik.constant.TypeVehicule;
import org.openlan2.shop_bin_idik.entities.*;
import org.openlan2.shop_bin_idik.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import org.openlan2.shop_bin_idik.util.PasswordUtil;

@SpringBootApplication
public class ShopBinIdikApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopBinIdikApplication.class, args);
    }

    //@Bean
    CommandLineRunner runner(
        CategorieRepository categorieRepository,
        ClientRepository clientRepository,
        ProductRepository productRepository,
        OrderRepository orderRepository,
        OrderItemRepository orderItemRepository,
        SizeRepository sizeRepository,
        ColorRepository colorRepository,
        ImageRepository imageRepository,
        UserRepository userRepository,
        DeliveryRepository deliveryRepository,
        LivreurRepository livreurRepository,
        DeliveryRequestRepository deliveryRequestRepository,
        CommercantRepository commercantRepository
    ) {
        return args -> {
            // Create Commercant users
            User userCommercant1 = userRepository.save(User.builder()
                    .username("commercant1")
                    .email("commercant1@business.com")
                    .password(PasswordUtil.hashPassword("password123"))
                    .role(Role.COMMERCANT)
                    .dateCreated(LocalDateTime.now())
                    .build());

            User userCommercant2 = userRepository.save(User.builder()
                    .username("commercant2")
                    .email("commercant2@business.com")
                    .password(PasswordUtil.hashPassword("password123"))
                    .role(Role.COMMERCANT)
                    .dateCreated(LocalDateTime.now())
                    .build());

            User userCommercant3 = userRepository.save(User.builder()
                    .username("commercant3")
                    .email("commercant3@business.com")
                    .password(PasswordUtil.hashPassword("password123"))
                    .role(Role.COMMERCANT)
                    .dateCreated(LocalDateTime.now())
                    .build());

            // Create Commercants
            commercantRepository.save(Commercant.builder()
                    .user(userCommercant1)
                    .nom("Alami")
                    .prenom("Hassan")
                    .telephone("0661234567")
                    .nomCommerce("Tech Store Morocco")
                    .categorie("Electronique")
                    .adresse("123 Boulevard Mohammed V")
                    .ville("Casablanca")
                    .logo("https://example.com/logos/tech-store.png")
                    .description("Magasin spécialisé dans les produits électroniques")
                    .numImmatriculationFiscale("12345678")
                    .registreCommerce("RC123456")
                    .documentAutre("Certificat d'exploitation")
                    .build());

            commercantRepository.save(Commercant.builder()
                    .user(userCommercant2)
                    .nom("Bennani")
                    .prenom("Fatima")
                    .telephone("0662345678")
                    .nomCommerce("Fashion Boutique")
                    .categorie("Vêtements")
                    .adresse("456 Avenue des FAR")
                    .ville("Rabat")
                    .logo("https://example.com/logos/fashion-boutique.png")
                    .description("Boutique de mode tendance pour homme et femme")
                    .numImmatriculationFiscale("87654321")
                    .registreCommerce("RC654321")
                    .documentAutre("Licence commerciale")
                    .build());

            commercantRepository.save(Commercant.builder()
                    .user(userCommercant3)
                    .nom("Tazi")
                    .prenom("Omar")
                    .telephone("0663456789")
                    .nomCommerce("Fresh Market")
                    .categorie("Alimentation")
                    .adresse("789 Rue de Fès")
                    .ville("Marrakech")
                    .logo("https://example.com/logos/fresh-market.png")
                    .description("Produits frais et bio de qualité")
                    .numImmatriculationFiscale("11223344")
                    .registreCommerce("RC112233")
                    .documentAutre("Certificat sanitaire")
                    .build());
        };
    }

}
