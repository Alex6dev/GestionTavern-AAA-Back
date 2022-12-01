package aaa.tavern.dto.received;

public class CustomerFinishDto {
    int customerId;
    int managerId;
    protected CustomerFinishDto(){

    }
    
    public CustomerFinishDto(int customerId, int managerId) {
        this.customerId = customerId;
        this.managerId = managerId;
    }

    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getManagerId() {
        return managerId;
    }
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
    
}
