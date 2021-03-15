package pl.coderslab.charity.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;


@Controller
public class HomeController {
    InstitutionService institutionService;
    DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @RequestMapping("home")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionService.getInstitution());
        model.addAttribute("donationsQuantity", donationService.getDonationsSum());
        model.addAttribute("donations", donationService.getDonationsQuantity());
        return "index";
    }
}
