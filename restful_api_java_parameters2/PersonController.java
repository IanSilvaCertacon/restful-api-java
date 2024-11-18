package br.com.certacon.restful_api_java_parameters2;

import br.com.certacon.restful_api_java_parameters.exceptions.UnsupportedMathOperationException;
import br.com.certacon.restful_api_java_parameters2.model.Person;
import br.com.certacon.restful_api_java_parameters2.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;



@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired //junto com o @Service ele linka um com o outro igual o comentario abaixo
    private PersonServices service;
    //private PersonServices service = new PersonServices();

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findbyId(@PathVariable(value = "id") String id) throws Exception {

         return service.findById(id);
    }
}

/*
private Double convertToDouble(String strNumber) {
        if(strNumber == null) return 0D;
        String number = strNumber.replaceAll(",", ".");
        if(isNumeric(number)) return Double.parseDouble(number);
            return 0D;
    }

    private boolean isNumeric(String strNumber) {
        if(strNumber == null) return false;
        String number = strNumber.replaceAll(",", ".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
        }

    }

 */

