package pl.coderslab.charity.service;


import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

@Service
public class DonationService {
    DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public Long getDonationsSum() {

        return ((donationRepository.getTotalQuantity() == null)?0:donationRepository.getTotalQuantity());
    }

    public Long getDonationsQuantity(){
        return donationRepository.findAll().stream().count();
    }

    public void saveDonation(Donation donation){ donationRepository.save(donation);}
}
