package com.program.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.program.model.Category;
import com.program.exception.CategoryException;
import com.program.repository.CategoryRepository;
import com.program.service.CategoryService;

@RestController
//@RequestMapping(value = "/api")
public class CategoryController {

	@Autowired
	public CategoryService categoryservice;
	
	@Autowired
	public CategoryRepository categorydao;

		@GetMapping("/categories")
		public ResponseEntity<List<Category>> getAllCategories() throws CategoryException{
		List<Category> categorys =	categoryservice.getAllCategories();
			return new ResponseEntity<List<Category>>(categorys,HttpStatus.OK);
		}
		
		
		@PostMapping("/category/add")
		public ResponseEntity<Category> addNewCategory (@RequestBody Category category) throws CategoryException  
		{
			Category category1 = categoryservice.addNewCategory(category);
			System.out.println(category);
			return new ResponseEntity<Category>(category1, HttpStatus.OK);
			
		}
		
		@GetMapping("/category/getById/{Id}")
		public ResponseEntity<Category> getCategoryById(@PathVariable ("Id") Integer id ) throws CategoryException{
			Category category1=categoryservice.getCategoryById(id);
			return new ResponseEntity<Category>(category1,HttpStatus.OK);
		}
		
		@PutMapping("/category/update/{id}")
		public ResponseEntity<Category> updateCategoryById(@RequestBody Category category) throws CategoryException{
			Category category1=	categoryservice.updateCategoryById(category);

			return new ResponseEntity<Category>(category1,HttpStatus.OK);
		}
		
		@DeleteMapping("/category/delete/{Id}")
		public ResponseEntity<Category> deleteCategoryById(@PathVariable ("Id") Integer id ) throws CategoryException{
			Category category1=categoryservice.deleteCategoryById(id);
			return new ResponseEntity<Category>(category1,HttpStatus.OK);
		}
		

}


