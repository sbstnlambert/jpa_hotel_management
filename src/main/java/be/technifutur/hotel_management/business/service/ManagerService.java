package be.technifutur.hotel_management.business.service;

import be.technifutur.hotel_management.models.dto.ManagerDTO;
import be.technifutur.hotel_management.models.forms.ManagerForm;

import java.util.List;

public interface ManagerService {

    // CREATE
    public ManagerDTO insert(ManagerForm form);

    // READ
    public ManagerDTO getOne(Long id);
    public List<ManagerDTO> getAll();

    // UPDATE
    public ManagerDTO update(Long id, ManagerForm form);

    // DELETE
    public ManagerDTO delete(Long id);

}
