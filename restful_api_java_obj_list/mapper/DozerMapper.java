import br.com.certacon.restful_api_java_obj_list.data.vo.v1.PersonVO;
import br.com.certacon.restful_api_java_obj_list.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DozerMapper {

    private final PersonMapper personMapper;

    @Autowired
    public DozerMapper(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    public PersonVO toPersonVO(Person person) {
        return personMapper.toPersonVO(person);
    }

    public Person toPerson(PersonVO personVO) {
        return personMapper.toPerson(personVO);
    }

    public <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<>();

        // Verificar o tipo do objeto antes de mapear
        for (O o : origin) {
            if (o instanceof Person) {
                // Convertendo Person para PersonVO
                destinationObjects.add((D)personMapper.toPersonVO((Person) o));
            } else {
                // Caso não seja Person, o que fazer? Dependendo do tipo de "O" você pode adicionar outro mapeamento
                // Aqui você pode adicionar outra lógica para tratar outros tipos, caso haja
            }
        }
        return destinationObjects;
    }

    // Interface do MapStruct para mapeamento
    @Mapper(componentModel = "spring")
    public interface PersonMapper {

        // Mapeando Person para PersonVO
        @Mapping(source = "id", target = "key")
        PersonVO toPersonVO(Person person);

        // Mapeando PersonVO para Person
        @Mapping(source = "key", target = "id")
        Person toPerson(PersonVO personVO);
    }
}