package com.example.CompanyService.Companyms;

import com.example.CompanyService.Companyms.clients.ReviewClient;
import com.example.CompanyService.Companyms.dto.ReviewMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private ReviewClient reviewClient;

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> optionalCompany = companyRepo.findById(id);
        if (optionalCompany.isPresent())
        {
            Company updatedCompany = optionalCompany.get();
            updatedCompany.setName(company.getName());
            updatedCompany.setDescription(company.getDescription());
            companyRepo.save(updatedCompany);
            return true;
        }

        return false;
    }

    @Override
    public void createCompany(Company company) {
        companyRepo.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        if (companyRepo.existsById(id))
        {
            companyRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepo.findById(id).get();
    }

    @Override
    public void updateCompanyRating(ReviewMessage reviewMessage) {
        System.out.println(reviewMessage.getDescription());
        Company company = companyRepo.findById(reviewMessage.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Double averageRating = reviewClient.getAverageRatingOfCompany(
                reviewMessage.getId());
        company.setRating(averageRating);
        companyRepo.save(company);

    }


}
