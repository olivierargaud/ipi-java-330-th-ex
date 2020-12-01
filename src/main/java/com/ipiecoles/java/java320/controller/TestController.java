package com.ipiecoles.java.java320.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TestController
{

    @RequestMapping(value = "/toto", method = RequestMethod.GET)
//    @GetMapping(value = "/test") // equivalent
    public String index(final ModelMap model)
    {
        model.put("nom", "IPI");
        model.put("titi", "j'ai cru voir un gros minet");
        model.put("tata", "un <strong>autre</strong> text");
        return "test";
    }




}
