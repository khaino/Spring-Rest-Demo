package com.khaino.springrest.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import com.khaino.springrest.constant.Constant;
import com.khaino.springrest.controller.HomeController;
import com.khaino.springrest.controller.SubjectController;
import com.khaino.springrest.controller.TeacherController;
import com.khaino.springrest.model.Subject;

@Component
public class SubjectResourceAssembler {

	public Resource<Subject> toSubjectResource(Subject subject) {

		Resource<Subject> resource = new Resource<Subject>(subject);
		resource.add(linkTo(SubjectController.class)
				.slash(subject.getSubjectId()).withSelfRel());

		if (subject.getTeacherId() != null) {
			resource.add(linkTo(TeacherController.class)
					.slash(subject.getTeacherId()).withRel(Constant.TEACHER));
		} else {		

			Link link = linkTo(methodOn(SubjectController.class)
        			.assignTeacher(subject.getSubjectId(), 0))
        			.withRel(Constant.SUBJECTS_ASSIGNED);
			resource.add(link);
		}

		resource.add(linkTo(SubjectController.class)
				.slash(subject.getSubjectId()).withRel(Constant.EDIT));
		
		resource.add(linkTo(SubjectController.class)
				.slash(subject.getSubjectId()).withRel(Constant.DELETE));

		return resource;
	}

	public Resources<Resource<Subject>> toSubjectResourceList(List<Subject> subjectList) {

		List<Resource<Subject>> resourceList = new ArrayList<Resource<Subject>>();

		for (Subject subject : subjectList) {
			resourceList.add(toSubjectResource(subject));
		}

		Resources<Resource<Subject>> resource = new Resources<Resource<Subject>>(resourceList);
		resource.add(linkTo(SubjectController.class).withSelfRel());

		resource.add(linkTo(HomeController.class).withRel(Constant.HOME));
		
		return resource;
	}
}
