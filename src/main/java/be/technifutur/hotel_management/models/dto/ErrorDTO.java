package be.technifutur.hotel_management.models.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
public class ErrorDTO {

    private final String message;
    private final int status;
    private final String uri;
    private final Map<String, Object> info = new HashMap<>();

    public ErrorDTO(String message, String uri) {
        this.message = message;
        this.status = 400;
        this.uri = uri;
    }

    public ErrorDTO(String message, int status, String uri) {
        this.message = message;
        this.status = status;
        this.uri = uri;
    }

    public ErrorDTO(String message, int status, String uri, Map<String, Object> info) {
        this.message = message;
        this.status = status;
        this.uri = uri;
        this.info.putAll(info);
    }
}
