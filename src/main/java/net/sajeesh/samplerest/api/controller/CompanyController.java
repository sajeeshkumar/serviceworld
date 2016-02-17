/**
 * 
 */
package net.sajeesh.samplerest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import net.sajeesh.samplerest.api.model.Company;
import net.sajeesh.samplerest.api.service.CompanyService;

/**
 * 
 * Front controller that handles all the REST API calls associated with model
 * Company.
 * 
 * @author Sajeesh
 *
 */
@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	/**
	 * Handler method to get company by id.
	 * 
	 * @param id
	 *            company id to search for.
	 * @return the company object or not found.
	 */
	@RequestMapping(value = "/company/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> getCompany(@PathVariable("id") long id) {
		final Company company = companyService.get(id);
		if (company == null) {
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Company>(company, HttpStatus.OK);
	}
	
	/**
	 * Handler method to get list of companies.
	 * @return list of companies.
	 */
	@RequestMapping(value="/company", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Company>> getCompanies() {
		final List<Company> companies = companyService.getAll();
		if(companies == null || companies.size() == 0){
			return new ResponseEntity<List<Company>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Company>>(companies, HttpStatus.OK);
	}
}
