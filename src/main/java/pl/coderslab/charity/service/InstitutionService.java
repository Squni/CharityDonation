package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


public class InstitutionService implements Service{
    private InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public List<Institution> getInstitution() {
        return institutionRepository.findTop4ByIdOrderByIdDesc();
    }
}
