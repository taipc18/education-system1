package Main.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Main.dao.ActivityDAO;
import Main.entity.Activity;
import Main.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService{
	
	@Autowired
	ActivityDAO acd;


	@Override
	public Activity create(Activity act) {
		return acd.save(act);
	}

	@Override
	public List<Activity> findAllByOrderByIdDesc() {
		return acd.findAllByOrderByIdDesc();
	}

}
