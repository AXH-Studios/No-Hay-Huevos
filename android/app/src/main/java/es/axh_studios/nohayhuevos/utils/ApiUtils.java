package es.axh_studios.nohayhuevos.utils;

import java.util.ArrayList;
import java.util.List;

import es.axh_studios.nohayhuevos.domain.Apuesta;
import okhttp3.MediaType;

/**
 * Created by Alejandro on 02/07/2016.
 */
public class ApiUtils {

    public static final String URL_BASE = "http://178.62.87.222:1337";

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    public static List<Apuesta> generarApuestas(){
        List<Apuesta> apuestas = new ArrayList<>();

        for(int i = 0; i< 5; i++){
            Apuesta apuesta = new Apuesta();

            apuesta.setDescripcion("Descripción de la apuesta " + (i+1));


            apuestas.add(apuesta);
        }

        return apuestas;
    }
}
