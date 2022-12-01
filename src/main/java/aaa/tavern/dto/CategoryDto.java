package aaa.tavern.dto;

import java.util.Objects;

import aaa.tavern.entity.Category;

public class CategoryDto {

	private Integer id;
	private String name;

	protected CategoryDto() {

	}

	public CategoryDto(Category category) {
		this.id = category.getIdCategory();
		this.name = category.getName();
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
		CategoryDto other = (CategoryDto) obj;
		return Objects.equals(id, other.id);
	}

	// #region
	public String getName() {
		return name;
	}

	public Integer getId() {
		return id;
	}
	// #endregion
}
