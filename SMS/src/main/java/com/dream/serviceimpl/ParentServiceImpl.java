package com.dream.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dream.model.Parent;
import com.dream.model.User;
import com.dream.repository.ParentRepository;
import com.dream.service.ParentService;

@Service
public class ParentServiceImpl implements ParentService{

	private static final Logger logger = LogManager.getLogger(ParentServiceImpl.class);
	
	@Autowired
	private ParentRepository parentRepo;
	
	@Override
	public boolean save(Parent parent) {
		if(parent != null) {
			parentRepo.save(parent);
			logger.info("Parent saved successfully...");
			return true;
		}
		logger.info("Parent not saved successfully...");
		return false;
	}

	@Override
	public Parent getParentById(int id) {
		if(id > 0) {
			return parentRepo.findById(id);
		}
		return null;
	}

	@Override
	public List<Parent> get() {
		List<Parent> list = new ArrayList<>();
		parentRepo.findAll().forEach(l -> list.add(l));
		if(list.size()>0) {
			return list;
		}
		return null;
	}

	@Override
	public Page<Parent> getPaginated(Pageable pageable) {
		return parentRepo.findAll(pageable);
	}

	@Override
	public Parent findByUser(User user) {
		return parentRepo.findByUser(user);
	}

}
