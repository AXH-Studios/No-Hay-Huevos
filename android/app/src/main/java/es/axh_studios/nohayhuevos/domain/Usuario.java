package es.axh_studios.nohayhuevos.domain;

import java.util.List;

/**
 * Created by Alejandro on 02/07/2016.
 */
public class Usuario {

    private Integer id;
    private String nombre;
    private String email;
    private Double cartera;
    private List<Apuesta> pikes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getCartera() {
        return cartera;
    }

    public void setCartera(Double cartera) {
        this.cartera = cartera;
    }

    public List<Apuesta> getPikes() {
        return pikes;
    }

    public void setPikes(List<Apuesta> pikes) {
        this.pikes = pikes;
    }
}
