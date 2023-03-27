package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import project.models.Cruise;
import project.models.Route;
import project.repos.CruiseRepo;
import project.repos.RouteRepo;

@Controller
public class CruiseController extends ParentController {
    @Autowired
    private CruiseRepo cruiseRepo;
    @Autowired
    private RouteRepo routeRepo;

    @GetMapping("/cruises_list")
    public String getAllCruises(Model model, @PageableDefault(sort = "cruiseName", size = 5) Pageable pageable) {
        Page<Cruise> cruisesList = cruiseRepo.findAll(pageable);
        model.addAttribute("cruises", cruisesList);
        if (cruisesList.isEmpty()) model.addAttribute("error", getMessage("Empty"));
        return "cruises_list";
    }

    @GetMapping("/cruise_details/{id}")
    public String getCruiseDetails(@PathVariable(value = "id") int id, Model model) {
        Cruise cruise = cruiseRepo.findById(id).orElseThrow(RuntimeException::new);
        Route route = routeRepo.findById(cruise.getRouteId()).orElseThrow(RuntimeException::new);
        model.addAttribute("cruise", cruise);
        model.addAttribute("route", route);
        return "cruise_details";
    }
}