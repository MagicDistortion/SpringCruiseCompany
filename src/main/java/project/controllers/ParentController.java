package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import project.models.User;
import project.repos.UserRepo;


public abstract class ParentController {
    @Autowired
    protected MessageSource messageSource;
    @Autowired
    protected UserRepo userRepo;

    @ModelAttribute
    public void addUserToModel(@AuthenticationPrincipal User user, Model model) {
        if (user != null) {
            model.addAttribute("user", userRepo.findById(user.getId()).orElseThrow(RuntimeException::new));
        }
    }

    @ModelAttribute
    public void addLocaleToModel(Model model) {
        model.addAttribute("locale", LocaleContextHolder.getLocale().toString());
    }

    public String getMessage(String string) {
        return messageSource.getMessage(string, null, LocaleContextHolder.getLocale());
    }
}