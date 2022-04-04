package be.technifutur.hotel_management.controllers;

import be.technifutur.hotel_management.business.service.ManagerService;
import be.technifutur.hotel_management.exceptions.ElementNotFoundException;
import be.technifutur.hotel_management.models.dto.ErrorDTO;
import be.technifutur.hotel_management.models.dto.ManagerDTO;
import be.technifutur.hotel_management.models.forms.ManagerForm;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// On peut utiliser RestController car
// on a ajouté la dépendance spring boot starter dans pom.xml
@RestController
public class ManagerController {

    private final ManagerService service;

    public ManagerController(ManagerService service) {
        this.service = service;
    }

    // (VERB) GET - http://localhost:8080/manager
//    @RequestMapping(method = RequestMethod.GET, path = "/manager")
    // Au lieu du RequestMapping, on peut utiliser le GetMapping
    // qui est un RequestMapping avec directement un RequestMethod.GET
    @GetMapping("/manager")
    public List<ManagerDTO> getAll() {
        return service.getAll();
    }

    // (VERB) GET - http://localhost:8080/manager/id
    // Les accolades d'id servent à lier le paramètre du lien au paramètre de la méthode
    // portant l'annotation PathVariable
    @GetMapping("/manager/{id}")

    // PathVariable détermine que le paramètre de la méthode doit être lié au paramètre du lien
    // ResponseEntity va gérer l'exception si aucun id n'existe
    // et indiquer à l'utilisateur que le gérant demandé n'existe pas dans la DB
    // ResponseEntity constituera alors le body de la réponse
    public ResponseEntity<Object> getOne(@PathVariable(name = "id") Long identifiant) {
        try {
//            Version courte de la réponse :
//            return ResponseEntity.ok(service.getOne(identifiant));

//            Version longue de la réponse :
            return ResponseEntity.status(HttpStatus.OK)
                    .header("from-controller", "ManagerController")
                    .body(service.getOne(identifiant));

        } catch (ElementNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .header("header-key", "value1", "value2")
                    .body(new ErrorDTO(e.getMessage(), 404, "/manager/"+identifiant, HttpMethod.GET));
        }
    }

    // (VERB) POST  - http://localhost:8080/manager OU
    //              - http://localhost:8080/manager/add
    // Je veux que les deux soient actives en même temps
    // NB : 'path =' est facultative
    @PostMapping(path = { "/manager", "/manager/add" })
    // RequestBody car on veut le formulaire dans le body
    public ResponseEntity<Object> insert(@RequestBody ManagerForm form) {
        try {
            return ResponseEntity.ok(service.insert(form));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ErrorDTO(e.getMessage(), "/manager/add", HttpMethod.POST));
        }
    }

    // (VERB) PUT - http://localhost:8080/manager
    @PutMapping("/manager/{id}")
    public ResponseEntity<Object> update(@RequestBody ManagerForm form, @PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.update(id, form));
        } catch (ElementNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(
                            ErrorDTO.builder()
                                    .message(e.getMessage())
                                    .uri("/gerant/" + id)
                                    .status(404)
                                    .method(HttpMethod.PUT)
                                    .build()
                    );
        }
    }

    // (VERB) DELETE - http://localhost:8080/manager/id
    @DeleteMapping("/manager/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.delete(id));
        } catch (ElementNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorDTO(e.getMessage(), 404, "/manager/"+id, HttpMethod.DELETE));
        }
    }

}
