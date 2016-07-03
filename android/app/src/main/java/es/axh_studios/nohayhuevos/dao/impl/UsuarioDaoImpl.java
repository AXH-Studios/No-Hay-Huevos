package es.axh_studios.nohayhuevos.dao.impl;

import java.io.IOException;

import es.axh_studios.nohayhuevos.utils.ApiUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Alejandro on 03/07/2016.
 */
public class UsuarioDaoImpl {

    private OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = ApiUtils.JSON;
    private static final String URL_BASE = ApiUtils.URL_BASE;

    public String getUser(String user) throws IOException {
        String url = URL_BASE+"/api/user/"+ user +"/";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
