package schoolservice.SchoolService.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import schoolservice.SchoolService.pojo.Student;

@RestController
public class SchoolController {
	
	private static Map<String, List<Student>> map = new HashMap<>();
	
	static {
		
		List<Student> lst = new ArrayList<>();
		Student stu = new Student("gaurav", "10");
		lst.add(stu);
		stu = new Student("Ankush", "5");
		lst.add(stu);
		
		map.put("l1", lst);
		
		 lst = new ArrayList<>();
		 stu = new Student("amit", "10");
		lst.add(stu);
		stu = new Student("john", "5");
		lst.add(stu);
		
		map.put("l2", lst);
	}
	
	@RequestMapping("/get/{schoolName}")
	public List<Student> getStudent(@PathVariable String schoolName){
		System.out.println("inside get student");
		List<Student> ls = map.get(schoolName);
		
		return ls;
		
		
	}
	
	

}
