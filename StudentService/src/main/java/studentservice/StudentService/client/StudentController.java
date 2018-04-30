package studentservice.StudentService.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.LookupService;
import studentservice.StudentService.StudentServiceApplication;

@RestController
public class StudentController {

	
	@Autowired
	private static DiscoveryClient discoveryClient;
	
	@Autowired
	EurekaClient eureka;
	
	@Autowired
	RestTemplate rest;

	public  String serviceUrl() {
		 System.out.println("Inside Service URL ");
		/*List<LookupService<StudentServiceApplication>> str =  eureka.getInstancesById("SCHOOL-SERVICE");
		str.stream().forEach(u->System.out.println(u.getApplication(arg0)));*/
	    List<ServiceInstance> list = this.discoveryClient.getInstances("SCHOOL-SERVICE");
	    System.out.println("The list is "+ list.get(0));
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getUri().toString();
	    }
	    return null;
	}
	
	
	@RequestMapping("/details/{id}")
	public String getDetails(@PathVariable String id) {
		System.out.println("Inside get Details");
		/*String response = rest.exchange("http://localhost:8098/get/{id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, id).getBody();*/
		
		String response = rest.exchange(serviceUrl(),
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, id).getBody();
		System.out.println("Response is "+ response);
		
		
		return response;
		
	}
	
	
}
