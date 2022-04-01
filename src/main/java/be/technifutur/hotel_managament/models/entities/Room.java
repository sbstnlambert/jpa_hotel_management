package be.technifutur.hotel_managament.models.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomNumber", nullable = false)
    private Long id;

    @Column(nullable = true)
    private boolean hasTelevision;

    @Column(nullable = false)
    private boolean hasKitchen;

    @Column(nullable = true)
    private boolean hasMiniBar;

    @Column(nullable = false)
    private int price;

    @OneToOne(mappedBy = "room")
    private Hotel hotel;

    @ManyToMany
    private Client client;
}