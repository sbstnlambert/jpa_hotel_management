package be.technifutur.hotel_management.models.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(
            columnDefinition = "INT NOT NULL CHECK (star_number BETWEEN 1 AND 5)")
    private byte starNumber;

    @Column(nullable = false, name = "hotel_name")
    private String name;

    // Adresse
    // Disons que nous voulons lui passer une valeur par défaut ("N/A")
    // Afin de le remplacer par sa valeur par défaut qui est NULL jusqu'à présent
    @Column(nullable = false, columnDefinition = "VARCHAR(200) DEFAULT 'N/A'")
    private String address = "N/A";

    @OneToOne
    // JointColumn Permet d'avoir le contrôle sur le nom de la ForeignKey
    @JoinColumn(
            name = "manager_id",
            foreignKey = @ForeignKey(name = "FK_HOTEL_MANAGER_ID"))
    private Manager owner;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms = new ArrayList<>();
}