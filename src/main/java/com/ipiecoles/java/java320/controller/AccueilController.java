package com.ipiecoles.java.java320.controller;


import com.ipiecoles.java.java320.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AccueilController
{

    @Autowired
    EmployeRepository employeRepository;

//    @RequestMapping(value = "", method = RequestMethod.GET)
    @GetMapping(value = "/")
    public String accueil(final ModelMap model)
    {
//        Long nbEmployes = employeRepository.count();
//        model.put("nbEmployes",nbEmployes);

        model.put("nbEmployes",employeRepository.count());
        model.put("nbEmploye",employeRepository.count());// pas pratique car a repeter dans toutes les requests

        return "accueil";
    }



}
