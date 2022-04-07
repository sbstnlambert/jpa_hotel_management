package be.technifutur.hotel_management.utils;

import be.technifutur.hotel_management.data.repositories.UserRepository;
import be.technifutur.hotel_management.models.entities.Hotel;
import be.technifutur.hotel_management.models.entities.Manager;
import be.technifutur.hotel_management.data.repositories.HotelRepository;
import be.technifutur.hotel_management.data.repositories.ManagerRepository;
import be.technifutur.hotel_management.models.entities.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseFiller implements InitializingBean {

    private final HotelRepository hotelRepository;
    private final ManagerRepository managerRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public DatabaseFiller(HotelRepository hotelRepository, ManagerRepository managerRepository, UserRepository userRepository, PasswordEncoder encoder) {
        this.hotelRepository = hotelRepository;
        this.managerRepository = managerRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setupHotelManager();

        User uChristopheC = new User();
        uChristopheC.setUsername("ChristopheC");
        uChristopheC.setPassword(encoder.encode("cc-password"));

        userRepository.save(uChristopheC);
    }

    private void setupHotelManager() {
        Manager l = Manager.builder()
                .beginCareerOn(LocalDate.now())
                .surname("Lionel")
                .name("Delsupexhe")
                .build();
        managerRepository.save(l);

        Hotel vanDerValk = Hotel.builder()
                .starNumber((byte)4)
                .name("Van Der Valk Sélys")
                .address("Rue du Mont St Martin 9/11, 4000 Liège")
                .owner(l)
                .build();
        hotelRepository.save(vanDerValk);

        Manager e = Manager.builder()
                .beginCareerOn(LocalDate.now().minusDays(2))
                .surname("Estelle")
                .name("Huynen")
                .build();
        managerRepository.save(e);

        Hotel hilton = Hotel.builder()
                .starNumber((byte)5)
                .name("Hilton")
                .address("8, Bervely Lane, California")
                .owner(e)
                .build();
        hotelRepository.save(hilton);

        Manager m = Manager.builder()
                .beginCareerOn(LocalDate.now().minusDays(6))
                .surname("Manon")
                .name("Kerrels")
                .build();
        managerRepository.save(m);

        Hotel ibis = Hotel.builder()
                .starNumber((byte)2)
                .name("Ibis Hotel")
                .address("Rue du Premier Lanciers 10, 5000 Namur")
                .owner(m)
                .build();
        hotelRepository.save(ibis);
    }
}
