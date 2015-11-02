package com.khaino.springrest.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import com.khaino.springrest.controller.SubjectController;
import com.khaino.springrest.controller.TeacherController;
import com.khaino.springrest.model.Subject;
import com.khaino.springrest.model.Teacher;

@Component
public class SubjectResourceAssembler {
	
	public Resource<Teacher> toTeacherResource(Teacher teacher) {
        Resource<Teacher> resource = new Resource<Teacher>(teacher);
        resource.add(linkTo(TeacherController.class)
        				.slash(teacher.getTeacherId())
        				.withSelfRel());
        return resource;
    }
	
	public Resource<Subject> toSubjectResource(Subject subject) {
        Resource<Subject> resource = new Resource<Subject>(subject);
        resource.add(linkTo(SubjectController.class)
        				.slash(subject.getSubjectId())
        				.withSelfRel());
        return resource;
    }
	
	public Resources<Resource<Subject>> toSubjectResourceList(List<Subject> subjectList){		
		List<Resource<Subject>> resourceList = new ArrayList<Resource<Subject>>();	
		
		for (Subject subject : subjectList) {			
			resourceList.add(toSubjectResource(subject));
		}		
		Resources<Resource<Subject>> resource = new Resources<Resource<Subject>>(resourceList);
		resource.add(linkTo(SubjectController.class).withSelfRel());
		
		return resource;		
	}
}
