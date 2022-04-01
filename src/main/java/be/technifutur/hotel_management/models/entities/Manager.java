package be.technifutur.hotel_management.models.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
@Entity
@Table(name = "manager")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false, name = "manager_name", length = 50)
    private String name;

    @Column(nullable = false, name = "manager_surname", length = 50)
    private String surname;

    @Column(nullable = true, updatable = false, columnDefinition = "DATE")
    private LocalDate beginCareerOn;

    @OneToOne(mappedBy = "owner")
    private Hotel manage;

}