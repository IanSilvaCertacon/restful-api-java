package br.com.certacon.restful_api_java_obj_list.services;

import br.com.certacon.restful_api_java_obj_list.controllers.PersonController;
import br.com.certacon.restful_api_java_obj_list.data.vo.v1.PersonVO;
import br.com.certacon.restful_api_java_obj_list.data.vo.v2.PersonVOV2;
import br.com.certacon.restful_api_java_obj_list.exceptions.ResourceNotFoundException;
import br.com.certacon.restful_api_java_obj_list.mapper_custom.PersonMapper;
import br.com.certacon.restful_api_java_obj_list.model.Person;
import br.com.certacon.restful_api_java_obj_list.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {

    private static final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper; // Usando MapStruct como mapper

    public List<PersonVO> findAll() {
        logger.info("Finding all people!");
        List<Person> persons = repository.findAll();
        List<PersonVO> personVOs = mapper.toPersonVOList(persons);
        personVOs.forEach(p -> p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel()));
        return personVOs;
    }

    public PersonVO findById(Long id) {
        logger.info("Finding one person!");
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        PersonVO vo = mapper.toPersonVO(entity);
        vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
        return vo;
    }

    public PersonVO create(PersonVO person) {
        logger.info("Creating one person! Received data: " + person);
        try {
            Person entity = mapper.toPerson(person);
            logger.info("Converted to entity: " + entity);
            Person savedEntity = repository.save(entity);
            logger.info("Saved entity: " + savedEntity);
            PersonVO result = mapper.toPersonVO(savedEntity);
            logger.info("Returning result: " + result);
            return result;
        } catch (Exception e) {
            logger.severe("Error creating person: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        logger.info("Creating one person with V2!");
        // Usando o mapper customizado para o mapeamento V2
        Person entity = mapper.convertVoToEntity(person);
        Person savedEntity = repository.save(entity);
        return mapper.convertEntityToVo(savedEntity);
    }

    public PersonVO update(PersonVO person) {
        logger.info("Updating one person!");
        // Encontrando a entidade existente no banco
        Person entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        // Atualizando os campos da entidade
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        // Salvando a entidade atualizada
        Person savedEntity = repository.save(entity);
        // Convertendo a entidade salva para PersonVO
        return mapper.toPersonVO(savedEntity);
    }

    public void delete(Long id) {
        logger.info("Deleting one person!");
        // Encontrando a entidade no banco para deletar
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        repository.delete(entity);
    }
}