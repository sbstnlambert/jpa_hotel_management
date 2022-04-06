package be.technifutur.hotel_management.controllers;

import be.technifutur.hotel_management.business.service.ManagerService;
import be.technifutur.hotel_management.exceptions.ElementNotFoundException;
import be.technifutur.hotel_management.models.dto.ErrorDTO;
import be.technifutur.hotel_management.models.dto.ManagerDTO;
import be.technifutur.hotel_management.models.entities.Manager;
import be.technifutur.hotel_management.models.forms.ManagerForm;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

// On peut utiliser RestController car
// on a ajouté la dépendance spring boot starter dans pom.xml
@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService service;

    public ManagerController(ManagerService service) {
        this.service = service;
    }

    // (VERB) GET - http://localhost:8080/manager
//    @RequestMapping(method = RequestMethod.GET, path = "/manager")
    // Au lieu du RequestMapping, on peut utiliser le GetMapping
    // qui est un RequestMapping avec directement un RequestMethod.GET
    @GetMapping
    public List<ManagerDTO> getAll() {
        return service.getAll();
    }

    // (VERB) GET - http://localhost:8080/manager/id
    // Les accolades d'id servent à lier le paramètre du lien au paramètre de la méthode
    // portant l'annotation PathVariable
    @GetMapping("/{id}")

    // PathVariable détermine que le paramètre de la méthode doit être lié au paramètre du lien
    // ResponseEntity va gérer l'exception si aucun id n'existe
    // et indiquer à l'utilisateur que le gérant demandé n'existe pas dans la DB
    // ResponseEntity constituera alors le body de la réponse
    public ResponseEntity<ManagerDTO> getOne(@PathVariable(name = "id") Long identifiant) {
//            Version courte de la réponse :
//            return ResponseEntity.ok(service.getOne(identifiant));

//            Version longue de la réponse :
            return ResponseEntity.status(HttpStatus.OK)
                    .header("from-controller", "ManagerController")
                    .body(service.getOne(identifiant));
    }

    // (VERB) POST  - http://localhost:8080/manager OU
    //              - http://localhost:8080/manager/add
    // Je veux que les deux soient actives en même temps
    // NB : 'path =' est facultative
    @PostMapping(path = { "", "/add" })
    // RequestBody car on veut le formulaire dans le body
    // VALIDATION : Je spécifie que le form doit être valide
    public ResponseEntity<Object> insert(@Valid @RequestBody ManagerForm form) {
            return ResponseEntity.ok(service.insert(form));
    }

    // (VERB) PUT - http://localhost:8080/manager
    @PutMapping("/{id}")
    public ResponseEntity<ManagerDTO> update(@RequestBody ManagerForm form, @PathVariable Long id) {
            return ResponseEntity.ok(service.update(id, form));
    }

    // (VERB) DELETE - http://localhost:8080/manager/id
    @DeleteMapping("/{id}")
    public ResponseEntity<ManagerDTO> delete(@PathVariable Long id) {
            return ResponseEntity.ok(service.delete(id));
    }

}
