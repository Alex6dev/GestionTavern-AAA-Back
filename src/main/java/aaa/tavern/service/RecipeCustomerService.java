package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.RecipeCustomerRepository;
import aaa.tavern.dto.RecipeCustomerDto;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.RecipeCustomer;
import aaa.tavern.utils.ServiceUtil;

@Service
public class RecipeCustomerService {

    @Autowired
    RecipeCustomerRepository recipeCustomerRepository;

    @Autowired
    CustomerRepository customerRepository;

    /**
     * Return customer's recipesDto
     * 
     * @param customerId
     * @return List<RecipeDto>
     */
    public List<RecipeCustomerDto> loadRecipeByCustomer(int customerId) {
        Customer customer = ServiceUtil.getEntity(customerRepository, customerId);

        List<RecipeCustomer> listRecipeCustomer = recipeCustomerRepository.findByCustomer(customer);

        List<RecipeCustomerDto> listRecipesDto = new ArrayList<RecipeCustomerDto>();

        for (RecipeCustomer recipeCustomer : listRecipeCustomer) {
            RecipeCustomerDto recipeDto = new RecipeCustomerDto(recipeCustomer);
            listRecipesDto.add(recipeDto);
        }

        return listRecipesDto;
    }
}
