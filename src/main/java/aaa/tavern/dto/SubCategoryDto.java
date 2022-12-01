package aaa.tavern.dto;

import java.util.Objects;

import aaa.tavern.entity.SubCategory;

public class SubCategoryDto {

    private Integer id;

    private String name;

    private Integer idCategory;

    protected SubCategoryDto() {

    }

    public SubCategoryDto(SubCategory subCategory) {
        this.id = subCategory.getIdSubCategory();
        this.name = subCategory.getName();
        this.idCategory = subCategory.getCategory().getIdCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubCategoryDto other = (SubCategoryDto) obj;
        return Objects.equals(id, other.id);
    }

    // #region Get
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getIdCategory() {
        return idCategory;
    }
    // #endregion

}
