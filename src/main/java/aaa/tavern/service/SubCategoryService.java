package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.SubCategoryRepository;
import aaa.tavern.dto.SubCategoryDto;
import aaa.tavern.entity.SubCategory;

@Service
public class SubCategoryService {

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	/**
	 * Load All SubCategory
	 * 
	 * @return List<SubCategoryDto>
	 */
	public List<SubCategoryDto> loadAllSubCategory() {

		Iterable<SubCategory> iterableSubCategory = subCategoryRepository.findAll();

		if (!iterableSubCategory.iterator().hasNext()) {
			throw new EntityNotFoundException();
		}

		List<SubCategoryDto> listSubCategoryDto = new ArrayList<SubCategoryDto>();
		for (SubCategory subCategory : iterableSubCategory) {
			SubCategoryDto subCategoryDto = new SubCategoryDto(subCategory);
			listSubCategoryDto.add(subCategoryDto);
		}

		return listSubCategoryDto;
	}
}
