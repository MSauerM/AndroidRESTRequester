# AndroidRESTRequester

(Under Construction)

[![Release](https://jitpack.io/v/User/Repo.svg)]
(https://jitpack.io/#MSauerM/AndroidRESTRequester)

Android-Library, which combines [Volley](https://github.com/google/volley) and [Gson](https://github.com/google/gson) for making simple JSON-Requests consuming REST-APIs and making a simple alternative to GsonRequest available.

# Goals
This library has the goal to simplify the process of consuming  a REST-API based on JSON as the format of the provided data and as a consequence of that being a simple alternative of a GsonRequest. 

## Current Features

- JSONRequest with Volley for the basic CRUD-HTTP-Verbs (GET, PUT, POST, DELETE) 
- Serialization and Deserialization of instances of custom made classes send and received by a REST-API with Gson

## Planned Features

- Making use of Volley-Caching
- Extending the supported range of HTTP-Verbs(PATCH, HEAD etc.)
- Adding headers to a request
- Appropriate Error Handling (Error Messages, Exceptions etc.)

## Usage Examples 

    Note: 
    The REST-API have to return a JSON-parseable answer and the variable names must be the same on the server and client, so
    gson can work correctly.

## RESTObject



## Data class

The following class represents a simple data entity, which should showcase the usage of the library.
```java
public class User{

private String firstname;
private String lastname;

public User(){}

public User(String firstname, String lastname){
    this.firstname = firstname;
    this.lastname = lastname;
}

 // GETTER and SETTER 

}
```


### GET-Request

The following examples showcasing a simple GET-Request on a single object REST-ressource and on a listressource.

#### Object

```java
RESTRequester<User> restRequester = new RESTRequester<>(context);
restRequester.getObject("http://url/restressource/:id", null, new RESTRequestHandler<User>(){
    public void onResponse(User responseObject){
       // handle Response
    }
     
    public void onError(VolleyError error){
    
    }
}, User.class);

```

#### Array

```java
RESTRequester<User> restRequester = new RESTRequester<>(context);
restRequester.getArray("http://url/restressource", null, new RESTRequestHandler<User>(){
    public void onResponse(User responseObject){
       // handle Response
    }
     
    public void onError(VolleyError error){
    
    }
}, User.class);

```



### PUT-Request


```java
RESTRequester<User> restRequester = new RESTRequester<>(context);
restRequester.putObject("http://url/restressource/:id", new User("John", "Doe"), new RESTRequestHandler<User>(){
    public void onResponse(User responseObject){
       // handle Response
    }
     
    public void onError(VolleyError error){
    
    }
}, User.class);

```

### POST-Request


```java
RESTRequester<User> restRequester = new RESTRequester<>(context);
restRequester.postObject("http://url/restressource", new User("John", "Doe"), new RESTRequestHandler<User>(){
    public void onResponse(User responseObject){
       // handle Response
    }
     
    public void onError(VolleyError error){
       // handle Error
    }
}, User.class);

```

### DELETE-Request

```java
RESTRequester<User> restRequester = new RESTRequester<>(context);
restRequester.deleteObject("http://url/restressource", null, new RESTRequestHandler<User>(){
    public void onResponse(User responseObject){
       // handle Response
    }
     
    public void onError(VolleyError error){
      // handle Error
    }
}, User.class);

```

## Notes

If you have any kind of question, tip, bug, problem or a feature-request, feel free to contact me or creating an issue. 



# License

```
Copyright 2019 Matthias Sauer
Copyright (C) 2011 The Android Open Source Project

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
