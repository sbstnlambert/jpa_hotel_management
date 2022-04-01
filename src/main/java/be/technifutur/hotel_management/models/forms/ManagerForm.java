package be.technifutur.hotel_management.models.forms;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ManagerForm {

    private String name;
    private String surname;
    private LocalDate beginCareerOn;

}
