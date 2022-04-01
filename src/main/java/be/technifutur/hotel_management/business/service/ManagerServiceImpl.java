package be.technifutur.hotel_management.business.service;

import be.technifutur.hotel_management.business.mapper.ManagerMapper;
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
        return null;
    }

    @Override
    public List<ManagerDTO> getAll() {
        return null;
    }

    @Override
    public ManagerDTO update(Long id, ManagerForm form) {
        return null;
    }

    @Override
    public ManagerDTO delete(Long id) {
        return null;
    }
}
