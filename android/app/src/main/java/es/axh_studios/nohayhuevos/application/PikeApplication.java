package es.axh_studios.nohayhuevos.application;

import android.app.Application;

import es.axh_studios.nohayhuevos.domain.Usuario;

/**
 * Created by Alejandro on 02/07/2016.
 */
public class PikeApplication extends Application {

    private Usuario usuarioConectado;

    public Usuario getUsuarioConectado() {
        return usuarioConectado;
    }

    public void setUsuarioConectado(Usuario usuarioConectado) {
        this.usuarioConectado = usuarioConectado;
    }
}
