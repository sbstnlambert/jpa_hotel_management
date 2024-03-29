package be.technifutur.hotel_management.business.service.spec;

import be.technifutur.hotel_management.models.dto.HotelDTO;
import be.technifutur.hotel_management.models.entities.Hotel;
import be.technifutur.hotel_management.models.forms.HotelForm;

import java.util.List;

public interface HotelService {
    
    // CREATE
    public HotelDTO insert(HotelForm form);
    
    // READ
    public HotelDTO getOne(Long id);
    public List<HotelDTO> getAll();
    public List<HotelDTO> getStars(byte starNumber);
    
    // UPDATE
    public HotelDTO update(Long id, HotelForm form);
    
    // DELETE
    public HotelDTO delete(Long id);

}
