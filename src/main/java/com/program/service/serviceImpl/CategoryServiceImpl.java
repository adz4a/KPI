package com.program.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.program.model.Event;
import com.program.model.Status;
import com.program.model.teacher.Teacher;
import com.program.repository.EventRepository;
import com.program.repository.StatusRepository;
import com.program.repository.TeacherRepository;
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

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private TeacherRepository teacherRepository;


	@Override
	public List<Category> getAllCategories() throws CategoryException {
		return categoryRepository.findAll();
	}

	@Override
	public Category addNewCategory(Category category) throws CategoryException {


		List<Status> status = category.getStatuses();

		if (!status.isEmpty()) {
			for (Status p : status) {
				p.setCategory(category);
			}
		}

		return categoryRepository.save(category);
	}

	@Override
	public Category getCategoryById(Integer CId) throws CategoryException {
		Optional<Category> opt = categoryRepository.findById(CId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new CategoryException("Category does not exist with Id :" + CId);
		}
	}

	@Override
	public void updateCategoryById(Integer id, Category category) throws CategoryException {
		if (category == null) {
			throw new CategoryException("Category details is Empty...");
		}

		Optional<Category> optionalCategory = categoryRepository.findById(id);
		if (optionalCategory.isEmpty()) {
			throw new CategoryException("Category with ID " + id + " does not exist.");
		}
		Category existingCategory = optionalCategory.get();

		List<Status> statuses = statusRepository.findByCategoryId(existingCategory.getCategoryId());
//		List<Teacher> teachers = teacherRepository.findByCategoryName(existingCategory.getCategoryName());
//		Setter
		existingCategory.setCategoryName(category.getCategoryName());
		categoryRepository.save(existingCategory);
//		if (!statuses.isEmpty()){
////			for (Status status : statuses) {
//////				Setter
////				List<Event> events = eventRepository.findByStatusId(status.getStatusId());
//////				if (!events.isEmpty()){
//////					for (Event event : events)
////////						Setter
//////						event.setEventCategory(existingCategory.getCategoryName());
//////				}
//////				if (!teachers.isEmpty()){
//////					for (Teacher teacher : teachers) {
////////						Setter
//////						teacher.setCategoryName(existingCategory.getCategoryName());
//////					}
//////				}
////			}
//		} else {
//			return null;
//		}
	}


	@Override
	public void deleteCategoryById(Integer id) throws CategoryException {
		Optional<Category> opt = categoryRepository.findById(id);
		if (opt.isPresent()) {
			Category existingCategory = opt.get();
			List<Status> statuses = statusRepository.findByCategoryId(existingCategory.getCategoryId());
			if (!statuses.isEmpty()){
				for (Status status : statuses) {
					List<Event> events = eventRepository.findByStatusId(status.getStatusId());
//					List<Teacher> teachers = teacherRepository.findByCategoryName(existingCategory.getCategoryName());
					if (!events.isEmpty()){
							eventRepository.deleteByStatusId(status.getStatusId());
					}
//					if (!teachers.isEmpty()){
//						for (Teacher teacher : teachers) {
////							Set null
//							teacherRepository.resetCategoryNameAndStatusName(existingCategory.getCategoryName());
//						}
//					}
				}
				statusRepository.deleteByCategoryId(id);
			} else {
				throw new CategoryException("Statuses with this id empty");
			}
			categoryRepository.deleteByCategoryId(id);
		} else {
			throw new CategoryException("Category does not exist with Id :" + id);
		}
	}
	
}

	
	

