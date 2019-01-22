package com.msauerm.androidrestrequester;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

/*Konfiguration der für Gson und Volley benötigten Strukturen*/
public class RESTConfig {

    public Gson gson;                   // GSON-Instanz
    public RequestQueue queue;          // zentrale Requestqueue

    public RESTConfig(Context context){                 // Erzeugung von Gson und der Requestqueue mittels statischer Factory-Method
        gson = new Gson();
        queue = Volley.newRequestQueue(context);
    }
}
