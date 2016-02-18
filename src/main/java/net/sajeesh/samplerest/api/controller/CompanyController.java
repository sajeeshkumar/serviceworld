/**
 * 
 */
package net.sajeesh.samplerest.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import net.sajeesh.samplerest.api.exception.ServiceException;
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
	
	/**
	 * Create a new company
	 * @param company company to create
	 * @param ucBuilder builder object
	 * @return response entity
	 */
	@RequestMapping(value = "/company", method = RequestMethod.POST)
    public ResponseEntity<Void> createCompany(@RequestBody Company company, UriComponentsBuilder ucBuilder) {
		try {
			companyService.add(company);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/company/{id}").buildAndExpand(company.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
	
	/**
	 * update company.
	 * @param id company id to update
	 * @param company company object to update
	 * @return response entity
	 */
	@RequestMapping(value = "/company/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateCompany(@PathVariable("id") long id, @RequestBody Company company) {
		if(companyService.get(id) == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		try {
			company.setId(id);
			companyService.update(company);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 * update owners
	 * @param id
	 * @param owners
	 * @return
	 */
	@RequestMapping(value = "/company/{id}/owner", method = RequestMethod.POST)
	public ResponseEntity<Void> updateOwner(@PathVariable("id") long id, @RequestBody List<String> owners) {
		if(companyService.get(id) == null){
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		try {
			companyService.addOwners(id, owners);
		} catch (ServiceException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
