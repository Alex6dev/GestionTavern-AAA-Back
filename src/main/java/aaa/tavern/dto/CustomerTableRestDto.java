package aaa.tavern.dto;

import aaa.tavern.entity.Customer;
import aaa.tavern.entity.TableRest;

public class CustomerTableRestDto {
    CustomerDto customer;
    TableRestDto tableRest;
    protected CustomerTableRestDto(){
        
    }
    public CustomerTableRestDto(Customer customer, TableRest tableRest){
        this.customer=new CustomerDto(customer);
        this.tableRest= new TableRestDto(tableRest);
    }
    public CustomerDto getCustomer() {
        return customer;
    }
    public TableRestDto getTableRest() {
        return tableRest;
    }
    

}
