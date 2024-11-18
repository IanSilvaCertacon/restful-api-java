package br.com.certacon.restful_api_java_parameters2.services;

import br.com.certacon.restful_api_java_parameters2.model.Person;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private  final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public Person findById(String id){

        logger.info("Finding one person!");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirtsName("Ian");
        person.setLastName("Silva");
        person.setAddres("São Paulo - Tucuruvi");
        person.setGender("Male");

        return person;
    }

}
