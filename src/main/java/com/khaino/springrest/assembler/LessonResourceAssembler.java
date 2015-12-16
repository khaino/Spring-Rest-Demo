package com.khaino.springrest.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import com.khaino.springrest.constant.Constant;
import com.khaino.springrest.controller.HomeController;
import com.khaino.springrest.controller.LessonController;
import com.khaino.springrest.controller.SubjectController;
import com.khaino.springrest.model.Lesson;

@Component
public class LessonResourceAssembler {
	
	public Resource<Lesson> toLessonResource(Lesson lesson) {
        Resource<Lesson> resource = new Resource<Lesson>(lesson);
        
        resource.add(linkTo(LessonController.class, lesson.getSubjectId())
        				.slash(lesson.getLessonId())
        				.withSelfRel());
        
        resource.add(linkTo(SubjectController.class)
        		.slash(lesson.getSubjectId())
        		.withRel(Constant.SUBJECT));
        
        resource.add(linkTo(LessonController.class, lesson.getSubjectId())
        		.slash(lesson.getLessonId())
        		.withRel(Constant.EDIT));
        
        resource.add(linkTo(LessonController.class, lesson.getSubjectId())
        		.slash(lesson.getLessonId())
        		.withRel(Constant.DELETE));
               
        return resource;
    }
	
	public Resources<Resource<Lesson>> toLessonResourceList(List<Lesson> lessonList, int subjectId){		
		List<Resource<Lesson>> resourceList = new ArrayList<Resource<Lesson>>();	
		
		for (Lesson lesson : lessonList) {			
			resourceList.add(toLessonResource(lesson));
		}		
		Resources<Resource<Lesson>> resource = new Resources<Resource<Lesson>>(resourceList);
		resource.add(linkTo(LessonController.class, subjectId).withSelfRel());
		
		resource.add(linkTo(HomeController.class).withRel(Constant.HOME));
		
		return resource;		
	}
}
