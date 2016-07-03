package es.axh_studios.nohayhuevos.service.impl;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.axh_studios.nohayhuevos.dao.impl.ApuestaDaoImpl;
import es.axh_studios.nohayhuevos.domain.Apuesta;
import es.axh_studios.nohayhuevos.domain.Participacion;
import es.axh_studios.nohayhuevos.domain.Usuario;
import es.axh_studios.nohayhuevos.utils.ApiUtils;

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

            JSONArray pikes = null;

            List<Apuesta> listaPikes = new ArrayList<Apuesta>();
            try{
                Object o = jsonLogin.opt("participaciones");

                pikes = (JSONArray) o;
            } catch (Exception e){
                return usuario;
            }

            for (int i = 0; i < pikes.length(); i++) {
                try {
                    JSONObject pike = pikes.getJSONObject(i);

                    Apuesta apuesta = new Apuesta();
                    apuesta.setId(pike.optInt("id"));

                    Object o = pike.opt("porra");

                    JSONObject porra = (JSONObject) o;
                    apuesta.setIdOwner(porra.optInt("owner"));
                    apuesta.setDescripcion(porra.optString("descripcion"));
                    apuesta.setCantidad(porra.optDouble("amount"));
                    apuesta.setEstado(porra.optString("status"));
                    apuesta.setTipo(porra.optString("type"));

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

    public Apuesta findPike(Integer id, String user){
        Apuesta apuesta = new Apuesta();

        try {
        String apuestaString = apuestaDao.findPike(id, user);
        JSONObject apuestaJson = new JSONObject(apuestaString);

            apuesta.setId(apuestaJson.optInt("id"));
            apuesta.setIdOwner(apuestaJson.optInt("owner"));
            apuesta.setDescripcion(apuestaJson.optString("descripcion"));
            apuesta.setCantidad(apuestaJson.optDouble("amount"));
            apuesta.setEstado(apuestaJson.optString("status"));
            apuesta.setTipo(apuestaJson.optString("type"));

            List<Participacion> participaciones = new ArrayList<>();

            JSONArray jsonArray;
            try{
                Object o = apuestaJson.opt("participaciones");

                jsonArray = (JSONArray) o;
            } catch (Exception e){
                return apuesta;
            }

            for(int i = 0; i<jsonArray.length();i++){
                JSONObject participacion = jsonArray.optJSONObject(i);

                Participacion p = new Participacion();
                p.setId(participacion.optInt("id"));
                p.setValor(participacion.optString("value"));
                p.setUser(participacion.optInt("user"));
                p.setApuesta(participacion.optInt("porra"));

                participaciones.add(p);
            }

            apuesta.setParticipaciones(participaciones);

        } catch (IOException | JSONException e) {
            return null;
        }

        return apuesta;
    }

    public Integer crearApuesta(Apuesta ap, String valor, String id){

        JSONObject json = new JSONObject();
        try {
            json.put("amount", ap.getCantidad());
            json.put("descripcion",ap.getDescripcion());
            json.put("value", valor);
            json.put("type", "libre");
        } catch (JSONException e) {
        }

        String jsonString = json.toString();

        try {
            String respuesta = apuestaDao.createPike(jsonString, id);
            JSONObject jsonRespuesta = new JSONObject(respuesta);

            Integer res = jsonRespuesta.getInt("id");
            return res;

        } catch (IOException | JSONException e) {
        }

        return null;
    }

    public String generarUrlCompartir(Integer id){
        return ApiUtils.URL_BASE + "algo" + id;
    }
}
