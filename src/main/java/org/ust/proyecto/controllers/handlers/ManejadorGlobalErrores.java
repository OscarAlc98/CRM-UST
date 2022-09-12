package org.ust.proyecto.controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.ust.proyecto.model.RespuestaError;

@RestControllerAdvice
public class ManejadorGlobalErrores {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejaException(Exception ex, WebRequest request) {
        return RespuestaError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Server encountered an error")
                .ruta(request.getDescription(false).substring(4))
                .entidad();
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleStatusException(MethodArgumentNotValidException ex, WebRequest request) {
        return RespuestaError.builder()
                .exception(ex)
                .message("Ocurrió un error al validar la información de la petición.")
                .ruta(request.getDescription(false).substring(4))
                .entidad();
    }
}
