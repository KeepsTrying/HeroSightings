/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sguild.superhumansightings.controller;

import com.sguild.superhumansightings.dto.Sighting;
import com.sguild.superhumansightings.servicelayer.Service;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class TrialController {
    
    private Service service;
    
    @Inject
    public TrialController(Service service) {
        this.service = service;
    }
    
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String trying(HttpServletRequest request, Model model) {
        
        List<Sighting> recentSightings = service.getRecentSightings();
        
        model.addAttribute("sightings", recentSightings);
        
        return "landing";
    }
    
}
