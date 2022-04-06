package be.technifutur.hotel_management.business.service;

import be.technifutur.hotel_management.business.mapper.HotelMapper;
import be.technifutur.hotel_management.exceptions.ElementNotFoundException;
import be.technifutur.hotel_management.models.dto.HotelDTO;
import be.technifutur.hotel_management.models.entities.Hotel;
import be.technifutur.hotel_management.models.forms.HotelForm;
import be.technifutur.hotel_management.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository repository;
    private final HotelMapper mapper;

    public HotelServiceImpl(HotelRepository repository, HotelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public HotelDTO insert(HotelForm form) {
        Hotel entity = mapper.formToEntity(form);
        entity = repository.save(entity);
        return mapper.entityToDTO(entity);
    }

    @Override
    public HotelDTO getOne(Long id) {
        return repository.findById(id)
                .map(mapper::entityToDTO)
                .orElseThrow(() -> new ElementNotFoundException(id, HotelDTO.class));
    }

    @Override
    public List<HotelDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::entityToDTO)
                .toList();
    }

    @Override
    public List<HotelDTO> getStars(byte starNumber) {
        return repository.findAll()
                .stream()
                .filter(hotel -> hotel.getStarNumber() == starNumber)
                .map(mapper::entityToDTO)
                .toList();
    }

    @Override
    public HotelDTO update(Long id, HotelForm form) {
        Hotel entity = repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(id, HotelDTO.class));

        entity.setName(form.getName());
        entity.setAddress(form.getAddress());
        entity.setStarNumber(form.getStarNumber());

        entity = repository.save(entity);

        return mapper.entityToDTO(entity);
    }

    @Override
    public HotelDTO delete(Long id) {
        HotelDTO toDelete = getOne(id);
        repository.deleteById(id);
        return toDelete;
    }
}
