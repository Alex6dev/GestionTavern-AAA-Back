package aaa.tavern.dto.received;

public class ShopIngredientQuantityDto {
    int idIngredient ;
    int quantity ;
    protected ShopIngredientQuantityDto(){
        
    }
    public ShopIngredientQuantityDto(int idIngredient, int quantity) {
        this.idIngredient = idIngredient;
        this.quantity = quantity;
    }
    public int getIdIngredient() {
        return idIngredient;
    }
    public void setIdIngredient(int idIngredient) {
        this.idIngredient = idIngredient;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
