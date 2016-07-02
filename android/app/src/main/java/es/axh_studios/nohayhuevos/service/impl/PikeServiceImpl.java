package es.axh_studios.nohayhuevos.service.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.axh_studios.nohayhuevos.dao.impl.ApuestaDaoImpl;
import es.axh_studios.nohayhuevos.domain.Apuesta;
import es.axh_studios.nohayhuevos.domain.Usuario;

/**
 * Created by Alejandro on 02/07/2016.
 */
public class PikeServiceImpl {

    private ApuestaDaoImpl apuestaDao;

    public PikeServiceImpl() {
        apuestaDao = new ApuestaDaoImpl();
    }

    public Usuario login(String id) {
        Usuario usuario = new Usuario();

        try {
            String stringLogin = apuestaDao.listPikes(id);
            JSONObject jsonLogin = new JSONObject(stringLogin);

            usuario.setId(jsonLogin.optInt("id"));
            usuario.setNombre(jsonLogin.optString("username"));
            usuario.setCartera(jsonLogin.optDouble("cartera"));
            usuario.setEmail(id);

            Object o = jsonLogin.get("participaciones");

            JSONArray pikes = (JSONArray) o;

            List<Apuesta> listaPikes = new ArrayList<Apuesta>();

            for (int i = 0; i < pikes.length(); i++) {
                try {
                    JSONObject pike = pikes.getJSONObject(i);

                    Apuesta apuesta = new Apuesta();
                    apuesta.setId(pike.optInt("id"));
                    apuesta.setDescripcion(pike.optString("descripcion"));
                    apuesta.setCantidad(pike.optDouble("amount"));
                    apuesta.setIdOwner(pike.optInt("owner"));
                    listaPikes.add(apuesta);
                } catch (Exception e) {
                    continue;
                }
            }

            usuario.setPikes(listaPikes);
        } catch (IOException | JSONException e) {
            return null;
        }

        return usuario;
    }

}
