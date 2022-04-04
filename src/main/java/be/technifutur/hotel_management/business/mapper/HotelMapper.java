package be.technifutur.hotel_management.business.mapper;

import be.technifutur.hotel_management.models.dto.HotelDTO;
import be.technifutur.hotel_management.models.entities.Hotel;
import be.technifutur.hotel_management.models.entities.Manager;
import be.technifutur.hotel_management.models.entities.Room;
import be.technifutur.hotel_management.models.forms.HotelForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelMapper {

    public HotelDTO entityToDTO(Hotel entity) {
        if (entity == null)
            return null;

        // Récupération des données de l'entité Manager
        Manager managerEntity = entity.getOwner();

        // Récupération des données de l'entité Room
        List<Room> roomEntity = entity.getRooms();

        // Instanciation d'un nouveau ManagerDTO
        HotelDTO.ManagerDTO manager =
                managerEntity == null ? null :
                        new HotelDTO.ManagerDTO(
                                managerEntity.getId(),
                                managerEntity.getName(),
                                managerEntity.getSurname()
                        );

        // Instanciation d'un nouveau RoomDTO
        List<HotelDTO.RoomDTO> rooms =
                roomEntity == null ? null :
                                roomEntity.stream()
                                    .map(
                                            room -> new HotelDTO.RoomDTO(
                                            room.getId(),
                                            room.isHasTelevision(),
                                            room.isHasKitchen(),
                                            room.isHasMiniBar(),
                                            room.getPrice())
                                    )
                                    .toList();

        return HotelDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .rooms(rooms)
                .manager(manager)
                .build();
    }

    public Hotel formToEntity(HotelForm form) {
        if (form == null)
            return null;

        return Hotel.builder()
                .name(form.getName())
                .address(form.getAddress())
                .starNumber(form.getStarNumber())
                .build();
    }

}
