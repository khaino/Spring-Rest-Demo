<h1>Spring-REST-Demo</h1>
<p>This is to demonstrate Spring Restful Demo.</p>
<p>The tables needed for this demo is give below </p>

<pre>
	CREATE TABLE LESSON
	(
		LESSON_ID serial NOT NULL,
		LESSON_NAME varchar(100),
		SUBJECT_ID int NOT NULL,
		PRIMARY KEY (LESSON_ID)
	) WITHOUT OIDS;
		
	CREATE TABLE SUBJECT
	(
		SUBJECT_ID serial NOT NULL,
		SUBJECT_NAME varchar(100),
		TEACHER_ID int,
		PRIMARY KEY (SUBJECT_ID)
	) WITHOUT OIDS;
		
	CREATE TABLE TEACHER
	(
		TEACHER_ID serial NOT NULL,
		TEACHER_NAME varchar(50),
		PRIMARY KEY (TEACHER_ID)
	) WITHOUT OIDS;
			
	/* Create Foreign Keys */
	
	ALTER TABLE LESSON
		ADD FOREIGN KEY (SUBJECT_ID)
		REFERENCES SUBJECT (SUBJECT_ID)
		ON UPDATE RESTRICT
		ON DELETE RESTRICT;
	
	
	ALTER TABLE SUBJECT
		ADD FOREIGN KEY (TEACHER_ID)
		REFERENCES TEACHER (TEACHER_ID)
		ON UPDATE RESTRICT
		ON DELETE RESTRICT;
</pre>

<p>Though the demo has not been completed, you can start with Get request at this link, http://localhost:8080/springexample/home, and explore more. Some of the examples of requests and results are :-</P>
<pre>
	{
	  "message": "This is a simple demo to demonstrate Spring Restful, please follow the links to explore more",
	  "by": "Pau Sian Khai",
	  "links": [
	    {
	      "rel": "teacher",
	      "href": "http://localhost:8080/springexample/teachers"
	    },
	    {
	      "rel": "subject",
	      "href": "http://localhost:8080/springexample/subjects"
	    }
	  ]
	}
</pre>

<p>http://localhost:8080/springexample/teachers</P>
<pre>
	{
	  "links": [
	    {
	      "rel": "self",
	      "href": "http://localhost:8080/springexample/teachers"
	    },
	    {
	      "rel": "home",
	      "href": "http://localhost:8080/springexample/home"
	    }
	  ],
	  "content": [
	    {
	      "teacherId": 1,
	      "teacherName": "ABCD",
	      "links": [
	        {
	          "rel": "self",
	          "href": "http://localhost:8080/springexample/teachers/1"
	        },
	        {
	          "rel": "subjects_assigned",
	          "href": "http://localhost:8080/springexample/teachers/1/subjects"
	        },
	        {
	          "rel": "edit",
	          "href": "http://localhost:8080/springexample/teachers/1"
	        },
	        {
	          "rel": "delete",
	          "href": "http://localhost:8080/springexample/teachers/1"
	        }
	      ]
	    },
	    {
	      "teacherId": 2,
	      "teacherName": "BCDE",
	      "links": [
	        {
	          "rel": "self",
	          "href": "http://localhost:8080/springexample/teachers/2"
	        },
	        {
	          "rel": "subjects_assigned",
	          "href": "http://localhost:8080/springexample/teachers/2/subjects"
	        },
	        {
	          "rel": "edit",
	          "href": "http://localhost:8080/springexample/teachers/2"
	        },
	        {
	          "rel": "delete",
	          "href": "http://localhost:8080/springexample/teachers/2"
	        }
	      ]
	    },
	    
		......
	  ]
	}
</pre>

<p>http://localhost:8080/springexample/teachers/1</P>
<pre>
	{
	  "teacherId": 1,
	  "teacherName": "ABCD",
	  "links": [
	    {
	      "rel": "self",
	      "href": "http://localhost:8080/springexample/teachers/1"
	    },
	    {
	      "rel": "subjects_assigned",
	      "href": "http://localhost:8080/springexample/teachers/1/subjects"
	    },
	    {
	      "rel": "edit",
	      "href": "http://localhost:8080/springexample/teachers/1"
	    },
	    {
	      "rel": "delete",
	      "href": "http://localhost:8080/springexample/teachers/1"
	    }
	  ]
	}
</pre>

<p>http://localhost:8080/springexample/subjects</p>
<pre>
	{
	  "links": [
	    {
	      "rel": "self",
	      "href": "http://localhost:8080/springexample/subjects"
	    },
	    {
	      "rel": "home",
	      "href": "http://localhost:8080/springexample/home"
	    }
	  ],
	  "content": [
	    {
	      "subjectId": 2,
	      "subjectName": "BBBBBBB",
	      "teacherId": 17,
	      "links": [
	        {
	          "rel": "self",
	          "href": "http://localhost:8080/springexample/subjects/2"
	        },
	        {
	          "rel": "teacher",
	          "href": "http://localhost:8080/springexample/teachers/17"
	        },
	        {
	          "rel": "edit",
	          "href": "http://localhost:8080/springexample/subjects/2"
	        },
	        {
	          "rel": "delete",
	          "href": "http://localhost:8080/springexample/subjects/2"
	        }
	      ]
	    },
		....
	    
	  ]
	}
</pre>

<p>http://localhost:8080/springexample/subjects/1/lessons</p>
<pre>
	{
	  "links": [
	    {
	      "rel": "self",
	      "href": "http://localhost:8080/springexample/subjects/1/lessons"
	    },
	    {
	      "rel": "home",
	      "href": "http://localhost:8080/springexample/home"
	    }
	  ],
	  "content": [
	    {
	      "lessonId": 5,
	      "lessonName": "Lesson Two",
	      "subjectId": 1,
	      "links": [
	        {
	          "rel": "self",
	          "href": "http://localhost:8080/springexample/subjects/1/lessons/5"
	        },
	        {
	          "rel": "subject",
	          "href": "http://localhost:8080/springexample/subjects/1"
	        },
	        {
	          "rel": "edit",
	          "href": "http://localhost:8080/springexample/subjects/1/lessons/5"
	        },
	        {
	          "rel": "delete",
	          "href": "http://localhost:8080/springexample/subjects/1/lessons/5"
	        }
	      ]
	    }
	  ]
	}
</pre>