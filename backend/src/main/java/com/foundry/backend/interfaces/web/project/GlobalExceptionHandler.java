package com.foundry.backend.interfaces.web.project;

import com.foundry.backend.domain.project.InvalidProjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

/**
 * Traduz exceções de domínio para respostas HTTP semanticamente corretas.
 * TODO: mover para interfaces/web/ (sem subpasta de contexto) quando surgir
 * uma segunda entidade de domínio com exceções próprias — este handler
 * deixará de ser específico de Project.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidProjectException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidProject(InvalidProjectException ex) {
        Map<String, Object> body = Map.of(
                "timestamp", Instant.now().toString(),
                "status", HttpStatus.BAD_REQUEST.value(),
                "message", ex.getMessage()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }
}