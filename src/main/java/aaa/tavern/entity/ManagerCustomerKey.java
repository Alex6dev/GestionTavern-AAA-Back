package aaa.tavern.entity;

import java.io.Serializable;

import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.Column;

@Embeddable
public class ManagerCustomerKey implements Serializable{
    
	private static final long serialVersionUID = -1382558260368613954L;

	@Column(name = "customer_id")
    private Integer idCustomer;

    @Column(name = "manager_id")
    private Integer idManager;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

            ManagerCustomerKey that = (ManagerCustomerKey) o;
        return idCustomer == that.idCustomer && idManager == that.idManager;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCustomer, idManager);
    }
    
    //#region get/set
    public Integer getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Integer idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Integer getIdManager() {
        return idManager;
    }

    public void setIdManager(Integer idManager) {
        this.idManager = idManager;
    }
    //#endregion
    
}
