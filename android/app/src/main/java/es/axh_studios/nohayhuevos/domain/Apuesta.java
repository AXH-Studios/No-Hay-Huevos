package es.axh_studios.nohayhuevos.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alejandro on 02/07/2016.
 */
public class Apuesta implements Serializable {


    private Integer id;
    private String descripcion;
    private Double cantidad;
    private Integer idOwner;
    private Integer idWinner;
    private String estado;
    private String tipo;
    private List<Participacion> participaciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Participacion> getParticipaciones() {
        return participaciones;
    }

    public void setParticipaciones(List<Participacion> participaciones) {
        this.participaciones = participaciones;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Apuesta) {
            Apuesta otraApuesta = (Apuesta) obj;
            if (this.id.equals(otraApuesta.id)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public Integer getIdWinner() {
        return idWinner;
    }

    public void setIdWinner(Integer idWinner) {
        this.idWinner = idWinner;
    }
}
