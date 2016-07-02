package es.axh_studios.nohayhuevos.dao.impl;

import java.io.IOException;

import es.axh_studios.nohayhuevos.utils.ApiUtils;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Alejandro on 02/07/2016.
 */
public class ApuestaDaoImpl {

    private OkHttpClient client = new OkHttpClient();
    private static final MediaType JSON = ApiUtils.JSON;
    private static final String URL_BASE = ApiUtils.URL_BASE;

    public String listPikes(String user) throws IOException {
        String url = URL_BASE+"/api/user/"+ user +"/porra/";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String findPike(Integer id, String user) throws IOException {
        String url = URL_BASE+"/api/porra/"+ id +"?user=" + user;

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String createPike(String json) throws IOException {
        String url = URL_BASE+"api/payment";

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

}
