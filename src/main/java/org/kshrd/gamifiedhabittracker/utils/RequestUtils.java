package org.kshrd.gamifiedhabittracker.utils;

import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.springframework.http.HttpStatus;

import java.net.URI;

import static java.time.LocalDateTime.now;
import static org.apache.logging.log4j.util.Strings.EMPTY;

public class RequestUtils {

    public static <T> Response<T> getResponse(String message, HttpStatus status, T payload) {
        return new Response<>(
                message,
                status.name(),
                payload,
                now()
        );
    }

    public static URI getUrl(){
        return URI.create(EMPTY);
    }
}
