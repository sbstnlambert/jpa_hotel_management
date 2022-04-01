package be.technifutur.hotel_management.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ManagerDTO {

    private Long id;
    private String name;
    private String surname;
    private LocalDate beginCareerOn;
    private Long hotelId;
    private HotelDTO hotel;

    @Data
    @AllArgsConstructor
    public static class HotelDTO {
        private Long id;
        private String name;
    }


}
