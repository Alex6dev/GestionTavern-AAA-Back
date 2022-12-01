package aaa.tavern.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ingredient")
public class Ingredient {
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name="name")
	private String name;

	@Column(name="level")
	private Integer level;

	@Column(name="buying_price")
	private Integer buyingPrice;

	@ManyToOne
    @JoinColumn(name = "subcategory_id")
	private SubCategory subCategory;

	public Ingredient() {}

	public Ingredient(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Ingredient(int id, String name, int level, int buyingPrice, SubCategory subCategory) {
		this.id = id;
		this.name = name;
		this.level = level ;
		this.buyingPrice = buyingPrice ;
		this.subCategory = subCategory ;
	}

	/**
	 * Deux Ingredients sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Ingredient that = (Ingredient) o;
        return Objects.equals(id, that.id);
    }

	/**
	 * L'identifiant définit le hash.
	 */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

	// #region Get/Set
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBuyingPrice() {
		return buyingPrice;
	}

	public void setBuyingPrice(Integer buyingPrice) {
		this.buyingPrice = buyingPrice;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	// #endregion

}