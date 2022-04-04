package be.technifutur.hotel_management.models.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

@Getter
@Builder
public class ErrorDTO {

    private final String message;
    private final int status;
    private final String uri;
    private final HttpMethod method;
    private final Map<String, Object> info = new HashMap<>();

    public ErrorDTO(String message, String uri, HttpMethod method) {
        this.message = message;
        this.method = method;
        this.status = 400;
        this.uri = uri;
    }

    public ErrorDTO(String message, int status, String uri, HttpMethod method) {
        this.message = message;
        this.status = status;
        this.uri = uri;
        this.method = method;
    }

    public ErrorDTO(String message, int status, String uri, Map<String, Object> info, HttpMethod method) {
        this.message = message;
        this.status = status;
        this.uri = uri;
        this.method = method;
        this.info.putAll(info);
    }
}
