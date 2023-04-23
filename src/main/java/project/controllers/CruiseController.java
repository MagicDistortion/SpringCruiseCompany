package project.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.models.Cruise;
import project.models.Routes;
import project.repos.CruiseRepo;
import project.repos.RouteRepo;
import project.repos.UserRepo;

@Controller
public class CruiseController extends ParentController {
    private final CruiseRepo cruiseRepo;
    private final RouteRepo routeRepo;

    @Autowired
    public CruiseController(MessageSource messageSource, UserRepo userRepo, CruiseRepo cruiseRepo, RouteRepo routeRepo) {
        super(messageSource, userRepo);
        this.cruiseRepo = cruiseRepo;
        this.routeRepo = routeRepo;
    }

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
        Routes routes = routeRepo.findById(cruise.getRouteId()).orElseThrow(RuntimeException::new);
        model.addAttribute("cruise", cruise);
        model.addAttribute("route", routes);
        return "cruise_details";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add_cruise")
    public String getAddCruise(Model model) {
        return "admin/add_cruise";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add_cruise")
    public String postAddCruise(Model model) {
        return "admin/add_cruise";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add_route")
    public String getAddRoute(Routes routes, Model model) {
        return "admin/add_route";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add_route")
    public String postAddRoute(@Valid Routes routes, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "admin/add_route";
        routeRepo.save(routes);
        return "redirect:/add_route";
    }
}