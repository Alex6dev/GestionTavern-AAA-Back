package aaa.tavern.dto.received;

public class AssignNewTableForCustomerDto {
    int customerId;
    int tableId;
    int managerId;
    
    protected AssignNewTableForCustomerDto(){
        
    }
    public AssignNewTableForCustomerDto(int customerId, int tableId,int managerId) {
        this.customerId = customerId;
        this.tableId = tableId;
        this.managerId = managerId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getTableId() {
        return tableId;
    }
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
    public int getManagerId() {
        return managerId;
    }
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    
}
