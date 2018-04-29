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

@RestController
public class StudentController {

	
	@Autowired
	private static DiscoveryClient discoveryClient;
	
	@Autowired
	RestTemplate rest;

	public static String serviceUrl() {
		 System.out.println("Inside Service URL ");
	    List<ServiceInstance> list = discoveryClient.getInstances("SCHOOL-SERVICE");
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
