package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.ManagerCustomerRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dto.CustomerDto;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.ManagerCustomer;
import aaa.tavern.entity.TableRest;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class CustomerManagerServiceTest {

	@MockBean
	private ManagerRepository managerRepository;

	@MockBean
	private ManagerCustomerRepository managerCustomerRepository;

	@MockBean
	private CustomerRepository customerRepository;

	@Autowired
	private ManagerCustomerService managerCustomerService;

	@Test
	public void whenLoadCustomer_thenReturnListCustomerDto() throws Exception {
		Manager manager = new Manager();

		Mockito.when(managerRepository.findById(0)).thenReturn(Optional.of(manager));

		List<ManagerCustomer> listManagerCustomers = new ArrayList<ManagerCustomer>();
		TableRest tableRest = new TableRest();
		tableRest.setIdTable(1);
		Customer customer1 = new Customer();
		customer1.setTableRest(tableRest);
		Customer customer2 = new Customer();
		customer2.setTableRest(tableRest);
		Customer customer3 = new Customer();
		customer3.setTableRest(tableRest);
		ManagerCustomer managerCustomer1 = new ManagerCustomer(manager, customer1);
		ManagerCustomer managerCustomer2 = new ManagerCustomer(manager, customer2);
		ManagerCustomer managerCustomer3 = new ManagerCustomer(manager, customer3);
		listManagerCustomers.add(managerCustomer1);
		listManagerCustomers.add(managerCustomer2);
		listManagerCustomers.add(managerCustomer3);

		Mockito.when(managerCustomerRepository.findByManager(manager)).thenReturn(listManagerCustomers);

		List<Customer> listCustomers = new ArrayList<Customer>();
		List<CustomerDto> listCustomersDto = new ArrayList<CustomerDto>();
		for (ManagerCustomer managerCustomer : listManagerCustomers) {
			listCustomers.add(managerCustomer.getCustomer());
		}

		for (Customer customer : listCustomers) {
			CustomerDto customerDto = new CustomerDto(customer);
			listCustomersDto.add(customerDto);
		}

		List<CustomerDto> listCustomersDto2 = managerCustomerService.loadCustomerByManager(0);

		assertEquals(listCustomersDto, listCustomersDto2);
	}
}
