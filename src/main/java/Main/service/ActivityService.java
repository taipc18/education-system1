package Main.service;

import java.util.List;

import Main.entity.Activity;


public interface ActivityService {

	
	Activity create(Activity act);

	List<Activity> findAllByOrderByIdDesc();
}
