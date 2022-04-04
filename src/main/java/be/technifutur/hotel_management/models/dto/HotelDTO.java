package be.technifutur.hotel_management.models.dto;

import be.technifutur.hotel_management.models.entities.Manager;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class HotelDTO {

    private Long id;
    private byte starNumber;
    private String name;
    private String address;
    private Long managerId;
    private ManagerDTO manager;
    private List<RoomDTO> rooms;

    @Data
    @AllArgsConstructor
    public static class ManagerDTO {
        private Long id;
        private String name;
        private String surname;
    }

    @Data
    @AllArgsConstructor
    public static class RoomDTO {
        private Long id;
        private boolean hasTelevision;
        private boolean hasKitchen;
        private boolean hasMinibar;
        private float price;
    }

}
