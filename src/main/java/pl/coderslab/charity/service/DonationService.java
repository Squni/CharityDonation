package pl.coderslab.charity.service;


import pl.coderslab.charity.repository.DonationRepository;

@org.springframework.stereotype.Service
public class DonationService {
    DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Long getDonationsSum() {
        return donationRepository.findSumOfAllDonations();
    }

    public Long getDonations(){
        return donationRepository.findAll().stream().count();
    }
}
