package com.example.SpringExample.controller;

import com.example.SpringExample.model.Person;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
	@GetMapping
	public Collection<Person> recieve() {
		return info.values();
	}

	// Delete mapping is used for removing the person by id
	@DeleteMapping(path = "id/{Id}")
	public String delete(@PathVariable String Id) {
		{
			if (info.containsKey(Id)) {
				info.remove(Id);
				return "Person details deleted";
			} else
				return "Person ID not found";
		}

	}
}