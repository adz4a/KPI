package com.program.service.serviceImpl;

import java.util.List;
import java.util.Optional;

import com.program.model.Status;
import com.program.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.exception.StatusException;

import com.program.repository.StatusRepository;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository statusRepository;

	// *************************** // 
	
	// BACKEND
	
	@Override
	public List<Status> getAllStatus() throws StatusException {
		return statusRepository.findAll();
	}
	
	@Override
	public Status addNewStatus(Status status) throws StatusException
	{
		Status status1= statusRepository.save(status);
		
		if(status1 != null) {
			return status1;
		}
		else {
			throw new StatusException("Product deatails is Empty...");
		}
	}

	@Override
	public Status getStatusById(Integer PId) throws StatusException {
		Optional<Status> opt= statusRepository.findById(PId);
		if(opt.isPresent()) {
			Status existingStatus =opt.get();
			return existingStatus;
		}
		else {
			throw new StatusException("Product does not exist with Id :"+PId);
			
		}
		
	}

	@Override
	public Status updateStatusById(Integer id) throws StatusException {
		Optional<Status> opt = statusRepository.findById(id);
		
		if (opt.isPresent())
		{
			Status p1 = statusRepository.save(id);
			return p1;
		}else {
	        throw new StatusException("Product with given id is not presesnt..."+ id);

		}
		
	}

	
	
	@Override
	public Status deleteStatusById(Integer Id) throws StatusException {
	
		Optional<Status> opt= statusRepository.findById(Id);
		if(opt.isPresent()) {
			Status existingStatus =opt.get();
			statusRepository.delete(existingStatus);
			
			return existingStatus;
		}
		else {
			throw new StatusException("Product does not exist with Id :"+Id);
			
		}
			
	}








	
}
