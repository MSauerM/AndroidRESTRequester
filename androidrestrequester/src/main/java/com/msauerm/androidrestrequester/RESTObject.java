package com.msauerm.androidrestrequester;

/*Container-Object for the instances, which are consumed of the REST-API.
* Parameterization makes it possible to handle custom classes as well.
* */
public class RESTObject<T> {

    private T object;

    public RESTObject(){

    }

    public RESTObject(T object){
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }


}
