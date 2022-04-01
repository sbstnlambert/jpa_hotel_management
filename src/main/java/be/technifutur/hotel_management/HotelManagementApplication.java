package be.technifutur.hotel_management;

import be.technifutur.hotel_management.repositories.ClientRepository;
import be.technifutur.hotel_management.repositories.HotelRepository;
import be.technifutur.hotel_management.repositories.ManagerRepository;
import be.technifutur.hotel_management.repositories.RoomRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class HotelManagementApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(HotelManagementApplication.class, args);

		ClientRepository clientRepo = context.getBean(ClientRepository.class);
		HotelRepository hotelRepo = context.getBean(HotelRepository.class);
		ManagerRepository managerRepo = context.getBean(ManagerRepository.class);
		RoomRepository roomRepo = context.getBean(RoomRepository.class);

	}

}
