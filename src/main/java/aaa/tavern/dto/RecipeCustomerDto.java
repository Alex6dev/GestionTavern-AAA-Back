package aaa.tavern.dto;

import aaa.tavern.entity.RecipeCustomer;

public class RecipeCustomerDto {

    private int customerId;
    private String recipeName;
    private int recipeId;
    private Long recipeStart;

    protected RecipeCustomerDto() {

    }

    public RecipeCustomerDto(int customerId, String recipeName,int recipeId, Long recipeStart) {
        this.customerId = customerId;
        this.recipeName = recipeName;
        this.recipeId=recipeId;
        if(recipeStart != null){
            this.recipeStart = recipeStart ;
        }else{
            this.recipeStart = null ;
        }
    }

    public RecipeCustomerDto(RecipeCustomer recipeCustomer) {
        this.customerId = recipeCustomer.getCustomer().getIdCustomer();
        this.recipeName = recipeCustomer.getRecipe().getName();
        this.recipeId=recipeCustomer.getRecipe().getId();
        if (recipeCustomer.getRecipeStart() != null) {
            this.recipeStart = recipeCustomer.getRecipeStart().getTime();
        } else {
            this.recipeStart = null;
        }
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public Long getRecipeStart() {
        return recipeStart;
    }

    public int getRecipeId() {
        return recipeId;
    }
    

}
