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
		
		

	}

	/* (non-Javadoc)
	 * @see net.sajeesh.samplerest.api.service.CompanyService#addOwners(java.lang.String, java.util.List)
	 */
	public void addOwners(String id, List<String> owners) {
		// TODO Auto-generated method stub

	}

}
