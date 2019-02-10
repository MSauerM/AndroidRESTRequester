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

/*requester, which combines the volley-requests with the serialization of gson to get usable java-instances made of the received server responses
* and their body content (in this case specifically JSON)
* */
public class RESTRequester<T> {

    private static RESTConfig config;       // Static Singleton instance to handle all settings with this object

    //
    public RESTRequester(Context context){
        if(config == null){
            config = new RESTConfig(context);
        }
    }

    /**GET-Request for consuming data of a REST-API
    *
     * @param url target url of the request
    * @param body null if its a GET-Request
     * @param handler implement the behavior of handling the response
    * @param parameterizedClass class of the instance, which has to be deserialized
     *
     * */
    public RESTObject<T> getObject(String url, T body, final RESTRequestHandler<T> handler, final Class<T> parameterizedClass){

        final RESTObject<T> responseObject= new RESTObject<T>();
        JsonObjectRequest getRequest =
                new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
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
        config.queue.add(getRequest);
        return responseObject;
    }

    /**PUT-Request for updating the specified instance of the Rest service
     * @param url target url of the request
     * @param body instance, which have to be serialized
     * @param handler implement the behavior of handling the response
     * @param parameterizedClass class of the instance, which has to be deserialized
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
        config.queue.add(putRequest);
        return responseObject;
    }

    /**POST-Request for creating a new instance in the REST-API
     * @param url target url of the request
     * @param body instance, which have to be serialized
     * @param handler implement the behavior of handling the response
     * @param parameterizedClass class of the instance, which has to be deserialized
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

    /**DELETE-Request for deleting the specified instance/instances of the REST-API
    * @param url target url of the request
     * @param body null, because its a DELETE-Request
     * @param handler implement the behavior of handling the response
     * @param parameterizedClass null, because it is a DELETE-Request
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

    /** GET-Request for retrieving an array
     * @param url target url of the request
     * @param body null, if its a GET-Request
     * @param handler implement the behavior of handling the response
     * @param parameterizedClass class of the instance, which has to be deserialized
     * */
    public RESTObject<ArrayList<T>> getArray(String url, T body, final RESTRequestHandler<ArrayList<T>> handler, final Class<T> parameterizedClass){

        final RESTObject<ArrayList<T>> responseObjectArray = new RESTObject<ArrayList<T>>();
        responseObjectArray.setObject(new ArrayList<T>());

        JsonArrayRequest getArrayRequest =
                new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
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
