# AndroidRESTRequester
Android-Library, which combines [Volley](https://github.com/google/volley) and [Gson](https://github.com/google/gson) for making simple JSON-Requests consuming REST-APIs

# Goals
This library has the goal to simplify the process of consuming  a REST-API based on JSON as the format of the provided data. 

## Current Features

- JSONRequest with Volley for the basic CRUD-HTTP-Verbs(GET, PUT, POST, DELETE) 
- Serialization and Deserialization of instances of custom made classes send and received by a REST-API with Gson

## Upcoming Features

- Making use of Volley-Caching
- Extending the supported range of HTTP-Verbs(PATCH, HEAD etc.)
- Adding headers to a request
- Appropriate Error Handling (Error Messages, Exceptions etc.)

## Usage Examples 

## RESTObject



## Data class

The following class represents a simple data entity, which should showcase the usage of the library.
```java
public class User{

private String firstname;
private String lastname;

public User(){}

 // GETTER und SETTER einfügen

}
```


### GET-Request

The following examples showcasing a simple GET-Request on a single object REST-ressource and on a listressource.

#### Object

```java
RESTRequester<User> restRequester = new RESTRequester<>(context);
restRequester.getObject("http://url/restressource/:id", null, new RESTRequestHandler<User>(){
    public void onResponse(User responseObject){
     
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
     
    }
     
    public void onError(VolleyError error){
    
    }
}, User.class);

```



### PUT-Request


```java
RESTRequester<User> restRequester = new RESTRequester<>(context);
restRequester.getArray("http://url/restressource/:id", new User("John", "Doe"), new RESTRequestHandler<User>(){
    public void onResponse(User responseObject){
     
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
     
    }
     
    public void onError(VolleyError error){
    
    }
}, User.class);

```

### DELETE-Request

`
// Insert Code here
`

## Notes

If you have any kind of question, tip, bug, problem or a feature-request, feel free to contact me or creating an issue. 



# License

// Insert License snippet here
 

