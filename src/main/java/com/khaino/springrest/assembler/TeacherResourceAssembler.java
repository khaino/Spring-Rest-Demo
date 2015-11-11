package com.khaino.springrest.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import com.khaino.springrest.constant.Constant;
import com.khaino.springrest.controller.TeacherController;
import com.khaino.springrest.model.Subject;
import com.khaino.springrest.model.Teacher;



@Component
public class TeacherResourceAssembler {
	@Autowired
	private SubjectResourceAssembler subjectResourceAssembler;
	
	public Resource<Teacher> toTeacherResource(Teacher teacher) throws SecurityException {
        Resource<Teacher> resource = new Resource<Teacher>(teacher);
        
        resource.add(linkTo(TeacherController.class)
        			.slash(teacher.getTeacherId())
        			.withSelfRel());
      
        resource.add(linkTo(methodOn(TeacherController.class)
        			.getAllSjubjects(teacher.getTeacherId()))
        			.withRel(Constant.SUBJECTS_ASSIGNED));
           
        resource.add(linkTo(TeacherController.class)
	        		.slash(teacher.getTeacherId())
	        		.withRel(Constant.EDIT));
	        
        resource.add(linkTo(TeacherController.class)
	        		.slash(teacher.getTeacherId())
	        		.withRel(Constant.DELETE));
        
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
	
	
	

	public Resources<Resource<Subject>> toSubjectResourceList(List<Subject> subjectList, int teacherId) {
		
		List<Resource<Subject>> resourceList = new ArrayList<Resource<Subject>>();

		for (Subject subject : subjectList) {
			resourceList.add(subjectResourceAssembler.toSubjectResource(subject));
		}

		Resources<Resource<Subject>> resource = new Resources<Resource<Subject>>(resourceList);
		
		resource.add(linkTo(methodOn(TeacherController.class)
    			.getAllSjubjects(teacherId))
    			.withSelfRel());
		
		return resource;
	}
}
