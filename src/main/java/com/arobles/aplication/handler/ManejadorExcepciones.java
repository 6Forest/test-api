
package com.arobles.aplication.handler;

import com.arobles.aplication.excepcion.SaldoInsuficienteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ManejadorExcepciones {

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<Object> manejarSaldoInsuficiente(SaldoInsuficienteException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse("Saldo no disponible"));
    }

    static class ErrorResponse {
        private String mensaje;

        public ErrorResponse(String mensaje) {
            this.mensaje = mensaje;
        }

        public String getMensaje() {
            return mensaje;
        }
    }
}