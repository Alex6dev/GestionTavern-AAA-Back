package aaa.tavern.dto.received;

public class RequestRecipeDto {
    int managerId;
    int recipeId ;
    int customerId;
    
    protected RequestRecipeDto(){

    }

    public RequestRecipeDto(int managerId, int recipeId, int customerId) {
        this.managerId = managerId;
        this.recipeId = recipeId;
        this.customerId = customerId;
    }


    public int getManagerId() {
        return managerId;
    }
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    public int getRecipeId() {
        return recipeId;
    }
    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
}
