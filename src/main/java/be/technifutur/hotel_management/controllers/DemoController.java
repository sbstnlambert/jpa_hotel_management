package be.technifutur.hotel_management.controllers;

import org.springframework.context.annotation.Role;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/demo")
public class DemoController {

    // <?> revient à dire <Object>
    // getHeaders récupère tous les headers
    @GetMapping("/headers")
    public ResponseEntity<?> getHeaders(@RequestHeader HttpHeaders headers) {
        headers.forEach((key, value) -> {
            System.out.println(key + " : " + value.get(0));
        });
        return ResponseEntity.ok().build();
    }

    // getHeader en récupère un en particulier
    @GetMapping("/header")
    public ResponseEntity<?> getHeader(@RequestHeader(HttpHeaders.HOST) String host) {
        System.out.println(host);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/params")
    public ResponseEntity<?> getParams(@RequestParam MultiValueMap<String, String> params) {
        params.forEach((key, value) -> {
            System.out.print(key + " : ");
            value.forEach((v) -> System.out.print(v  + ", "));
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/param")
    public ResponseEntity<?> getParam(@RequestParam int size) {
        System.out.println("size: " + size);
        return ResponseEntity.ok().build();
    }

    // SECURITE
    // Les rôles donnent accès aux authorities, pas l'inverse
    @GetMapping("/for-all")
    @PreAuthorize("permitAll()")
    public String getForAll() {
        return "For all";
    }

    @GetMapping("/for-connected")
    @PreAuthorize("isAuthenticated()")
    public String getForConnected() {
        return "For connected";
    }

    @GetMapping("/for-user")
//    @Secured("ROLE_USER")
    @RolesAllowed("ROLE_USER")
    public String getForUser() {
        return "For user";
    }

    @GetMapping("/for-admin")
    @Secured("ROLE_ADMIN")
//    @RolesAllowed("ROLE_ADMIN")
    public String getForAdmin() {
        return "For admin";
    }

}
