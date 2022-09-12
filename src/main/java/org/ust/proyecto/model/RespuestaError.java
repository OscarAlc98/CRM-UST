package org.ust.proyecto.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.ust.proyecto.model.builders.RespuestaErrorBuilder;

public class RespuestaError {

    private final LocalDateTime timestamp = LocalDateTime.now();
    private int estatus;
    private String mensaje;
    private Map<String,String> errores;
    private String ruta;
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public int getEstatus() {
        return estatus;
    }
    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }
    public Map<String, String> getErrores() {
        return errores;
    }
    public void setErrores(Map<String, String> errores) {
        this.errores = errores;
    }
    public String getRuta() {
        return ruta;
    }
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public static RespuestaErrorBuilder builder() {
        return new RespuestaErrorBuilder();
    }
    
    
}
