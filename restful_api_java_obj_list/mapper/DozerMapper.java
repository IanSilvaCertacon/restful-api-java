package br.com.certacon.restful_api_java_obj_list.mapper;


import br.com.certacon.restful_api_java_obj_list.data.vo.v1.PersonVO;
import br.com.certacon.restful_api_java_obj_list.model.Person;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.List;


public class DozerMapper {

    public static ModelMapper mapper = new ModelMapper();

    static {
        mapper.addMappings(new PropertyMap<PersonVO, Person>() {
            @Override
            protected void configure() {
                skip(destination.getId());  // Ignora o mapeamento do campo 'id'
                map(source.getAddress(), destination.getAddress());
            }
        });
    }

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origin, Class<D> destination) {
        List<D> destinationObjects = new ArrayList<>();
        for (O o : origin) {
            destinationObjects.add(mapper.map(o, destination));
        }
        return destinationObjects;
    }

}
