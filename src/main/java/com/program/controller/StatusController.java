package com.program.controller;

import java.util.List;

import com.program.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.program.exception.StatusException;
import com.program.repository.StatusRepository;
import com.program.service.StatusService;

@RestController
public class StatusController {

	@Autowired
	public StatusService statusService;
	
	@Autowired
	public StatusRepository statusRepository;

		
		@GetMapping("/statuses")
		public ResponseEntity<List<Status>> getAllStatus() throws StatusException
		{
			List<Status> status =	statusService.getAllStatus();
			return new ResponseEntity<List<Status>>(status,HttpStatus.OK);
		}
		
		@PostMapping("/createstatus")
		public ResponseEntity<Status> addNewStatus(@RequestBody Status status, Model m) throws StatusException
		{
			Status status1 = statusService.addNewStatus(status);
			System.out.println(status);
			return new ResponseEntity<Status>(status1, HttpStatus.OK);
		}
		
		@GetMapping("/status/getById/{Id}")
		public ResponseEntity<Status> getStatusById(@PathVariable ("Id") Integer id ) throws StatusException {
			Status status1 =statusService.getStatusById(id);
			return new ResponseEntity<Status>(status1,HttpStatus.OK);
		}
		
		@PutMapping("/status/update/{id}")
		public ResponseEntity<Status> updateStatusById(@PathVariable("Id") Integer id) throws StatusException {
			Status status1 =	statusService.updateStatusById(id);
			return new ResponseEntity<Status>(status1,HttpStatus.OK);
		}
		
		@DeleteMapping("/status/delete/{Id}")
		public ResponseEntity<Status> deleteStatusById(@PathVariable ("Id") Integer id ) throws StatusException {
			Status status1 =statusService.deleteStatusById(id);
			return new ResponseEntity<Status>(status1,HttpStatus.OK);
		}

	
}




