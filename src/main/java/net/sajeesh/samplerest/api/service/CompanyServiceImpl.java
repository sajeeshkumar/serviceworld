/**
 * 
 */
package net.sajeesh.samplerest.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import net.sajeesh.samplerest.api.exception.ServiceException;
import net.sajeesh.samplerest.api.model.Company;

/**
 * Implementation of Company service.
 * @author Sajeesh
 *
 */
@Service("companyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {
	
	/** Temporary place holders to be replaced by DAO and database code. */
	private static final AtomicLong ID_GENERATOR = new AtomicLong();
	private static final List<Company> COMPANIES = new ArrayList<Company>();

	/* (non-Javadoc)
	 * @see net.sajeesh.samplerest.api.service.CompanyService#get(java.lang.String)
	 */
	public Company get(long id) {
		for(final Company company : COMPANIES){
			if(company.getId() == id){
				return company;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see net.sajeesh.samplerest.api.service.CompanyService#getAll()
	 */
	public List<Company> getAll() {
		return COMPANIES;
	}

	/* (non-Javadoc)
	 * @see net.sajeesh.samplerest.api.service.CompanyService#add(net.sajeesh.samplerest.api.model.Company)
	 */
	public void add(Company company) throws ServiceException {
		validateCompany(company);
		company.setId(ID_GENERATOR.incrementAndGet());
		COMPANIES.add(company);
	}

	/* (non-Javadoc)
	 * @see net.sajeesh.samplerest.api.service.CompanyService#update(net.sajeesh.samplerest.api.model.Company)
	 */
	public void update(Company company) throws ServiceException {
		final Company found = get(company.getId());
		if(found == null){
			throw new ServiceException("Company not found by id: " + company.getId());
		}
		
		if(!StringUtils.isEmpty(company.getName())){
			found.setName(company.getName());
		}
		
		if(!StringUtils.isEmpty(company.getAddress())){
			found.setAddress(company.getAddress());
		}
		
		if(!StringUtils.isEmpty(company.getCity())){
			found.setCity(company.getCity());
		}
		
		if(!StringUtils.isEmpty(company.getCountry())){
			found.setCountry(company.getCountry());
		}
		
		if(!StringUtils.isEmpty(company.getEmail())){
			found.setEmail(company.getEmail());
		}
		
		if(!StringUtils.isEmpty(company.getPhoneNum())){
			found.setPhoneNum(company.getPhoneNum());
		}

		if(!CollectionUtils.isEmpty(company.getOwners())){
			found.getOwners().addAll(company.getOwners());
		}
	}

	/* (non-Javadoc)
	 * @see net.sajeesh.samplerest.api.service.CompanyService#addOwners(java.lang.String, java.util.List)
	 */
	public void addOwners(long id, List<String> owners) throws ServiceException {
		final Company found = get(id);
		if(found == null){
			throw new ServiceException("Company not found by id: " + id);
		}
		if(!CollectionUtils.isEmpty(owners)){
			found.getOwners().addAll(owners);
		}
	}
	
	/**
	 * Validates mandatory fields.
	 * @param company object to validate
	 * @throws ServiceException exception thrown in case of error.
	 */
	private void validateCompany(Company company) throws ServiceException {
		if (StringUtils.isEmpty(company.getName()) || StringUtils.isEmpty(company.getAddress())
				|| StringUtils.isEmpty(company.getCity()) || StringUtils.isEmpty(company.getCountry())) {
			throw new ServiceException("Name, Address, City and Country are mandatory fields...");
		}
	}

}
