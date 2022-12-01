package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.ManagerCustomerRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dto.CustomerDto;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.ManagerCustomer;
import aaa.tavern.utils.ServiceUtil;

@Service
public class ManagerCustomerService {

	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private ManagerCustomerRepository managerCustomerRepository;

	/**
	 * Returns a list containing manager's customers converted to Dto
	 * 
	 * @param idManager
	 * @return List<CustomerDto>
	 * @throws Exception
	 */
	public List<CustomerDto> loadCustomerByManager(int idManager) throws Exception {
		Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		if (currentPrincipalName.equals(manager.getPlayer().getEmail())) {

			List<ManagerCustomer> listManagerCustomer = managerCustomerRepository.findByManager(manager);

			List<Customer> listCustomers = new ArrayList<Customer>();
			List<CustomerDto> listCustomersDto = new ArrayList<CustomerDto>();

			for (ManagerCustomer managerCustomer : listManagerCustomer) {
				listCustomers.add(managerCustomer.getCustomer());
			}

			for (Customer customer : listCustomers) {
				CustomerDto customerDto = new CustomerDto(customer);
				listCustomersDto.add(customerDto);
			}

			return listCustomersDto;
		} else {
			throw new Exception("Le manager ne correspond pas a votre compte");
		}
	}

}
