package be.technifutur.hotel_management.data.repositories;

import be.technifutur.hotel_management.models.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
