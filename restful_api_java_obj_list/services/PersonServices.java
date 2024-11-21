package br.com.certacon.restful_api_java_obj_list.services;

import br.com.certacon.restful_api_java_obj_list.data.vo.v2.PersonVOV2;
import br.com.certacon.restful_api_java_obj_list.exceptions.ResourceNotFoundException;
import br.com.certacon.restful_api_java_obj_list.mapper.DozerMapper;
import br.com.certacon.restful_api_java_obj_list.mapper_custom.PersonMapper;
import br.com.certacon.restful_api_java_obj_list.model.Person;
import br.com.certacon.restful_api_java_obj_list.repositories.PersonRepository;
import br.com.certacon.restful_api_java_obj_list.data.vo.v1.PersonVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static br.com.certacon.restful_api_java_obj_list.mapper.DozerMapper.mapper;

@Service
public class PersonServices {

    private  final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper mapper;

    public List<PersonVO> findAll() {
        logger.info("Finding all people!");

        return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
    }
    public PersonVO findById(Long id){

        logger.info("Finding one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return DozerMapper.parseObject(entity, PersonVO.class);
    }

    public PersonVO create(PersonVO person){

        logger.info("Creating one person!");

        var entity = DozerMapper.parseObject(person, Person.class);
        var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person){

        logger.info("Creating one person with V2!");

        var entity = mapper.convertVoToEntity(person);
        var vo = mapper.convertEntityToVo((repository.save(entity)));

        return vo;
    }

    public PersonVO update(PersonVO person){

        logger.info("Updating one person!");

        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        //entity.setId(counter.incrementAndGet());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var savedEntity = repository.save(entity);
        var vo = DozerMapper.parseObject(savedEntity, PersonVO.class);
        return vo;
    }

    public void delete(Long id){

        logger.info("Deleting one person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.delete(entity);

    }

}
