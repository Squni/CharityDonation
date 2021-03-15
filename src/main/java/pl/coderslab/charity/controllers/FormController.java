package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.DonationService;

import javax.validation.Valid;


@Controller
@RequestMapping("app")
public class FormController {
    CategoryRepository categoryRepository;
    InstitutionRepository institutionRepository;
    DonationService donationService;

    public FormController(CategoryRepository categoryRepository, InstitutionRepository institutionRepository, DonationService donationService) {
        this.categoryRepository = categoryRepository;
        this.institutionRepository = institutionRepository;
        this.donationService = donationService;
    }

    @GetMapping("form")
    public String showForm(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryRepository.findAll());
        return "form";
    }

    @PostMapping("form-confirm")
    public String formConfirmation(@Valid Donation donation, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:app/form";
        }
        donationService.saveDonation(donation);
        return "formConfirmation";
    }

}
