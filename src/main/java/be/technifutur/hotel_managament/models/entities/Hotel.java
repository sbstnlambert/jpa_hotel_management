package be.technifutur.hotel_managament.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "hotel")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(
            nullable = false,
            columnDefinition = "INT NOT NULL CHECK (star_number BETWEEN 1 AND 5",
            insertable = false)
    private byte starNumber;

    @Column(nullable = false, name = "hotel_name")
    private String name;

    // Adresse
    // Disons que nous voulons lui passer une valeur par défaut ("N/A")
    // Afin de le remplacer par sa valeur par défaut qui est NULL jusqu'à présent
    @Column(nullable = false, columnDefinition = "VARCHAR(200) DEFAULT 'N/A'")
    private String address = "N/A";

    @OneToOne
    private Manager owner;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms = new ArrayList<>();
}