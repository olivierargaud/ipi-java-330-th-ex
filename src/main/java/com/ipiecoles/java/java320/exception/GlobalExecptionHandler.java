package com.ipiecoles.java.java320.exception;


import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;


@RestControllerAdvice
public class GlobalExecptionHandler
{





    @ExceptionHandler(EntityNotFoundException.class)    // quand on rencontre une exection de ce type
    @ResponseStatus(HttpStatus.NOT_FOUND)               // on renvoi ce type de code defaut
    public String handleEntityNotFoundException(EntityNotFoundException entityNotFoundException)
    {
        return entityNotFoundException.getMessage();    // on renvoi ce message dans le corps de la reponse
    }






    @ExceptionHandler(MethodArgumentTypeMismatchException.class)    // quand on rencontre une exection de ce type
    @ResponseStatus(HttpStatus.BAD_REQUEST)               // on renvoi ce type de code defaut
    public String handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException methodArgumentTypeMismatchException)
    {
//        return methodArgumentTypeMismatchException.getMessage();    // on renvoi ce message dans le corps de la reponse
        return "le parametre "+methodArgumentTypeMismatchException.getName()+" a une valeur incorect : "+methodArgumentTypeMismatchException.getValue();
    }




    @ExceptionHandler(IllegalArgumentException.class)    // quand on rencontre une exection de ce type
    @ResponseStatus(HttpStatus.BAD_REQUEST)               // on renvoi ce type de code defaut
    public String handleIllegalArgumentException(IllegalArgumentException illegalArgumentException )
    {
        return illegalArgumentException.getMessage();    // on renvoi ce message dans le corps de la reponse
    }








    @ExceptionHandler(EntityExistsException.class)    // quand on rencontre une exection de ce type
    @ResponseStatus(HttpStatus.CONFLICT)               // on renvoi ce type de code defaut
    public String handleEntityExistsException(EntityExistsException entityExistsException)
    {
        return entityExistsException.getMessage();    // on renvoi ce message dans le corps de la reponse
    }







    @ExceptionHandler(PropertyReferenceException.class)    // quand on rencontre une exection de ce type
    @ResponseStatus(HttpStatus.BAD_REQUEST)               // on renvoi ce type de code defaut
    public String handlePropertyReferenceException(PropertyReferenceException propertyReferenceException)
    {
        return "la propriete "+propertyReferenceException.getPropertyName()+" n'existe pas ";    // on renvoi ce message dans le corps de la reponse
    }





}
