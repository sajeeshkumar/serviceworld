/**
 * 
 */
package net.sajeesh.samplerest.api.service;

import java.util.List;

import net.sajeesh.samplerest.api.exception.ServiceException;
import net.sajeesh.samplerest.api.model.Company;

/**
 * Interface file that defines all methods related to CRUD operations
 * on Company.
 * 
 * @author Sajeesh
 *
 */
public interface CompanyService {

	/**
	 * Get company by id.
	 * @param id company id.
	 * @return company.
	 */
	public Company get(long id);
	
	/**
	 * get all companies.
	 * @return list of all companies.
	 */
	public List<Company> getAll();
	
	/**
	 * Add a new company.
	 * @param company company to add
	 * @throws ServiceException exception when trying to add.
	 */
	public void add(Company company) throws ServiceException;
	
	/**
	 * update a company.
	 * @param company company to update.
	 * @throws ServiceException
	 */
	public void update(Company company) throws ServiceException;
	
	/**
	 * Add owners to an existing company.
	 * @param id company id.
	 * @param owners owners to add.
	 */
	public void addOwners(long id, List<String> owners) throws ServiceException;
}
