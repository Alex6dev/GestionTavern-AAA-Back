package aaa.tavern.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.CustomerDto;
import aaa.tavern.dto.CustomerTableRestDto;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.dto.received.AssignNewTableForCustomerDto;
import aaa.tavern.dto.received.CustomerFinishDto;
import aaa.tavern.dto.received.CustomerServedDto;
import aaa.tavern.dto.received.ManagerIdDto;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.CustomerManagementService;

@RestController
@RequestMapping("/api/game")
public class CustomerManagementController {
	@Autowired
	private CustomerManagementService customerManagementService;


	/**
	 * Controller that allows to create a new customer
	 * 
	 * @requestBody ManagerIdDto Object that contains id of the manager to whom we
	 *              create a new customer
	 * @return CustomerDto Object that contains the information of the new customer
	 * @throws EntityNotFoundException exception if the id manager is not in the
	 *                                 database
	 */
	@PostMapping("/customerManagement/newCustomer")
	public CustomerDto getNewCustomer(@RequestBody ManagerIdDto managerIdDto) {
		try {

			return customerManagementService.getNewCustomer(managerIdDto.getManagerId());

		} catch (EntityNotFoundException e) {

			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Ce manager n'existe pas ");
		} catch (Exception e) {
			throw new ResponseStatusException(
					HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	/**
	 * Controller that assigns a table to a customer
	 * 
	 * @ResquestBody AssignNewTableForCustomerDto with id customer and tableRest
	 * @return CustomerTableRestDto with Customer and TableRest update
	 * @throws EntityNotFoundException exception if the id customer or table are not
	 *                                 in the database
	 * @throws ForbiddenException      exception if there is no more room on the
	 *                                 table
	 */
	@PostMapping("/customerManagement/customerAssignTable")
	public CustomerTableRestDto assignNewTableForCustomer(
			@RequestBody AssignNewTableForCustomerDto assignNewTableForCustomerDto) {
		try {
			return customerManagementService.assignNewTable(assignNewTableForCustomerDto.getCustomerId(),
					assignNewTableForCustomerDto.getTableId(),assignNewTableForCustomerDto.getManagerId());

		} catch (EntityNotFoundException e) {

			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Customer ou table non trouvé dans la BDD");
		} catch (ForbiddenException e1) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Il ne reste plus de place !");
		} catch (Exception e) {
			throw new ResponseStatusException(
					HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

	/**
	 * Controller which indicates that the customer is served
	 * 
	 * @param customerId id customer who starts to eat
	 * @return CustomerDto whith customer update
	 * @throws EntityNotFoundException exception if the id customer are not in the
	 *                                 database
	 */
	@PostMapping("/customerManagement/customerServed")
	public CustomerDto customerServed(@RequestBody CustomerServedDto customerServedDto) {
		try {
			return customerManagementService.customerServed(customerServedDto.getCustomerId());

		} catch (EntityNotFoundException e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Customer non trouvé dans la BDD");
		}
	}

	/**
	 * Controller that allows to get the money from the recipe consume from the
	 * customer
	 * 
	 * @param customerId id customer who finished the recipe
	 * @param managerId  id manager who should be given the money
	 * @return ManagerDto with manager update
	 * @throws EntityNotFoundException exception if the id customer or manager are
	 *                                 not in the database.
	 * @throws ForbiddenException      exception if the consumption time is not good
	 */
	@PostMapping("/customerManagement/customerFinish")
	public ManagerDto customerFinish(@RequestBody CustomerFinishDto customerFinishDto) {
		try {
			return customerManagementService.customerFinishRecipe(customerFinishDto.getCustomerId(),
					customerFinishDto.getManagerId());
		} catch (EntityNotFoundException e) {

			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Customer ou manager non trouvé dans la BDD");
		} catch (ForbiddenException e1) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST, "Le temps n'est pas terminé !");
		} catch (Exception e) {
			throw new ResponseStatusException(
					HttpStatus.FORBIDDEN, e.getMessage());
		}
	}

}
