package com.program.service;

import java.util.List;

import com.program.model.Status;

import com.program.exception.StatusException;


public interface StatusService {

	  List<Status>  getAllStatus() throws StatusException;

	  Status addNewStatus(Status status)throws StatusException;

	  Status getStatusById(Integer PId)throws StatusException;

	  Status updateStatusById(Integer id) throws StatusException;

	  Status deleteStatusById(Integer Id) throws StatusException;

}
