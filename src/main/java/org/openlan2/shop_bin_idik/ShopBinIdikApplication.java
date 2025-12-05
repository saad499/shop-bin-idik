package org.openlan2.shop_bin_idik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.openlan2.shop_bin_idik.entities.Categorie;
import org.openlan2.shop_bin_idik.repository.CategorieRepository;

@SpringBootApplication
public class ShopBinIdikApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopBinIdikApplication.class, args);
    }

    //@Bean
    CommandLineRunner runner(CategorieRepository categorieRepository) {
        return args -> {
            categorieRepository.save(Categorie.builder().nom("Alimentation").description("Produits alimentaires").isActiveCategory(true).build());
            categorieRepository.save(Categorie.builder().nom("Electronique").description("Appareils électroniques").isActiveCategory(true).build());
            categorieRepository.save(Categorie.builder().nom("Vêtements").description("Mode et vêtements").isActiveCategory(false).build());
            categorieRepository.save(Categorie.builder().nom("Alimentation 2").description("Produits alimentaires 2").isActiveCategory(true).build());
            categorieRepository.save(Categorie.builder().nom("Electronique 2").description("Appareils électroniques 2").isActiveCategory(true).build());
            categorieRepository.save(Categorie.builder().nom("Vêtements 2").description("Mode et vêtements 2").isActiveCategory(false).build());
        };
    }

}
