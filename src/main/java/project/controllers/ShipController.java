package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import project.models.Ship;
import project.repos.ShipRepo;

@Controller
public class ShipController extends ParentController {
    @Autowired
    private ShipRepo shipRepo;

    @GetMapping("/ships_list")
    public String getAllShips(Model model, @PageableDefault(sort = "name", size = 5) Pageable pageable) {
        Page<Ship> shipsList = shipRepo.findAll(pageable);
        model.addAttribute("ships", shipsList);
        if (shipsList.isEmpty()) model.addAttribute("error", getMessage("Empty"));
        return "ships_list";
    }
}