package com.msauerm.androidrestrequester;

import com.android.volley.VolleyError;

/*Abstrakte Klasse für die Definition des Reaktionsverhaltens auf entsprechende Requests*/
public abstract class RESTRequestHandler<T> {

    /*Wird getriggert, wenn die Volley-Request erfolgreich verlief (mit Statuscodes 2xx)*/
    public abstract void onResponse(T responseObject);

    /*Wird getriggert, wenn die Volley-Request nicht erfolgreich verlief (mit Statuscode 4xx oder 5xx)*/
    public abstract void onError(VolleyError error);
}
