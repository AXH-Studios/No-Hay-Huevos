package es.axh_studios.nohayhuevos.service.impl;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import es.axh_studios.nohayhuevos.dao.impl.UsuarioDaoImpl;
import es.axh_studios.nohayhuevos.domain.Usuario;

/**
 * Created by Alejandro on 02/07/2016.
 */
public class UserServiceImpl {

    private UsuarioDaoImpl usuarioDao;

    public UserServiceImpl() {
        usuarioDao = new UsuarioDaoImpl();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public Usuario getUser(String id) {
        Usuario usuario = new Usuario();

        try {
            String stringLogin = usuarioDao.getUser(id);
            JSONObject jsonLogin = new JSONObject(stringLogin);

            usuario.setId(jsonLogin.optInt("id"));
            usuario.setCartera(jsonLogin.optDouble("cartera"));
            usuario.setEmail(jsonLogin.optString("username"));
        } catch (IOException | JSONException e) {
            return null;
        }

        return usuario;
    }
}
