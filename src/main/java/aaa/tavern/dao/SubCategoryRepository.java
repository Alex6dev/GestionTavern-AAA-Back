package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.SubCategory;


@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory, Integer> {
    
}
