package be.technifutur.hotel_managament.utils;

import be.technifutur.hotel_managament.models.entities.Hotel;
import be.technifutur.hotel_managament.repositories.HotelRepository;
import org.springframework.beans.factory.InitializingBean;

public class DatabaseFiller implements InitializingBean {

    private final HotelRepository repository;

    public DatabaseFiller (HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        Hotel hotel = new Hotel();
        hotel.setName("Hilton");
        hotel.setStarNumber((byte)4);

        repository.save(hotel);
    }
}
