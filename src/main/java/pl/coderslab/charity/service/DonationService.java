package pl.coderslab.charity.service;


import org.springframework.stereotype.Service;
import pl.coderslab.charity.repository.DonationRepository;

@Service
public class DonationService {
    DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Long getDonationsSum() {
        return donationRepository.getTotalQuantity();
    }

    public Long getDonations(){
        return donationRepository.findAll().stream().count();
    }
}
