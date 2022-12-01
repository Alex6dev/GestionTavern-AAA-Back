package aaa.tavern.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;



@Entity
@Table(name = "manager_customer")
public class ManagerCustomer implements Serializable{
    
 
	private static final long serialVersionUID = 6590933195588818011L;

	@EmbeddedId
    private ManagerCustomerKey id = new ManagerCustomerKey();

    @ManyToOne
    @MapsId("idManager")
    @JoinColumn(name = "manager_id")
    private Manager manager;

    @ManyToOne
    @MapsId("idCustomer")
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public ManagerCustomer(Manager manager, Customer newCustomer) {
        this.manager=manager;
        this.customer=newCustomer;
    }

    public ManagerCustomer(){
    	
    }
    /**
	 * Deux ManagerCustomer sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

            ManagerCustomer that = (ManagerCustomer) o;
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
    public ManagerCustomerKey getId() {
        return id;
    }

    public void setId(ManagerCustomerKey id) {
        this.id = id;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    //#endregion

    
}
