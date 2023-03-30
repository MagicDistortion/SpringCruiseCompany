package project.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.dto.UserEditDTO;
import project.dto.UserPasswordDTO;
import project.dto.UserRegistrationDTO;
import project.models.Role;
import project.models.User;
import project.repos.UserRepo;

import java.util.Collections;

@Controller
public class UserController extends ParentController {
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected UserRepo userRepo;

    @GetMapping("/registration")
    public String registration(UserRegistrationDTO userRegistrationDTO) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult) {
        User userFromDb = userRepo.findByUsername(userRegistrationDTO.getUsername());
        if (bindingResult.hasErrors() || userFromDb != null) return "registration";
        User user = new User(userRegistrationDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Collections.singleton(Role.NOT_ASSIGNED));
        user.setActive(true);
        userRepo.save(user);
        return "redirect:/index";
    }

    private void setUserStates(UserEditDTO userEditDTO, User user) {
        userEditDTO.setSurname(user.getSurname());
        userEditDTO.setName(user.getName());
        userEditDTO.setUsername(user.getUsername());
        userEditDTO.setTel(user.getTel());
        userEditDTO.setDateOfBirth(user.getDateOfBirth());
    }

    @GetMapping("/edit_profile")
    public String editPage(UserEditDTO userEditDTO, UserPasswordDTO userPasswordDTO, @ModelAttribute User user) {
        setUserStates(userEditDTO, user);
        return "edit_profile";
    }

    @Transactional
    @PostMapping("/edit_profile")
    public String editUserProfile(@AuthenticationPrincipal User user, UserPasswordDTO userPasswordDTO
            , @Valid UserEditDTO userEditDTO, BindingResult bindingResult, Model model) {
        User userFromDB = userRepo.findById(user.getId()).orElseThrow(RuntimeException::new);
        if (userRepo.findByUsername(userEditDTO.getUsername()) != null
                && !userEditDTO.getUsername().equals(userFromDB.getUsername())) {
            bindingResult.addError(new ObjectError("username", getMessage("UsernameAlreadyExist")));
            userEditDTO.setUsername(userFromDB.getUsername());
        }
        if (bindingResult.hasErrors()) return "edit_profile";
        userFromDB.setSurname(userEditDTO.getSurname());
        userFromDB.setName(userEditDTO.getName());
        userFromDB.setUsername(userEditDTO.getUsername());
        userFromDB.setTel(userEditDTO.getTel());
        userFromDB.setDateOfBirth(userEditDTO.getDateOfBirth());
        userRepo.saveAndFlush(userFromDB);
        model.addAttribute("success", getMessage("SuccessfulChanged"));
        return "edit_profile";
    }

    @Transactional
    @PostMapping("/edit_password")
    public String editUserPassword(UserEditDTO userEditDTO, @AuthenticationPrincipal User user, @Valid UserPasswordDTO userPasswordDTO
            , BindingResult bindingResult, Model model) {
        User userFromDB = userRepo.findById(user.getId()).orElseThrow(RuntimeException::new);
        setUserStates(userEditDTO, userFromDB);
        if (bindingResult.hasErrors()) return "edit_profile";
        userFromDB.setPassword(passwordEncoder.encode(userPasswordDTO.getPassword()));
        userRepo.saveAndFlush(userFromDB);
        model.addAttribute("success", getMessage("SuccessfulChanged"));
        return "edit_profile";
    }
}