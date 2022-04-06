package be.technifutur.hotel_management.models.forms;

import be.technifutur.hotel_management.models.entities.Manager;
import lombok.Data;

@Data
public class HotelForm {

    private byte starNumber;
    private String name;
    private String address;
    private Manager manager;

}
