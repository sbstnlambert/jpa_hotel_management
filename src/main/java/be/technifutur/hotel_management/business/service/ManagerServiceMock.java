package be.technifutur.hotel_management.business.service;

import be.technifutur.hotel_management.models.dto.ManagerDTO;
import be.technifutur.hotel_management.models.forms.ManagerForm;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class ManagerServiceMock implements ManagerService {
    @Override
    public ManagerDTO insert(ManagerForm form) {
        return null;
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
