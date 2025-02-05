package com.example.CompanyService.Companyms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyServiceImpl companyService;

    @GetMapping("/get-all")
    public ResponseEntity<List<Company>> getAll()
    {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PutMapping("/update/id/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id,
                                                       @RequestBody Company company)
    {
        boolean flag = companyService.updateCompany(id,company);
        if(flag)
        {
            return ResponseEntity.ok("Company updated successfully");
        }
        return ResponseEntity.notFound().build();

    }

    @PostMapping("/create")
    public ResponseEntity<String>createCompany(@RequestBody Company company)
    {
        companyService.createCompany(company);
        return new ResponseEntity<>("Company created successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String>deleteCompany(@PathVariable Long id)
    {
        boolean flag = companyService.deleteCompany(id);
        if(flag) return new ResponseEntity<>("Company deleted successfully", HttpStatus.OK);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("get/id/{id}")
    public ResponseEntity<Company>getById(@PathVariable Long id)
    {
        Company company = companyService.getCompanyById(id);
        if(company != null) return new ResponseEntity<>(company, HttpStatus.OK);
        return ResponseEntity.notFound().build();
    }





}
