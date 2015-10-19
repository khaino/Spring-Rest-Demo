package com.khaino.springrest.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import com.khaino.springrest.controller.TeacherController;
import com.khaino.springrest.model.Teacher;

@Component
public class TeacherResourceAssembler {
	
	public Resource<Teacher> toTeacherResource(Teacher teacher) {
        Resource<Teacher> resource = new Resource<Teacher>(teacher);
        resource.add(linkTo(TeacherController.class)
        				.slash(teacher.getTeacherId())
        				.withSelfRel());
        return resource;
    }
	
	public Resources<Resource<Teacher>> toTeacherResourceList(List<Teacher> teacherList){		
		List<Resource<Teacher>> resourceList = new ArrayList<Resource<Teacher>>();	
		
		for (Teacher teacher : teacherList) {			
			resourceList.add(toTeacherResource(teacher));
		}		
		Resources<Resource<Teacher>> resource = new Resources<Resource<Teacher>>(resourceList);
		resource.add(linkTo(TeacherController.class).withSelfRel());
		
		return resource;		
	}
}
