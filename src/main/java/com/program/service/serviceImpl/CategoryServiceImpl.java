package com.program.service.serviceImpl;


import java.util.List;
import java.util.Optional;

import com.program.model.Status;
import com.program.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.model.Category;
import com.program.exception.CategoryException;
import com.program.repository.CategoryRepository;

@Service
public class  CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	
	// BACKEND
	
	@Override
	public List<Category> getAllCategories() throws CategoryException {
		return categoryRepository.findAll();
	}

	@Override
	public Category addNewCategory(Category category) throws CategoryException {
		

		
		List<Status> status = category.getStatuses();
		
		if(!status.isEmpty()) {
			for(Status p: status) {
				p.setCategory(category);
			}
		}
		Category addCategory= categoryRepository.save(category);
		
		if(addCategory != null) {
			return addCategory;
		}
		else {
			throw new CategoryException("Product details is Empty...");
		}
		
		}
	
	@Override
	public Category updateCategoryById(Category category) throws CategoryException {
		
			Optional<Category> opt = categoryRepository.findById(category.getCategoryId());
			
			if (opt.isPresent())
			{
				Category c1 = categoryRepository.save(category);
				return c1;
			}else {
		        throw new CategoryException("Category with given id is not present........");

			}
	}

	@Override
	public Category getCategoryById(Integer CId) throws CategoryException {
		Optional<Category> opt= categoryRepository.findById(CId);
		if(opt.isPresent()) {
			Category  existingCategory=opt.get();
			return existingCategory;
		}
		else {
			throw new CategoryException("Category does not exist with Id :"+CId);
			
		}
	}
	

	@Override
	public Category deleteCategoryById(Integer CId) throws CategoryException {
		
			Optional<Category> opt= categoryRepository.findById(CId);
			if(opt.isPresent()) {
				Category existingCategory =opt.get();
				categoryRepository.delete(existingCategory);
				
				return existingCategory;
			}
			else {
				throw new CategoryException("Category does not exist with Id :"+CId);
				
			}
	}
	
}

	
	

