package be.technifutur.hotel_management.utils;

import be.technifutur.hotel_management.models.entities.Hotel;
import be.technifutur.hotel_management.models.entities.Manager;
import be.technifutur.hotel_management.repositories.HotelRepository;
import be.technifutur.hotel_management.repositories.ManagerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseFiller implements InitializingBean {

    private final HotelRepository hotelRepository;
    private final ManagerRepository managerRepository;

    public DatabaseFiller (HotelRepository hotelRepository, ManagerRepository managerRepository) {
        this.hotelRepository = hotelRepository;
        this.managerRepository = managerRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Manager l = Manager.builder()
                .beginCareerOn(LocalDate.now())
                .surname("Lionel")
                .name("Delsupexhe")
                .build();
        managerRepository.save(l);

        Manager e = Manager.builder()
                .beginCareerOn(LocalDate.now().minusDays(2))
                .surname("Estelle")
                .name("Huynen")
                .build();
        managerRepository.save(e);

        Hotel hotel = Hotel.builder()
                .starNumber((byte)4)
                .name("Hilton")
                .address("8, Bervely Lane, California")
                .owner(e)
                .build();

        hotelRepository.save(hotel);
    }
}
