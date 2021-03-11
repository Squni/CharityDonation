package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

@Controller
public class FormController {
    CategoryRepository categoryRepository;
    InstitutionRepository institutionRepository;

    public FormController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("form")
    public String showForm(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryRepository.findAll());
        return "form";
    }

    @PostMapping("/form/confirm")
    public String formConfirmation() {
        return "form-confirmation";
    }


}
