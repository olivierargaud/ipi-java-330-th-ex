package com.ipiecoles.java.java320.controller;


import com.ipiecoles.java.java320.model.Employe;
import com.ipiecoles.java.java320.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Controller
@RequestMapping(value = "/commercial")
public class CommercialController
{



    @Autowired
    EmployeRepository employeRepository;

//    MediaType.APPLICATION_FORM_URLENCODED_VALUE

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                                    //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String employeInf(final ModelMap model ,Employe employe)
    {

//        Employe employe = new Employe();
        model.put("emp",employe);



        return "detail";

    }


}
