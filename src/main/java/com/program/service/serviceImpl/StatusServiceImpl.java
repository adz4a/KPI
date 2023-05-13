package com.program.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.program.model.Category;
import com.program.model.Event;
import com.program.model.Status;
import com.program.model.Teacher;
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
	public Status addNewStatus(Status status) throws StatusException {

		if (status!=null) {
			String statusCategory = status.getStatusCategory();
			Category category = categoryRepository.findByName(statusCategory);
			List<Event> events = status.getEvents();

			if(!events.isEmpty()) {
				for(Event e: events) {
					e.setStatus(status);
				}
			}

			if (category != null) {
				status.setCategory(category);
				return statusRepository.save(status);

			}
			else {
				return null;
			}
		}
		else {
			throw new StatusException("Status details is Empty...");
		}
	}

	@Override
	public Status getStatusById(Integer PId) throws StatusException {
		Optional<Status> opt= statusRepository.findById(PId);
		if(opt.isPresent()) {
			return opt.get();
		}
		else {
			throw new StatusException("Status does not exist with Id :"+PId);

		}
	}

	@Override
	public Status updateStatusById(Integer id, Status status) throws StatusException {
		if (status == null) {
			throw new StatusException("Status details is Empty...");
		}

		Optional<Status> optionalStatus = statusRepository.findById(id);
		if (optionalStatus.isEmpty()) {
			throw new StatusException("Status with ID " + id + " does not exist.");
		}

		Status existingStatus = optionalStatus.get();
//		Setter
		existingStatus.setStatusName(status.getStatusName());

		List<Event> events = eventRepository.findByStatusId(existingStatus.getStatusId());
		List<Teacher> teachers = teacherRepository.findByStatusId(existingStatus.getStatusId());
		if (!events.isEmpty()){
			for (Event event : events)
//				Setter
				event.setEventStatus(existingStatus.getStatusName());
		}
		if (!teachers.isEmpty()){
			for (Teacher teacher : teachers) {
//				Setter
				teacher.setStatusName(existingStatus.getStatusName());
			}
		}

		Category category = categoryRepository.findByName(status.getStatusCategory());
		if (category != null) {
//			Setter
			existingStatus.setCategory(category);
			existingStatus.setStatusCategory(status.getStatusCategory());
			if (!events.isEmpty()){
				for (Event event : events)
//					Setter
					event.setEventCategory(existingStatus.getStatusCategory());
			}
			if (!teachers.isEmpty()){
				for (Teacher teacher : teachers) {
//					Setter
					teacher.setCategoryName(existingStatus.getStatusCategory());
				}
			}
		} else {
			throw new StatusException("Category with name " + status.getStatusName() + " doesn't exist!");
		}

		return statusRepository.save(existingStatus);
	}


	
	@Override
	public void deleteStatusById(Integer id) throws StatusException {

		Optional<Status> opt = statusRepository.findById(id);
		if (opt.isPresent()) {
			teacherRepository.resetStatusByStatusId(id);
			eventRepository.deleteByStatusId(id);
			statusRepository.deleteByStatusId(id);
		} else {
			throw new StatusException("Status does not exist with Id :" + id);
		}
			
	}
	
}
