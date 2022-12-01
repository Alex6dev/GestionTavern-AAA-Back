package aaa.tavern.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_customer")
public class RecipeCustomer implements Serializable{

	private static final long serialVersionUID = -1995248500696168494L;

	@EmbeddedId
    private RecipeCustomerKey id=new RecipeCustomerKey();

    @ManyToOne
    @MapsId("customerId")
    @JoinColumn(name = "customer_id")
    private Customer customer;
    
    @ManyToOne
    @MapsId("recipeId")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;
    
    @Column(name="recipe_start",nullable = true)
    private Timestamp recipeStart;
    
    public RecipeCustomer() {}

    public RecipeCustomer(Recipe recipe , Customer customer, Timestamp recipeStart){
        this.recipe= recipe;
        this.customer= customer;
        this.recipeStart = recipeStart;
    }

    /**
	 * Deux RecipeCustomer sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

            RecipeCustomer that = (RecipeCustomer) o;
        return Objects.equals(id, that.id);
    }

	/**
	 * L'identifiant définit le hash.
	 */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    //#region get/set
    public RecipeCustomerKey getId() {
        return id;
    }

    public void setId(RecipeCustomerKey id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Timestamp getRecipeStart() {
        return recipeStart;
    }

    public void setRecipeStart(Timestamp recipeStart) {
        this.recipeStart = recipeStart;
    }

    //#endregion

}
