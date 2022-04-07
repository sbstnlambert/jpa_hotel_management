package be.technifutur.hotel_management.business.service;

import be.technifutur.hotel_management.business.mapper.ManagerMapper;
import be.technifutur.hotel_management.business.service.spec.ManagerService;
import be.technifutur.hotel_management.exceptions.ElementNotFoundException;
import be.technifutur.hotel_management.models.dto.ManagerDTO;
import be.technifutur.hotel_management.models.entities.Manager;
import be.technifutur.hotel_management.models.forms.ManagerForm;
import be.technifutur.hotel_management.repositories.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository repository;
    private final ManagerMapper mapper;

    public ManagerServiceImpl(ManagerRepository repository, ManagerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ManagerDTO insert(ManagerForm form) {

        Manager entity = mapper.formToEntity(form);
        entity = repository.save(entity);
        return mapper.entityToDTO(entity);

    }

    @Override
    public ManagerDTO getOne(Long id) {
        return repository.findById(id)
                // Pas besoin de .stream() car un findById() renvoie un Optional
                // qui est un peu un comme un "stream" à un élément
                .map(mapper::entityToDTO)
                .orElseThrow(() -> new ElementNotFoundException(id, ManagerDTO.class));
    }

    @Override
    public List<ManagerDTO> getAll() {
        return repository.findAll()
                // Ici, utilisation du .stream() car pas Optional
                .stream()
                .map(mapper::entityToDTO)
                .toList();
    }

    @Override
    public ManagerDTO update(Long id, ManagerForm form) {
        Manager entity = repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(id, ManagerDTO.class));

        entity.setName(form.getName());
        entity.setSurname(form.getSurname());
        entity.setBeginCareerOn(form.getBeginCareerOn());

        entity = repository.save(entity);
        return mapper.entityToDTO(entity);
    }

    @Override
    public ManagerDTO delete(Long id) {
        ManagerDTO toDelete = getOne(id);
        repository.deleteById(id);
    return toDelete;
    }
}
