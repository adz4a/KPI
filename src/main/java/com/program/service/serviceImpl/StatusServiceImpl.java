package com.program.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.program.model.Category;
import com.program.model.Event;
import com.program.model.Status;
import com.program.model.teacher.Teacher;
import com.program.repository.CategoryRepository;
import com.program.repository.EventRepository;
import com.program.repository.TeacherRepository;
import com.program.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.exception.StatusException;

import com.program.repository.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private EventRepository eventRepository;

	
	@Override
	public List<Status> getAllStatus() throws StatusException {
		return statusRepository.findAll();
	}
	
	@Override
	public Status addNewStatus(Integer categoryId,Status status) throws StatusException {

		if (status!=null) {
			Category category = categoryRepository.findByCategoryId(categoryId);
			List<Event> events = status.getEvents();

			if(!events.isEmpty()) {
				for(Event e: events) {
					e.setStatus(status);
				}
			}

			if (category != null) {
				status.setCategory(category);
				return statusRepository.save(status);

			}else {
				throw new StatusException("Category that you indicated doesn't exist! Category Id: " + categoryId);
			}
		}
		else {
			throw new StatusException("Status details is Empty...");
		}
	}

	@Override
	public Status getStatusById(Integer id) throws StatusException {
		Optional<Status> opt= statusRepository.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new StatusException("Status does not exist with Id : "+id);

		}
	}

	@Override
	public void updateStatusById(Integer id, Status status) throws StatusException {
		if (status == null) {
			throw new StatusException("Status details is Empty...");
		}

		Optional<Status> optionalStatus = statusRepository.findById(id);
		if (optionalStatus.isEmpty()) {
			throw new StatusException("Status with ID " + id + " does not exist.");
		}

		Status existingStatus = optionalStatus.get();

		Category category = categoryRepository.findByCategoryId(existingStatus.getCategoryId());
//		List<Teacher> teachers = teacherRepository.findByCategoryAndStatusName(existingStatus.getCategoryName(),existingStatus.getStatusName());

//		Setter
		existingStatus.setStatusName(status.getStatusName());

//		if (!teachers.isEmpty()){
//			for (Teacher teacher : teachers) {
////				Setter
//				teacher.setStatusName(existingStatus.getStatusName());
//			}
//		}

		if (category != null) {
//			Setter
			existingStatus.setCategory(category);

		} else {
			throw new StatusException("Category with name " + status.getStatusName() + " doesn't exist!");
		}

		statusRepository.save(existingStatus);
	}


	@Override
	public void deleteStatusById(Integer id) throws StatusException {

		Optional<Status> opt = statusRepository.findById(id);
		if (opt.isPresent()) {
//			Status existingStatus = opt.get();
//			teacherRepository.resetStatusName(existingStatus.getCategoryName(),existingStatus.getStatusName());
			eventRepository.deleteByStatusId(id);
			statusRepository.deleteByStatusId(id);
		} else {
			throw new StatusException("Status does not exist with Id : " + id);
		}
			
	}
	
}
