package be.technifutur.hotel_management.controllers;

import be.technifutur.hotel_management.business.service.spec.HotelService;
import be.technifutur.hotel_management.models.dto.HotelDTO;
import be.technifutur.hotel_management.models.forms.HotelForm;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    // (VERB) GET - http://localhost:8080/hotel
    @GetMapping
    public List<HotelDTO> getAll() {
        return service.getAll();
    }

    // (VERB) GET - http://localhost:8080/hotel/id
    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    // (VERB) POST  - http://localhost:8080/hotel OU
    //              - http://localhost:8080/hotel/add
    @PostMapping({"", "/add"})
    public ResponseEntity<HotelDTO> insert(@RequestBody HotelForm form) {
        return ResponseEntity.ok(service.insert(form));
    }

    // (VERB) PUT - http://localhost:8080/hotel/id
    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> update(@RequestBody HotelForm form, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(id, form));
    }

    // (VERB) DELETE - http://localhost:8080/hotel/id
    @DeleteMapping("/{id}")
    public ResponseEntity<HotelDTO> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }

    // (VERB) GET - http://localhost:8080/hotel/stars/{starNumber}
    @GetMapping("stars/{starNumber}")
    public List<HotelDTO> getStars(@PathVariable byte starNumber) {
        return service.getStars(starNumber);
    }

}
