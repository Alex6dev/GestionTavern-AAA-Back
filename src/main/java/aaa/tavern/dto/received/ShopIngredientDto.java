package aaa.tavern.dto.received;

public class ShopIngredientDto {
    int idManager;
    ShopIngredientQuantityDto[] shopIngredientQuantity ;

    protected ShopIngredientDto(){

    }
    public ShopIngredientDto(int idManager, ShopIngredientQuantityDto[] shopIngredientQuantity) {
        this.idManager = idManager;
        this.shopIngredientQuantity = shopIngredientQuantity;
    }
    public int getIdManager() {
        return idManager;
    }
    public void setIdManager(int idManager) {
        this.idManager = idManager;
    }
    public ShopIngredientQuantityDto[] getShopIngredientQuantity() {
        return shopIngredientQuantity;
    }
    public void setShopIngredientQuantity(ShopIngredientQuantityDto[] shopIngredientQuantity) {
        this.shopIngredientQuantity = shopIngredientQuantity;
    }
}
