package com.msauerm.androidrestrequester;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

/**Configuration-Class*/
public class RESTConfig {

    public Gson gson;                   // instance of Gson
    public RequestQueue queue;          // Volley-RequestQueue

    /**
     * Constructor
     *
     * @param context Required for the instantiation of the volley requestqueue
     * */
    public RESTConfig(Context context){
        gson = new Gson();
        queue = Volley.newRequestQueue(context);
    }
}
