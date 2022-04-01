package be.technifutur.hotel_management.business.mapper;

import be.technifutur.hotel_management.models.dto.ManagerDTO;
import be.technifutur.hotel_management.models.entities.Hotel;
import be.technifutur.hotel_management.models.entities.Manager;
import be.technifutur.hotel_management.models.forms.ManagerForm;
import org.springframework.stereotype.Service;

@Service
public class ManagerMapper {

    public ManagerDTO entityToDTO(Manager entity) {

        if (entity == null)
            return null;

        Hotel hotelEntity = entity.getManage();
        ManagerDTO.HotelDTO hotel =
                hotelEntity == null ? null :
                        new ManagerDTO.HotelDTO(
                                hotelEntity.getId(),
                                hotelEntity.getName()
                        );

        return ManagerDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .beginCareerOn(entity.getBeginCareerOn())
                .hotel(hotel)
                .build();

    }

    public Manager formToEntity(ManagerForm form) {
        if (form == null)
            return null;

    return Manager.builder()
            .name(form.getName())
            .surname(form.getSurname())
            .beginCareerOn(form.getBeginCareerOn())
            .build();
    }

}
