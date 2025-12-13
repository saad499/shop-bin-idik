package org.openlan2.shop_bin_idik;

import org.openlan2.shop_bin_idik.constant.Role;
import org.openlan2.shop_bin_idik.constant.StatusOrder;
import org.openlan2.shop_bin_idik.constant.StatusProduct;
import org.openlan2.shop_bin_idik.entities.*;
import org.openlan2.shop_bin_idik.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class ShopBinIdikApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopBinIdikApplication.class, args);
    }

    //@Bean
    CommandLineRunner runner(CategorieRepository categorieRepository,
                             ClientRepository clientRepository,
                             ProductRepository productRepository,
                             OrderRepository orderRepository,
                             OrderItemRepository orderItemRepository,
                             SizeRepository sizeRepository,
                             ColorRepository colorRepository,
                             ImageRepository imageRepository,
                             UserRepository userRepository) {
        return args -> {
            // Categories
            Categorie cat1 = categorieRepository.save(Categorie.builder()
                    .nom("Alimentation")
                    .description("Produits alimentaires")
                    .isActiveCategory(true)
                    .build());

            Categorie cat2 = categorieRepository.save(Categorie.builder()
                    .nom("Electronique")
                    .description("Appareils électroniques")
                    .isActiveCategory(true)
                    .build());

            Categorie cat3 = categorieRepository.save(Categorie.builder()
                    .nom("Vêtements")
                    .description("Mode et vêtements")
                    .isActiveCategory(true)
                    .build());
            // Users
            User user1 = userRepository.save(User.builder()
                    .username("client1")
                    .email("client1@example.com")
                    .password("password123")
                    .role(Role.CLIENT)
                    .dateCreated(LocalDateTime.now())
                    .build());
            User user2 = userRepository.save(User.builder()
                    .username("client2")
                    .email("client2@example.com")
                    .password("password123")
                    .role(Role.CLIENT)
                    .dateCreated(LocalDateTime.now())
                    .build());

            // Client
            Client client1 = clientRepository.save(Client.builder()
                    .user(user1)
                    .nom("Dupont")
                    .prenom("Jean")
                    .email("jean.dupont@example.com")
                    .telephone("0612345678")
                    .adresse("123 Rue de la Paix, 75001 Paris")
                    .build());

            Client client2 = clientRepository.save(Client.builder()
                    .user(user2)
                    .nom("Martin")
                    .prenom("Marie")
                    .email("marie.martin@example.com")
                    .telephone("0698765432")
                    .adresse("456 Avenue des Champs, 69001 Lyon")
                    .build());

            // Products
            Product product1 = productRepository.save(Product.builder()
                    .nom("T-Shirt Premium")
                    .description("T-shirt en coton de haute qualité")
                    .prix(29.99)
                    .stock(100)
                    .status(StatusProduct.ACTIF)
                    .isActiveProduct(true)
                    .dateCreated(LocalDateTime.now())
                    .categorie(cat3)
                    .build());

            Product product2 = productRepository.save(Product.builder()
                    .nom("Smartphone X Pro")
                    .description("Smartphone dernière génération")
                    .prix(799.99)
                    .stock(50)
                    .status(StatusProduct.ACTIF)
                    .isActiveProduct(true)
                    .dateCreated(LocalDateTime.now())
                    .categorie(cat2)
                    .build());

            Product product3 = productRepository.save(Product.builder()
                    .nom("Laptop UltraBook")
                    .description("Ordinateur portable léger et puissant")
                    .prix(1299.99)
                    .stock(30)
                    .status(StatusProduct.ACTIF)
                    .isActiveProduct(true)
                    .dateCreated(LocalDateTime.now())
                    .categorie(cat2)
                    .build());

            // Sizes for Product 1 (T-Shirt)
            Size size1 = sizeRepository.save(Size.builder()
                    .sizeName("S")
                    .product(product1)
                    .build());

            Size size2 = sizeRepository.save(Size.builder()
                    .sizeName("M")
                    .product(product1)
                    .build());

            Size size3 = sizeRepository.save(Size.builder()
                    .sizeName("L")
                    .product(product1)
                    .build());

            // Colors for Product 1
            Color color1 = colorRepository.save(Color.builder()
                    .colorName("Red")
                    .colorCode("#FF0000")
                    .product(product1)
                    .build());

            Color color2 = colorRepository.save(Color.builder()
                    .colorName("Blue")
                    .colorCode("#0000FF")
                    .product(product1)
                    .build());

            Color color3 = colorRepository.save(Color.builder()
                    .colorName("Black")
                    .colorCode("#000000")
                    .product(product1)
                    .build());

            // Images for Products
            imageRepository.save(Image.builder()
                    .imageUrl("https://example.com/images/tshirt-red.jpg")
                    .product(product1)
                    .build());

            imageRepository.save(Image.builder()
                    .imageUrl("https://example.com/images/tshirt-blue.jpg")
                    .product(product1)
                    .build());

            imageRepository.save(Image.builder()
                    .imageUrl("https://example.com/images/smartphone-x.jpg")
                    .product(product2)
                    .build());

            imageRepository.save(Image.builder()
                    .imageUrl("https://example.com/images/laptop-ultra.jpg")
                    .product(product3)
                    .build());

            // Orders
            Order order1 = orderRepository.save(Order.builder()
                    .client(client1)
                    .orderDate(LocalDateTime.now())
                    .totalPrice(89.97)
                    .status(StatusOrder.EN_TRAITEMENT)
                    .shippingAddress("123 Rue de la Paix, 75001 Paris")
                    .billingAddress("123 Rue de la Paix, 75001 Paris")
                    .build());

            Order order2 = orderRepository.save(Order.builder()
                    .client(client2)
                    .orderDate(LocalDateTime.now().minusDays(1))
                    .totalPrice(2099.98)
                    .status(StatusOrder.LIVREE)
                    .shippingAddress("456 Avenue des Champs, 69001 Lyon")
                    .billingAddress("456 Avenue des Champs, 69001 Lyon")
                    .build());

            // Order Items for Order 1
            orderItemRepository.save(OrderItem.builder()
                    .order(order1)
                    .product(product1)
                    .size(size2)
                    .color(color1)
                    .prix(29.99)
                    .quantite(3)
                    .total(89.97)
                    .build());

            // Order Items for Order 2
            orderItemRepository.save(OrderItem.builder()
                    .order(order2)
                    .product(product2)
                    .size(null)
                    .color(null)
                    .prix(799.99)
                    .quantite(1)
                    .total(799.99)
                    .build());

            orderItemRepository.save(OrderItem.builder()
                    .order(order2)
                    .product(product3)
                    .size(null)
                    .color(null)
                    .prix(1299.99)
                    .quantite(1)
                    .total(1299.99)
                    .build());
        };
    }

}
