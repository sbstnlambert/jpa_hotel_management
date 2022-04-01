package be.technifutur.hotel_management.repositories;

import be.technifutur.hotel_management.models.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
