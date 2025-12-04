package org.openlan2.shop_bin_idik.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "table_support")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String typeReclamation;
    private String message;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
