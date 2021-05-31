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
		String url = "http://localhost/Itop/web//webservices/rest.php?version=1.3&json_data={ \"operation\": "
				+ "\"core/get\", \"class\": \"Person\", \"key\": \"SELECT `Person` FROM Person AS `Person` JOIN Organization AS "
				+ "`Organization` ON `Person`.org_id = `Organization`.id JOIN Organization AS `Organization1` ON `Organization`"
				+ ".parent_id BELOW `Organization1`.id WHERE (`Organization1`.`id` = '5')\", \"output_fields\": \"friendlyname, email, "
				+ "phone\" }";
				
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin", "admin");
		HttpEntity<String> entidad = new HttpEntity<String>(httpHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entidad, String.class);
		
		String datosITop = response.getBody();
				
		List<ITopUser> listaDatos = new ArrayList<>();
		
		String informacion[] = datosITop.replace("{\"objects\":{", "").split("},");

		Integer idPersona = 42;
		for (int i = 0; i < informacion.length-1; i++) {
			informacion[i]= informacion[i]
					.replace("\"Person::" + idPersona + "\":{\"code\":0,\"message\":\"\",\"class\":\"Person\",\"key\":\"" + idPersona + "\",\"fields\":", "")
					.replace("{", "").replaceAll("}", "").replaceAll("\"", "").replace("friendlyname:", "").replace("email:", "").replace("phone:", "");
			
			String personInfo[] = informacion[i].split(",");
			listaDatos.add(new ITopUser(personInfo[0], personInfo[1], personInfo[2]));
			
			idPersona++;
		}
		
		model.put("listaDatos", listaDatos);
		return "API/API_ITop";
	}

}
