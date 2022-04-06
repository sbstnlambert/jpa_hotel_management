package be.technifutur.hotel_management.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/for-all")
    public String getForAll() {
        return "For all";
    }

    @GetMapping("/for-connected")
    public String getForConnected() {
        return "For connected";
    }

    @GetMapping("/for-user")
    public String getForUser() {
        return "For user";
    }

    @GetMapping("/for-admin")
    public String getForAdmin() {
        return "For admin";
    }

}
