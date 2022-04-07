package be.technifutur.hotel_management.data.repositories;

import be.technifutur.hotel_management.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // le username fait référence à notre variable indiquée dans l'entité User
    Optional<User> findByUsername(String username);

}
