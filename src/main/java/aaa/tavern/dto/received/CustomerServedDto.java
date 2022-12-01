package aaa.tavern.dto.received;

public class CustomerServedDto {
    int customerId;

    protected CustomerServedDto(){

    }
    
    public CustomerServedDto(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
}
