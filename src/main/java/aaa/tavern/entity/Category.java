package aaa.tavern.entity;


import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="category")
public class Category {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;

    @Column(name="name")
    private String name;

	public Category() {}

    
    public Category(Integer idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }


    /**
	 * Deux Category sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Category that = (Category) o;
        return Objects.equals(idCategory, that.idCategory);
    }

	/**
	 * L'identifiant définit le hash.
	 */
    @Override
    public int hashCode() {
        return idCategory.hashCode();
    }
//#region Get/Set
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

    public Integer getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }
//#endregion
}