package com.program.controller;

import java.util.List;

import com.program.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
		@PreAuthorize("hasRole('ADMIN') or hasRole('OBSERVER')")
		public ResponseEntity<List<Status>> getAllStatuses() throws StatusException
		{
			List<Status> statuses =	statusService.getAllStatus();
			return new ResponseEntity<List<Status>>(statuses,HttpStatus.OK);
		}

		@PostMapping("category/{Id}/status/add")
		@PreAuthorize("hasRole('ADMIN')")
		public HttpEntity<? extends Object> addNewStatus(@PathVariable ("Id") Integer id,@RequestBody Status status) throws StatusException
		{
			Status status1 = statusService.addNewStatus(id,status);

			if (status1==null) {
				return new ResponseEntity<String>("Status details is empty or category which you indicated does not exist!", HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Status>(status1, HttpStatus.OK);
			}
		}

		@GetMapping("category/status/getById/{Id}")
		public ResponseEntity<Status> getStatusById(@PathVariable ("Id") Integer id ) throws StatusException {
			Status status1 =statusService.getStatusById(id);
			return new ResponseEntity<Status>(status1,HttpStatus.OK);
		}

		@GetMapping("category/status/update/{Id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<Status> updateStatusById(@PathVariable ("Id") Integer id ) throws StatusException {
		Status status1 =statusService.getStatusById(id);
		return new ResponseEntity<Status>(status1,HttpStatus.OK);
		}

		@PutMapping("category/status/update/{id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<Object> updateStatusById(@PathVariable Integer id,@RequestBody Status status) throws StatusException {
			try {
				Status updatedStatus = statusService.updateStatusById(id, status);
				return ResponseEntity.ok(updatedStatus);
			} catch (StatusException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}

		@DeleteMapping("category/status/delete/{Id}")
		@PreAuthorize("hasRole('ADMIN')")
		public ResponseEntity<String> deleteStatusById(@PathVariable ("Id") Integer id ) throws StatusException {
			statusService.deleteStatusById(id);
			return new ResponseEntity<String>("Status with this Id deleted",HttpStatus.OK);

		}



}




