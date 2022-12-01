package aaa.tavern.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="recipe_ingredient")
public class RecipeIngredient implements Serializable {

	private static final long serialVersionUID = -8104241206055761620L;

	@EmbeddedId
    private RecipeIngredientKey id ;

    @ManyToOne
    @MapsId("idRecipe")
    @JoinColumn(name = "recipe_id")
    private Recipe recipe ;

    @ManyToOne 
    @MapsId("idIngredient")
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient ;

    @Column(name = "quantity")
    Integer quantity;

    public RecipeIngredient() {}

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, Integer quantity) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }

    /**
	 * Deux RecipeCustomer sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

            RecipeIngredient that = (RecipeIngredient) o;
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
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public RecipeIngredientKey getId() {
        return id;
    }

    public void setId(RecipeIngredientKey id) {
        this.id = id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //#endregion
}
