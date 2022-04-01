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
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, name = "client_name")
    private String name;

    @Column(nullable = false, name = "client_surname")
    private String surname;

    @ManyToMany(mappedBy = "clients")
    private List<Room> rooms = new ArrayList<>();
}