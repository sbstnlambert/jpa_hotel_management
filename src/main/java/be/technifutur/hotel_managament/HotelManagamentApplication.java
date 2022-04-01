package be.technifutur.hotel_managament;

import be.technifutur.hotel_managament.repositories.ClientRepository;
import be.technifutur.hotel_managament.repositories.HotelRepository;
import be.technifutur.hotel_managament.repositories.ManagerRepository;
import be.technifutur.hotel_managament.repositories.RoomRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HotelManagamentApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(HotelManagamentApplication.class, args);

		ClientRepository clientRepo = context.getBean(ClientRepository.class);
		HotelRepository hotelRepo = context.getBean(HotelRepository.class);
		ManagerRepository managerRepo = context.getBean(ManagerRepository.class);
		RoomRepository roomRepo = context.getBean(RoomRepository.class);

	}

}
