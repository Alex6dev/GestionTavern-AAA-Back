package aaa.tavern.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import aaa.tavern.dto.RecipeCustomerInventoryIngredientDto;
import aaa.tavern.dto.received.RequestRecipeDto;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.service.RecipeService;

@RestController
@RequestMapping("/api/game")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    /**
     * controller who creates the preparation of the recipe
     * 
     * @ResquestBody RequestRecipeDto with id manager, customer and recipe
     * @return RecipeCustomerInventoryIngredientDto with recipeCutomer information
     *         and manager inventory Update
     * @throws EntityNotFoundException exception if the id manager or recipe or
     *                                 customer is not in the database
     * @throws ForbiddenException      exception if the inventory does not allow the
     *                                 creation of this revenue
     */
    @PostMapping("/recipe/requestRecipe")
    public RecipeCustomerInventoryIngredientDto requestRecipe(@RequestBody RequestRecipeDto requestRecipeDto) {
        try {
            return recipeService.prepareRecipe(requestRecipeDto.getManagerId(), requestRecipeDto.getRecipeId(),
                    requestRecipeDto.getCustomerId());
        } catch (EntityNotFoundException e1) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "recette,client ou manager ne sont pas dans la BDD");
        } catch (ForbiddenException e2) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "l'inventaire du manager ne permet pas la création de la recette");
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN, e.getMessage());
        }
    }
}
