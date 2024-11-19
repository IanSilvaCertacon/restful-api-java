package br.com.certacon.restful_api_java_obj_list.services;

import br.com.certacon.restful_api_java_obj_list.exceptions.ResourceNotFoundException;
import br.com.certacon.restful_api_java_obj_list.model.Person;
import br.com.certacon.restful_api_java_obj_list.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private  final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    public List<Person> findAll(){

        logger.info("Finding all people!");

        return repository.findAll();

    }
    public Person findById(Long id){

        logger.info("Finding one person!");



        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Person create(Person person){

        logger.info("Creating one person!");

        return repository.save(person);
    }

    public Person update(Person person){

        logger.info("Updating one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setId(counter.incrementAndGet());
        entity.setFirtsName(person.getFirtsName());
        entity.setLastName(person.getLastName());
        entity.setAddres(person.getAddres());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id){

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);

    }

}
