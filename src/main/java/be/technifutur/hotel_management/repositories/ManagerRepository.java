package be.technifutur.hotel_management.repositories;

import be.technifutur.hotel_management.models.entities.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
