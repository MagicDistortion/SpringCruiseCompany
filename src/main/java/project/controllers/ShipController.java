package project.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import project.dto.ShipDTO;
import project.models.Ship;
import project.repos.ShipRepo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Controller
public class ShipController extends ParentController {
    @Autowired
    private ShipRepo shipRepo;
    private static final String UPLOAD_DIRECTORY = "src/main/resources/static/images";
    @GetMapping("/ships_list")
    public String getAllShips(Model model, @PageableDefault(sort = "name", size = 5) Pageable pageable) {
        Page<Ship> shipsList = shipRepo.findAll(pageable);
        model.addAttribute("ships", shipsList);
        if (shipsList.isEmpty()) model.addAttribute("error", getMessage("Empty"));
        return "ships_list";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/add_ship")
    public String getAddShip(ShipDTO shipDTO, Model model) {
        return "admin/add_ship";
    }

    @Transactional
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/add_ship")
    public String uploadImage(Model model, @Valid ShipDTO shipDTO, BindingResult bindingResult
            , @RequestParam("image") MultipartFile file) throws IOException {
        if (bindingResult.hasErrors() || shipRepo.findByName(shipDTO.getName()) != null) {
            bindingResult.addError(new ObjectError("name", getMessage("ShipNameExist")));
            return "admin/add_ship";
        }
        Ship ship = new Ship(shipDTO);
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, ship.getImage());
        Files.write(fileNameAndPath, file.getBytes());
        shipRepo.save(ship);
        return "redirect:/add_ship";
    }
}