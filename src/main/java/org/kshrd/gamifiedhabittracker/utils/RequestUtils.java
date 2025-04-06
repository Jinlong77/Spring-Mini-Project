package org.kshrd.gamifiedhabittracker.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.kshrd.gamifiedhabittracker.model.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;

import java.io.IOException;
import java.net.URI;

import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static java.time.LocalDateTime.now;
import static org.apache.logging.log4j.util.Strings.EMPTY;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_PROBLEM_JSON_VALUE;

public class RequestUtils {

    public static <T> Response<T> getResponse(String message, HttpStatus status, T payload) {
        return new Response<>(
                message,
                status.name(),
                payload,
                now()
        );
    }

    public static void handleAuthorityError(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(SC_FORBIDDEN);
        response.setContentType(APPLICATION_PROBLEM_JSON_VALUE);

        ProblemDetail problem = ProblemDetail.forStatus(FORBIDDEN);
        problem.setTitle("Access Denied");
        problem.setDetail(accessDeniedException.getMessage());
        problem.setProperty("path", request.getRequestURI());

        objectMapper.writeValue(response.getOutputStream(), problem);
    }

    public static URI getUrl(){
        return URI.create(EMPTY);
    }
}
