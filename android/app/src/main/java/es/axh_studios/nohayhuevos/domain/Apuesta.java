package es.axh_studios.nohayhuevos.domain;

import java.io.Serializable;

/**
 * Created by Alejandro on 02/07/2016.
 */
public class Apuesta implements Serializable {


    private String id;
    private String descripcion;
    private Double cantidad;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
