package com.program.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	public CategoryRepository categoryRepository;

		@GetMapping("/categories")
		@PreAuthorize("hasRole('ADMIN') or hasRole('OBSERVER')")
		public ResponseEntity<List<Category>> getAllCategories() throws CategoryException{
		List<Category> categories =	categoryservice.getAllCategories();
			return new ResponseEntity<List<Category>>(categories,HttpStatus.OK);
		}


		@PostMapping("/category/add")
		@PreAuthorize("hasRole('ADMIN')")
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

		@GetMapping("/category/update/{Id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<Category> updateCategoryById(@PathVariable ("Id") Integer id ) throws CategoryException{
		Category category1=categoryservice.getCategoryById(id);
		return new ResponseEntity<Category>(category1,HttpStatus.OK);
		}

		@PutMapping("/category/update/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<Category> updateCategoryById(@PathVariable Integer id,@RequestBody Category category) throws CategoryException{
			Category category1=	categoryservice.updateCategoryById(id, category);
			return new ResponseEntity<Category>(category1,HttpStatus.OK);
		}

		@DeleteMapping("/category/delete/{Id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<Object> deleteCategoryById(@PathVariable ("Id") Integer id) throws CategoryException{
			categoryservice.deleteCategoryById(id);
			return new ResponseEntity<>("Category with this id deleted",HttpStatus.OK);
		}


}


