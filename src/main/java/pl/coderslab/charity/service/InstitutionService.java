package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;


import java.util.List;

@org.springframework.stereotype.Service
public class InstitutionService {
    private InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    public List<Institution> getInstitution() {
        return institutionRepository.findTop4ByOrderByIdDesc();
    }
}
