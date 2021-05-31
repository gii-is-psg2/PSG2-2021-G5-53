package org.springframework.samples.petclinic.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.model.ITopUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class APIController {
	
	@Autowired
	public APIController() {
	}
	
	@GetMapping(value = "/supportPage")
	public String getiTopInfo(ModelMap model) {
//		String url = "http://localhost/iTop";
//				
//		HttpHeaders httpHeaders = new HttpHeaders();
//		httpHeaders.setBasicAuth("admin", "admin");
//		HttpEntity<String> entity = new HttpEntity<String>(httpHeaders);
//		
//		RestTemplate restTemplate = new RestTemplate();
//		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//		
//		String developersInfo = response.getBody();
//				
//		List<ITopUser> developersInfoList = new ArrayList<>();
//		
//		String infoSplitted[] = developersInfo.replace("{\"objects\":{", "").split("},");
//
//		Integer numKeyPersona = 2;
//		for (int i = 0; i < infoSplitted.length-1; i++) {
//			infoSplitted[i]= infoSplitted[i]
//					.replace("\"Person::" + numKeyPersona + "\":{\"code\":0,\"message\":\"\",\"class\":\"Person\",\"key\":\"" + numKeyPersona + "\",\"fields\":", "")
//					.replace("{", "").replaceAll("}", "").replaceAll("\"", "").replace("friendlyname:", "").replace("email:", "").replace("phone:", "");
//			
//			String personInfo[] = infoSplitted[i].split(",");
//			developersInfoList.add(new ITopUser(personInfo[0], personInfo[1], personInfo[2]));
//			
//			numKeyPersona++;
//		}
//		
//		model.put("developersInfoList", developersInfoList);
		
		ITopUser developersInfoList = (new ITopUser("admin", "admin@gmail.com", "657555876"));
		model.put("developersInfoList", developersInfoList);
		return "API/API_ITop";
	}

}
