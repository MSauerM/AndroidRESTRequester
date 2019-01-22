package com.msauerm.androidrestrequester;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/*Requester-Klasse, welche die Volley-Request mit der Gson-Serialisierung verbindet, wodurch
* vom Server erhaltene JSON-Instanzen direkt als Java-Instanzen genutzt werden können
* */
public class RESTRequester<T> {

    private static RESTConfig config;

    // Konstruktor für die Erstellung einer Singleton-Instanz der REST-Config-Klasse
    public RESTRequester(Context context){
        if(config == null){
            config = new RESTConfig(context);
        }
    }

    /**GET-Request auf die angegebene URL mit zugehörigem Parsing der Klasseninstanz
    *
     * @param url Ziel-URL der HTTP-Request
    * @param body Bei der GET-Request = null
     * @param handler Implementation des Verhaltens nach erfolgreicher/fehlgeschlagener Request
    * @param parameterizedClass Klasse der zu deserialisierenden JSON-Antwort
     *
     * */
    public RESTObject<T> getObject(String url, T body, final RESTRequestHandler<T> handler, final Class<T> parameterizedClass){

        final RESTObject<T> responseObject= new RESTObject<T>();                    // Erstellung eines RESTObjects<>
        JsonObjectRequest getRequest =
                new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {       // Zusammenfügen des Volley-JsonObjectRequest
                        T object = config.gson.fromJson(String.valueOf(response), parameterizedClass); // Deserialisieren der Json-Response
                        responseObject.setObject(object);
                        handler.onResponse(object);                                                     // Übergabe der deserialisierten Instanz an das Handlerobject
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handler.onError(error);
                    }
                });
        config.queue.add(getRequest);                                                                   // Volley: notwendiges Hinzufügen zu der in der RESTConfig erstellten Requestqueue
        return responseObject;
    }

    /**PUT-Request auf angegebene URL mit der entsprechenden Aktualisierung der Instanz
     * @param url Ziel-URL der HTTP-Request
     * @param body Die zu serialisierende Klasseninstanz
     * @param handler Implementation des Verhaltens nach erfolgreicher/fehlgeschlagener Request
     * @param parameterizedClass Klasse der zu deserialisierenden JSON-Antwort
     * */
    public RESTObject<T> putObject(String url, T body, final RESTRequestHandler<T> handler, final Class<T> parameterizedClass){

        final RESTObject<T> responseObject = new RESTObject<T>();

        JSONObject jsonBody = null;
        try {
            jsonBody = new JSONObject(config.gson.toJson(body));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest putRequest =
                new JsonObjectRequest(Request.Method.PUT, url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                       // TypeToken<T> responseType= new TypeToken<T>(){};
                        T object = config.gson.fromJson(String.valueOf(response), parameterizedClass);
                        responseObject.setObject(object);
                  //      responseObject.onSuccessfulRequest();
                        handler.onResponse(object);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handler.onError(error);
                    }
                });
        config.queue.add(putRequest);
        return responseObject;
    }

    /**POST-Request auf die angegebenen URL mit dem zu serialisierenden Objekt der entsprechenden Klasse
     * @param url Ziel-URL der HTTP-Request
     * @param body Die zu serialisierende Klasseninstanz
     * @param handler Implementation des Verhaltens nach erfolgreicher/fehlgeschlagener Request
     * @param parameterizedClass Klasse der zu deserialisierenden JSON-Antwort
     * */
    public RESTObject<T> postObject(String url, T body, final RESTRequestHandler<T> handler, final Class<T> parameterizedClass){

        final RESTObject<T> responseObject = new RESTObject<T>();

        JSONObject jsonBody = null;
        try {
            jsonBody = new JSONObject(config.gson.toJson(body));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest postRequest =
                new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //TypeToken<T> responseType= new TypeToken<T>(){};
                        T object = config.gson.fromJson(String.valueOf(response), parameterizedClass);
                        responseObject.setObject(object);
                        handler.onResponse(object);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handler.onError(error);
                    }
                });
        config.queue.add(postRequest);
        return responseObject;
    }

    /**DELETE-Request auf die angegebene URL
    * @param url Ziel-URL der HTTP-Request
     * @param body Bei DELETE-Request: null
     * @param handler Implementation des Verhaltens nach erfolgreicher/fehlgeschlagener Request
     * @param parameterizedClass Bei DELETE-REQUEST: null
    * */
    public void deleteObject(String url, T body, RESTRequestHandler<T> handler, final Class<T> parameterizedClass){
        JsonObjectRequest deleteRequest =
                new JsonObjectRequest(Request.Method.DELETE, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        config.queue.add(deleteRequest);
    }

    /** GET-Request auf die angegebene URL einer Listenressource
     * @param url Ziel-URL der HTTP-Request
     * @param body Bei GET-Request: null
     * @param handler Implementation des Verhaltens nach erfolgreicher/fehlgeschlagener Request
     * @param parameterizedClass Klasse der zu deserialisierenden JSON-Antwort
     * */
    public RESTObject<ArrayList<T>> getArray(String url, T body, final RESTRequestHandler<ArrayList<T>> handler, final Class<T> parameterizedClass){

        final RESTObject<ArrayList<T>> responseObjectArray = new RESTObject<ArrayList<T>>();
        responseObjectArray.setObject(new ArrayList<T>());

        JsonArrayRequest getArrayRequest =
                new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i<response.length(); i++){
                            try {
                                T object = config.gson.fromJson(String.valueOf(response.getJSONObject(i)), parameterizedClass);
                                responseObjectArray.getObject().add(object);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        handler.onResponse(responseObjectArray.getObject());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        handler.onError(error);

                    }
                });
        config.queue.add(getArrayRequest);
        return responseObjectArray;
    }

}
