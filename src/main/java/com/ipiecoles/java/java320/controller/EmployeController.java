package com.ipiecoles.java.java320.controller;


import com.ipiecoles.java.java320.model.Commercial;
import com.ipiecoles.java.java320.model.Employe;
import com.ipiecoles.java.java320.model.Manager;
import com.ipiecoles.java.java320.model.Technicien;
import com.ipiecoles.java.java320.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;


@Controller
@RequestMapping(value = "/employes")
public class EmployeController
{

    @Autowired
    EmployeRepository employeRepository;



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                                    //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String employeInf(final ModelMap model , @PathVariable(value = "id") Long id)
    {
        Optional<Employe>employeOptional =  employeRepository.findById(id);
        if (employeOptional.isEmpty())
        {
            throw new EntityNotFoundException("employé non trouvé");
        }

        Employe employe = employeOptional.get();
        model.put("emp",employe);
        model.put("toto","tito");
        model.put("nbEmploye",employeRepository.count());// pas pratique car a repeter dans toutes les requests

        return "detail";

    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                                    //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping(method = RequestMethod.GET,  produces = "application/json"  , value = "",params = "matricule")
    @ResponseStatus(HttpStatus.ACCEPTED) //202
    public String employesByMatricul(final ModelMap model , @RequestParam("matricule") String matricule)
    {
        Employe employe = employeRepository.findByMatricule(matricule);
        if(employe == null)
        {
            throw new EntityNotFoundException("aucun employé matricule : " + matricule + " n'a été trouvé.");
        }

        model.put("emp",employe);
        model.put("nbEmploye",employeRepository.count());// pas pratique car a repeter dans toutes les requests

        return "detail";
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                                    //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




//    @RequestMapping(method = RequestMethod.GET,  produces = "application/json"  , value = "",params = {"page","size"})
//    public String listEmployes
//            (
//                    final ModelMap model ,
//                    @RequestParam("page") Integer page,
//                    @RequestParam("size") Integer size,
//                    @RequestParam("sortProperty") String sortProperty,
//                    @RequestParam(value = "sortDirection",defaultValue = "ASC")String sortDirection
//            )
//    {
//        if(page <0 )
//        {
//            throw new IllegalArgumentException("le parametre page doit etre positif ou nul!");
//        }
//        if(size <=0 || size>50)
//        {
//            throw new IllegalArgumentException("le parametre page doit etre compris entre 0 et 50!");
//        }
//        if(!"ASC".equalsIgnoreCase(sortDirection)&&!"DESC".equalsIgnoreCase(sortDirection))
//        {
//            throw new IllegalArgumentException("le parametre page doit etre egal a ASC ou DESC");
//        }
//
//        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.fromString(sortDirection),sortProperty);
//
//        Page<Employe> listEmployes = employeRepository.findAll(pageRequest);
//
//        model.put("listeEmployes",listEmployes);
//
//        return "listeEmployes";
//    }


    @RequestMapping(method = RequestMethod.GET, value = "",params = {"page","size"})
    public String listEmployes
            (
                    final ModelMap model ,
                    @RequestParam Integer page,
                    @RequestParam Integer size,
                    @RequestParam String sortProperty,
                    @RequestParam String sortDirection
            )
    {
//        if(page <0 )
//        {
//            throw new IllegalArgumentException("le parametre page doit etre positif ou nul!");
//        }
//        if(size <=0 || size>50)
//        {
//            throw new IllegalArgumentException("le parametre page doit etre compris entre 0 et 50!");
//        }
//        if(!"ASC".equalsIgnoreCase(sortDirection)&&!"DESC".equalsIgnoreCase(sortDirection))
//        {
//            throw new IllegalArgumentException("le parametre page doit etre egal a ASC ou DESC");
//        }

        PageRequest pageRequest = PageRequest.of(page,size, Sort.Direction.fromString(sortDirection),sortProperty);

        Page<Employe> listEmployes = employeRepository.findAll(pageRequest);

        model.put("listeEmployes",listEmployes);
        model.put("pageNumber",page+1);
        model.put("start",page*size+1);
        model.put("end",page*size+listEmployes.getNumberOfElements());
        model.put("nextPage",page+1);
        model.put("previousPage",page-1);
        model.put("nbEmploye",employeRepository.count()); // pas pratique car a repeter dans toutes les requests

        return "listeEmployes";
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                                    //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//
//    @RequestMapping(method = RequestMethod.POST, value = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//    public String createEmploye(/*@RequestBody*/ Employe employe, final ModelMap model)
//    {
//
//        return "detail";
//    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////                                  formulaire nouveau commercial                                                     //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    @RequestMapping(method = RequestMethod.GET, value = "/new/commercial")
//    public String createCommercial(final ModelMap model)
//    {
//        Commercial commercial = new Commercial();
//        model.put("emp",commercial);
//
//        return "detail";
//    }
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////                                    formulaire nouveau technicien                                                   //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    @RequestMapping(method = RequestMethod.GET, value = "/new/technicien")
//    public String createTechnicien(final ModelMap model)
//    {
//        Technicien technicien = new Technicien();
//
//        model.put("emp",technicien);
//
//        return "detail";
//    }
//
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////                                     formulaire nouveau manager                                                     //
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//
//
//    @RequestMapping(method = RequestMethod.GET, value = "/new/manager")
//    public String createManager(final ModelMap model)
//    {
//        Manager manager = new Manager();
//
//        model.put("emp",manager);
//
//        return "detail";
//    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                     formulaire nouveau employe                                                     //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @RequestMapping(method = RequestMethod.GET, value = "/new/{typeEmploye}")
    public String newEmploye(final ModelMap model,@PathVariable String typeEmploye)
    {
        switch (typeEmploye.toLowerCase())
        {
            case "technicien": model.put("emp",new Technicien());
                break;
            case "commercial": model.put("emp",new Commercial());
                break;
            case "manager": model.put("emp",new Manager());
                break;
        }
//        Manager manager = new Manager();
//
//        model.put("emp",manager);

        return "detail";
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//                                                                                                                    //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    @RequestMapping(method = RequestMethod.POST, value = "/manager/{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createEmploye(/*@RequestBody*/ Employe employe , final ModelMap model)
    {
        model.put("emp",employeRepository.save(employe));


        return "detail";

//        return "listeEmployes";
    }














    //1 Request Mapping pour chaque type d'employé pour gérer la sauvegarde
    ///employes/commercial/{id}
    ///employes/technicien/{id}
    ///employes/manager/{id}
    //Suppression => rediriger vers la liste des employés
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public RedirectView deleteEmploye()
    {
        //Faire la suppression
        return new RedirectView("/employes");
    }







}
