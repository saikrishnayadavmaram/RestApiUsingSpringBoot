package com.example.SpringExample.controller;

import com.example.SpringExample.model.Person;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//Using RestController to takes care of mapping request data to the defined request handler method.
// Once response body is generated from the handler method, it converts it to JSON data
@RestController
@RequestMapping("/persons")
public class PersonController {

    //Mapping the Person details
    Map<String, Person> info = new HashMap<>();

    // Post Mapping is used For Adding person details
    @PostMapping("/add")
    public String send(@RequestBody Person persondetails) {
        Person add = new Person();
        add.setId(persondetails.getId());
        add.setAge(persondetails.getAge());
        add.setName(persondetails.getName());
        info.put(persondetails.getId(), add);
        System.out.println(persondetails);
        return "personadded";
    }

    // Get Mapping is used for getting the person details
    @GetMapping("/get")
    //Using Collection fetching the entire data
    public Collection<Person> receive() {
        return info.values();
    }

    // PatchMapping is used for updating the partial data of the user
    @PatchMapping("/update")

    public String update(@RequestParam("Id") String Id, @RequestBody Person persondetails) {

        //sending request to (info) whether it contains that key mentioned in the endpoint
        if (info.containsKey(Id)) {
            Person details = new Person();
            details.setId(persondetails.getId());
            details.setName(persondetails.getName());
            details.setAge(persondetails.getAge());
            info.put(Id, details);
            return "Update is done";
        } else return "Person ID not found";
    }

    //PutMapping is used for changing the entire record
    @PutMapping(path = "/{Id}")

    public String alter(@PathVariable String Id, @RequestBody Person persondetails) {

        //sending request to (info) whether it contains that key mentioned in the endpoint
        if (info.containsKey(Id)) {
            Person Details = new Person();
            //removing the mentioned id in the endpoint
            info.remove(Id);
            //adding the details(id here)
            info.put(Id, persondetails);
            return "Person details have been edited";
        } else return "Person ID was not found";
    }

    // Delete mapping is used for removing the person by id
    @DeleteMapping(path = "id/{Id}")

    //pathvariable is used to specify the path to server from where it has to read the data
    public String delete(@PathVariable String Id) {
        {
            //sending request to (info) whether it contains that key mentioned in the endpoint
            if (info.containsKey(Id)) {
                //deleting the record from it
                info.remove(Id);
                //returning messege
                return "Person details deleted";
                //or else returning messege
            } else return "Person ID not found";
        }
    }
}