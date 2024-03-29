package be.technifutur.hotel_management.data.repositories;

import be.technifutur.hotel_management.models.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
