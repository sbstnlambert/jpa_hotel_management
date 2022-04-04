package be.technifutur.hotel_management.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // <?> revient à dire <Object>
    // getHeaders récupère tous les headers
    @GetMapping("/demo/headers")
    public ResponseEntity<?> getHeaders(@RequestHeader HttpHeaders headers) {
        headers.forEach((key, value) -> {
            System.out.println(key + " : " + value.get(0));
        });
        return ResponseEntity.ok().build();
    }

    // getHeader en récupère un en particulier
    @GetMapping("/demo/header")
    public ResponseEntity<?> getHeader(@RequestHeader(HttpHeaders.HOST) String host) {
        System.out.println(host);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/demo/params")
    public ResponseEntity<?> getParams(@RequestParam MultiValueMap<String, String> params) {
        params.forEach((key, value) -> {
            System.out.print(key + " : ");
            value.forEach((v) -> System.out.print(v  + ", "));
        });
        return ResponseEntity.ok().build();
    }

    @GetMapping("/demo/param")
    public ResponseEntity<?> getParam(@RequestParam int size) {
        System.out.println("size: " + size);
        return ResponseEntity.ok().build();
    }

}
