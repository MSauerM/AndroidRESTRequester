package com.msauerm.androidrestrequester;

/*Container-Object für die mittels REST-Schnittstelle verfügbaren Klassen.
* Struktur wurde für das leichtere verarbeiten, von entsprechenden Instanzen in inneren Klassen erstellt,
* da diese 'final' sein müssen,*/
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
