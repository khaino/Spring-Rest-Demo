package com.khaino.springrest.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import com.khaino.springrest.controller.LessonController;
import com.khaino.springrest.controller.SubjectController;
import com.khaino.springrest.model.Lesson;
import com.khaino.springrest.model.Subject;

@Component
public class LessonResourceAssembler {
	
	public Resource<Subject> toSubjectResource(Subject subject) {
        Resource<Subject> resource = new Resource<Subject>(subject);
        resource.add(linkTo(SubjectController.class)
        				.slash(subject.getSubjectId())
        				.withSelfRel());
        return resource;
    }
	
	public Resource<Lesson> toLessonResource(Lesson lesson) {
        Resource<Lesson> resource = new Resource<Lesson>(lesson);
        resource.add(linkTo(LessonController.class)
        				.slash(lesson.getLessonId())
        				.withSelfRel());
        return resource;
    }
	
	public Resources<Resource<Lesson>> toLessonResourceList(List<Lesson> lessonList){		
		List<Resource<Lesson>> resourceList = new ArrayList<Resource<Lesson>>();	
		
		for (Lesson lesson : lessonList) {			
			resourceList.add(toLessonResource(lesson));
		}		
		Resources<Resource<Lesson>> resource = new Resources<Resource<Lesson>>(resourceList);
		resource.add(linkTo(LessonController.class).withSelfRel());
		
		return resource;		
	}
}
