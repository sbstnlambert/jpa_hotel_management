package be.technifutur.hotel_management.models.entities;

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
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_number", nullable = false)
    private Long id;

    @Column(nullable = false)
    private boolean hasTelevision;

    @Column(nullable = false)
    private boolean hasKitchen;

    @Column(nullable = false, name = "has_minibar")
    private boolean hasMiniBar;

    // precision = nombre de chiffres après la virgule du float
    @Column(nullable = false, precision = 2)
    private float price;

    @ManyToOne
    private Hotel hotel;

    @ManyToMany
    private List<Client> clients = new ArrayList<>();
}