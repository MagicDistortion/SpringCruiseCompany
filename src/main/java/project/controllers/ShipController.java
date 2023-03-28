package project.controllers;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.models.Routes;
import project.models.Ship;
import project.repos.ShipRepo;

import java.io.File;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10,      // 10MB
        maxRequestSize = 1024 * 1024 * 50)//50MB
@Controller
public class ShipController extends ParentController {
    @Autowired
    private ShipRepo shipRepo;
    private static final String SAVE_DIR = "images";

    @GetMapping("/ships_list")
    public String getAllShips(Model model, @PageableDefault(sort = "name", size = 5) Pageable pageable) {
        Page<Ship> shipsList = shipRepo.findAll(pageable);
        model.addAttribute("ships", shipsList);
        if (shipsList.isEmpty()) model.addAttribute("error", getMessage("Empty"));
        return "ships_list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add_ship")
    public String getAddShip(Ship ship, Model model) {
        return "admin/add_ship";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add_ship")
    public String postAddShip(@Valid Routes routes, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "admin/add_ship";



        return "redirect:/add_ship";
    }
}