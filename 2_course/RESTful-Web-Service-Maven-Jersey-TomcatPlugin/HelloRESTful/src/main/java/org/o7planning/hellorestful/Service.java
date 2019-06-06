package org.o7planning.hellorestful;


import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


@Path("/service")
public class Service {
    
    private static Map<Integer, Person> persons = new HashMap<Integer, Person>();
    
    static {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            int id = i + 1;
            person.setId(id);
            person.setFullName("Person № " + id);
            person.setAge(new Random().nextInt(40) + 1);
            persons.put(id, person);
        }
    }
    
    /* http://localhost:8080/rest/service/getPersonByIdXML/(1-10) */
    @GET
    @Path("/getPersonByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getPersonByIdXML(@PathParam("id")int id) {
        return persons.get(id);
    }
    
    /* http://localhost:8080/rest/service/getPersonByIdJSON/(1-10) */
    @GET
    @Path("/getPersonByIdJSON/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByIdJSON(@PathParam("id")int id){
        return persons.get(id);
    }
    
    /* http://localhost:8080/rest/service/getAllPersonsInXML */
    @GET
    @Path("/getAllPersonsInXML")
    @Produces(MediaType.APPLICATION_XML)
    public ArrayList<Person> getAllPersonsInXML() {
        return new ArrayList<Person>(persons.values());
    }
    
    /* http://localhost:8080/rest/service/getAllPersonsInJSON */
    @GET
    @Path("/getAllPersonsInJSON")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Person> getAllPersonsInJSON() {
        return new ArrayList<Person>(persons.values());
    }
}