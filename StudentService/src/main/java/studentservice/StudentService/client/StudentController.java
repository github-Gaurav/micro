package studentservice.StudentService.client;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.netflix.discovery.EurekaClient;


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
	    List<ServiceInstance> list = StudentController.discoveryClient.getInstances("School-Service");
	    ServiceInstance service = list.get(0);
	    String baseUrl = service + "/get";
	    System.out.println("The Base URL is "+ baseUrl);
	    /*System.out.println("The list is "+ list.get(0));
	    if (list != null && list.size() > 0 ) {
	        return list.get(0).getUri().toString();
	    }*/
	    return baseUrl;
	}
	
	private static HttpEntity<?> getHeaders() throws IOException{
		
		HttpHeaders http = new HttpHeaders();
		http.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		
		return new HttpEntity<>(http);
		
	}
	
	
	@RequestMapping("/details/{id}")
	public String getDetails(@PathVariable String id) throws RestClientException, IOException {
		System.out.println("Inside get Details");
		/*String response = rest.exchange("http://localhost:8098/get/{id}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, id).getBody();*/
		
		/*String response = rest.exchange(serviceUrl(),
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, id).getBody();*/
		String response = rest.exchange(serviceUrl(),
                HttpMethod.GET, getHeaders(), String.class).getBody();
		System.out.println("Response is "+ response);
		
		
		return response;
		
	}
	
	
}
