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
        DeliveryRequestRepository deliveryRequestRepository
    ) {
        return args -> {
            // Categories
            /*Categorie cat1 = categorieRepository.save(Categorie.builder()
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

            // Clients
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

            // Products (7 products)
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

            Product product4 = productRepository.save(Product.builder()
                    .nom("Jeans Classique")
                    .description("Jean denim confortable")
                    .prix(59.99)
                    .stock(80)
                    .status(StatusProduct.ACTIF)
                    .isActiveProduct(true)
                    .dateCreated(LocalDateTime.now())
                    .categorie(cat3)
                    .build());

            Product product5 = productRepository.save(Product.builder()
                    .nom("Veste en Cuir")
                    .description("Veste en cuir véritable")
                    .prix(199.99)
                    .stock(40)
                    .status(StatusProduct.ACTIF)
                    .isActiveProduct(true)
                    .dateCreated(LocalDateTime.now())
                    .categorie(cat3)
                    .build());

            Product product6 = productRepository.save(Product.builder()
                    .nom("Casque Audio Pro")
                    .description("Casque sans fil haute qualité")
                    .prix(149.99)
                    .stock(60)
                    .status(StatusProduct.ACTIF)
                    .isActiveProduct(true)
                    .dateCreated(LocalDateTime.now())
                    .categorie(cat2)
                    .build());

            Product product7 = productRepository.save(Product.builder()
                    .nom("Montre Connectée")
                    .description("Montre intelligente multifonctions")
                    .prix(249.99)
                    .stock(45)
                    .status(StatusProduct.ACTIF)
                    .isActiveProduct(true)
                    .dateCreated(LocalDateTime.now())
                    .categorie(cat2)
                    .build());

            // Sizes (for clothing products)
            Size sizeS = sizeRepository.save(Size.builder().sizeName("S").product(product1).build());
            Size sizeM = sizeRepository.save(Size.builder().sizeName("M").product(product1).build());
            Size sizeL = sizeRepository.save(Size.builder().sizeName("L").product(product1).build());
            Size sizeXL = sizeRepository.save(Size.builder().sizeName("XL").product(product1).build());

            Size sizeS4 = sizeRepository.save(Size.builder().sizeName("S").product(product4).build());
            Size sizeM4 = sizeRepository.save(Size.builder().sizeName("M").product(product4).build());
            Size sizeL4 = sizeRepository.save(Size.builder().sizeName("L").product(product4).build());

            Size sizeS5 = sizeRepository.save(Size.builder().sizeName("S").product(product5).build());
            Size sizeM5 = sizeRepository.save(Size.builder().sizeName("M").product(product5).build());
            Size sizeL5 = sizeRepository.save(Size.builder().sizeName("L").product(product5).build());

            // Colors
            Color colorRed = colorRepository.save(Color.builder().colorName("Red").colorCode("#FF0000").product(product1).build());
            Color colorBlue = colorRepository.save(Color.builder().colorName("Blue").colorCode("#0000FF").product(product1).build());
            Color colorBlack = colorRepository.save(Color.builder().colorName("Black").colorCode("#000000").product(product1).build());

            Color colorWhite = colorRepository.save(Color.builder().colorName("White").colorCode("#FFFFFF").product(product4).build());
            Color colorGray = colorRepository.save(Color.builder().colorName("Gray").colorCode("#808080").product(product4).build());
            Color colorNavy = colorRepository.save(Color.builder().colorName("Navy").colorCode("#000080").product(product4).build());

            Color colorBrown = colorRepository.save(Color.builder().colorName("Brown").colorCode("#8B4513").product(product5).build());
            Color colorBlack5 = colorRepository.save(Color.builder().colorName("Black").colorCode("#000000").product(product5).build());
            Color colorTan = colorRepository.save(Color.builder().colorName("Tan").colorCode("#D2B48C").product(product5).build());

            // Images
            imageRepository.save(Image.builder().imageUrl("https://example.com/images/tshirt-1.jpg").product(product1).build());
            imageRepository.save(Image.builder().imageUrl("https://example.com/images/smartphone.jpg").product(product2).build());
            imageRepository.save(Image.builder().imageUrl("https://example.com/images/laptop.jpg").product(product3).build());
            imageRepository.save(Image.builder().imageUrl("https://example.com/images/jeans.jpg").product(product4).build());
            imageRepository.save(Image.builder().imageUrl("https://example.com/images/jacket.jpg").product(product5).build());
            imageRepository.save(Image.builder().imageUrl("https://example.com/images/headphones.jpg").product(product6).build());
            imageRepository.save(Image.builder().imageUrl("https://example.com/images/watch.jpg").product(product7).build());

            // Orders (7 orders with different statuses)
            Order order1 = orderRepository.save(Order.builder()
                    .client(client1)
                    .orderDate(LocalDateTime.now())
                    .totalPrice(269.97)
                    .status(StatusOrder.EN_TRAITEMENT)
                    .shippingAddress("123 Rue de la Paix, 75001 Paris")
                    .billingAddress("123 Rue de la Paix, 75001 Paris")
                    .build());

            Order order2 = orderRepository.save(Order.builder()
                    .client(client2)
                    .orderDate(LocalDateTime.now().minusDays(1))
                    .totalPrice(539.97)
                    .status(StatusOrder.PREPAREE)
                    .shippingAddress("456 Avenue des Champs, 69001 Lyon")
                    .billingAddress("456 Avenue des Champs, 69001 Lyon")
                    .build());

            Order order3 = orderRepository.save(Order.builder()
                    .client(client1)
                    .orderDate(LocalDateTime.now().minusDays(2))
                    .totalPrice(649.97)
                    .status(StatusOrder.EXPEDIEE)
                    .shippingAddress("123 Rue de la Paix, 75001 Paris")
                    .billingAddress("123 Rue de la Paix, 75001 Paris")
                    .build());

            Order order4 = orderRepository.save(Order.builder()
                    .client(client2)
                    .orderDate(LocalDateTime.now().minusDays(3))
                    .totalPrice(449.97)
                    .status(StatusOrder.LIVREE)
                    .shippingAddress("456 Avenue des Champs, 69001 Lyon")
                    .billingAddress("456 Avenue des Champs, 69001 Lyon")
                    .build());

            Order order5 = orderRepository.save(Order.builder()
                    .client(client1)
                    .orderDate(LocalDateTime.now().minusDays(4))
                    .totalPrice(749.96)
                    .status(StatusOrder.EN_TRAITEMENT)
                    .shippingAddress("123 Rue de la Paix, 75001 Paris")
                    .billingAddress("123 Rue de la Paix, 75001 Paris")
                    .build());

            Order order6 = orderRepository.save(Order.builder()
                    .client(client2)
                    .orderDate(LocalDateTime.now().minusDays(5))
                    .totalPrice(1199.97)
                    .status(StatusOrder.PREPAREE)
                    .shippingAddress("456 Avenue des Champs, 69001 Lyon")
                    .billingAddress("456 Avenue des Champs, 69001 Lyon")
                    .build());

            Order order7 = orderRepository.save(Order.builder()
                    .client(client1)
                    .orderDate(LocalDateTime.now().minusDays(6))
                    .totalPrice(359.97)
                    .status(StatusOrder.EXPEDIEE)
                    .shippingAddress("123 Rue de la Paix, 75001 Paris")
                    .billingAddress("123 Rue de la Paix, 75001 Paris")
                    .build());

            // Order Items - Order 1 (3 items with different colors and sizes)
            orderItemRepository.save(OrderItem.builder()
                    .order(order1).product(product1).size(sizeS).color(colorRed)
                    .prix(29.99).quantite(1).total(29.99).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order1).product(product1).size(sizeM).color(colorBlue)
                    .prix(29.99).quantite(2).total(59.98).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order1).product(product4).size(sizeL4).color(colorNavy)
                    .prix(59.99).quantite(3).total(179.97).build());

            // Order Items - Order 2
            orderItemRepository.save(OrderItem.builder()
                    .order(order2).product(product4).size(sizeM4).color(colorWhite)
                    .prix(59.99).quantite(2).total(119.98).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order2).product(product5).size(sizeL5).color(colorBrown)
                    .prix(199.99).quantite(1).total(199.99).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order2).product(product6).size(null).color(null)
                    .prix(149.99).quantite(1).total(149.99).build());

            // Order Items - Order 3
            orderItemRepository.save(OrderItem.builder()
                    .order(order3).product(product5).size(sizeM5).color(colorBlack5)
                    .prix(199.99).quantite(1).total(199.99).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order3).product(product6).size(null).color(null)
                    .prix(149.99).quantite(2).total(299.98).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order3).product(product7).size(null).color(null)
                    .prix(249.99).quantite(1).total(249.99).build());

            // Order Items - Order 4
            orderItemRepository.save(OrderItem.builder()
                    .order(order4).product(product1).size(sizeL).color(colorBlack)
                    .prix(29.99).quantite(3).total(89.97).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order4).product(product4).size(sizeS4).color(colorGray)
                    .prix(59.99).quantite(2).total(119.98).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order4).product(product7).size(null).color(null)
                    .prix(249.99).quantite(1).total(249.99).build());

            // Order Items - Order 5
            orderItemRepository.save(OrderItem.builder()
                    .order(order5).product(product2).size(null).color(null)
                    .prix(799.99).quantite(1).total(799.99).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order5).product(product1).size(sizeXL).color(colorRed)
                    .prix(29.99).quantite(2).total(59.98).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order5).product(product4).size(sizeL4).color(colorWhite)
                    .prix(59.99).quantite(1).total(59.99).build());

            // Order Items - Order 6
            orderItemRepository.save(OrderItem.builder()
                    .order(order6).product(product3).size(null).color(null)
                    .prix(1299.99).quantite(1).total(1299.99).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order6).product(product5).size(sizeS5).color(colorTan)
                    .prix(199.99).quantite(2).total(399.98).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order6).product(product6).size(null).color(null)
                    .prix(149.99).quantite(1).total(149.99).build());

            // Order Items - Order 7
            orderItemRepository.save(OrderItem.builder()
                    .order(order7).product(product7).size(null).color(null)
                    .prix(249.99).quantite(2).total(499.98).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order7).product(product1).size(sizeM).color(colorBlue)
                    .prix(29.99).quantite(1).total(29.99).build());
            orderItemRepository.save(OrderItem.builder()
                    .order(order7).product(product4).size(sizeM4).color(colorNavy)
                    .prix(59.99).quantite(2).total(119.98).build());
                    

            // Create Livreur users
            User userLivreur1 = userRepository.save(User.builder()
                    .username("livreur1")
                    .email("ahmed.benali@delivery.com")
                    .password("password123")
                    .role(Role.LIVREUR)
                    .dateCreated(LocalDateTime.now())
                    .build());

            User userLivreur2 = userRepository.save(User.builder()
                    .username("livreur2")
                    .email("fatima.zahra@delivery.com")
                    .password("password123")
                    .role(Role.LIVREUR)
                    .dateCreated(LocalDateTime.now())
                    .build());

            User userLivreur3 = userRepository.save(User.builder()
                    .username("livreur3")
                    .email("mohammed.alami@delivery.com")
                    .password("password123")
                    .role(Role.LIVREUR)
                    .dateCreated(LocalDateTime.now())
                    .build());

            // Create Livreurs
            Livreur livreur1 = livreurRepository.save(Livreur.builder()
                    .user(userLivreur1)
                    .nomComplet("Ahmed Benali")
                    .telephone("0661234567")
                    .nomCommerce("Express Delivery")
                    .typeVehicule(TypeVehicule.MOTO)
                    .numImmatriculation("12345-A-67")
                    .photoConducteur("https://example.com/photos/ahmed-benali.jpg")
                    .photoVehiculeRecto("https://example.com/vehicles/12345-A-67-recto.jpg")
                    .photoVehiculeVerso("https://example.com/vehicles/12345-A-67-verso.jpg")
                    .build());

            Livreur livreur2 = livreurRepository.save(Livreur.builder()
                    .user(userLivreur2)
                    .nomComplet("Fatima Zahra")
                    .telephone("0662345678")
                    .nomCommerce("Fast Delivery")
                    .typeVehicule(TypeVehicule.VOITURE)
                    .numImmatriculation("54321-B-89")
                    .photoConducteur("https://example.com/photos/fatima-zahra.jpg")
                    .photoVehiculeRecto("https://example.com/vehicles/54321-B-89-recto.jpg")
                    .photoVehiculeVerso("https://example.com/vehicles/54321-B-89-verso.jpg")
                    .build());

            Livreur livreur3 = livreurRepository.save(Livreur.builder()
                    .user(userLivreur3)
                    .nomComplet("Mohammed Alami")
                    .telephone("0663456789")
                    .nomCommerce("Quick Delivery")
                    .typeVehicule(TypeVehicule.MOTO)
                    .numImmatriculation("98765-C-43")
                    .photoConducteur("https://example.com/photos/mohammed-alami.jpg")
                    .photoVehiculeRecto("https://example.com/vehicles/98765-C-43-recto.jpg")
                    .photoVehiculeVerso("https://example.com/vehicles/98765-C-43-verso.jpg")
                    .build());

            // Deliveries (with Livreur relationships)
            deliveryRepository.save(Delivery.builder()
                    .livreur(livreur1)
                    .note(4.8)
                    .delai("20-30 min")
                    .status("DISPONIBLE")
                    .build());

            deliveryRepository.save(Delivery.builder()
                    .livreur(livreur2)
                    .note(4.5)
                    .delai("30-45 min")
                    .status("EN_LIVRAISON")
                    .build());

            deliveryRepository.save(Delivery.builder()
                    .livreur(livreur3)
                    .note(4.9)
                    .delai("15-25 min")
                    .status("DISPONIBLE")
                    .build());

            deliveryRepository.save(Delivery.builder()
                    .livreur(livreur1)
                    .note(4.7)
                    .delai("25-35 min")
                    .status("DISPONIBLE")
                    .build());

            deliveryRepository.save(Delivery.builder()
                    .livreur(livreur2)
                    .note(4.6)
                    .delai("35-50 min")
                    .status("INDISPONIBLE")
                    .build());

            deliveryRepository.save(Delivery.builder()
                    .livreur(livreur3)
                    .note(4.8)
                    .delai("20-30 min")
                    .status("EN_LIVRAISON")
                    .build());

            deliveryRepository.save(Delivery.builder()
                    .livreur(livreur1)
                    .note(5.0)
                    .delai("15-20 min")
                    .status("DISPONIBLE")
                    .build());*/

            // Example: create a DeliveryRequest for orderId=1 and deliveryId=1
            // (Assume order1 and delivery1 are already created above)
            Delivery delivery1 = deliveryRepository.findAll().get(0); // or use the variable if available
            Order order1 = orderRepository.findAll().get(0); // or use the variable if available

            deliveryRequestRepository.save(DeliveryRequest.builder()
                .delivery(delivery1)
                .order(order1)
                .pickupAddress("123 Rue de la Paix, 75001 Paris")
                .deliveryAddress("456 Avenue des Champs, 69001 Lyon")
                .contactMethod(org.openlan2.shop_bin_idik.constant.ContactMethod.MESSAGE)
                .estimatedPrice(15.5)
                .status(org.openlan2.shop_bin_idik.constant.RequestStatus.PENDING)
                .message("Request sent successfully")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());
        };
    }

}
