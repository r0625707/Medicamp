package com.medicamp.mobiel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;

@Component
public class ApiFixer {

	RestTemplate restTemplate=new RestTemplate();
	
	public List<Object> fix(String user,String auth) {
		
		CompleteClass c=new CompleteClass();
		CompleteClassGenest cg=new CompleteClassGenest();
		

		  
		  


		
		
	//	User gevondenUser = userEntity.getBody();
		
		return startAfhaal( user, c,cg,auth);
		
		
		}
	
	public List<Object> startAfhaal(String user,CompleteClass c,CompleteClassGenest cg,String auth){
		String mijnUser ="https://medicamp-so.appspot.com/api/user/"+user+"/";	
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", auth);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<User> userEntity = restTemplate.exchange(mijnUser,HttpMethod.GET,entity,User.class);
		
		User gevondenUser = userEntity.getBody();
		
		
		
		
		c.setUser(gevondenUser);
		cg.setUser(gevondenUser);
		return fixTakken( user, c,cg,entity);
	}
	
	public List<Object> fixTakken(String user,CompleteClass c,CompleteClassGenest cg,HttpEntity entity){
		String takken ="https://medicamp-so.appspot.com/api/user/"+user+"/tak/";	
		
		ResponseEntity<List<Tak>> ee = restTemplate.exchange(takken,HttpMethod.GET,entity,new ParameterizedTypeReference<List<Tak>>() {
	    });
		
		List<Tak> takkenjaa = ee.getBody();
		//takkenjaa doorlopen, van elke tak kind fixen
		//1 in lijst kinderen zetten
		//2 kindIDS toevoegen aan lijst in TAK
		//3 in completeClass miss ook een geneste databank maken
		// ==> in TakObject ook een lijst kinderen zetten ipv enkel met ids werken
		
		
		
		
		
		//cg.setTakken(takkenjaa);
		//pas hier takken doorlopen en Tak aanpassen want CompleteClass willen we puur houden
		List<Tak> takkenKopie=new ArrayList<Tak>();
		List<Kind> kinderenVulLijst=new ArrayList<Kind>();
		List<Tak> takkenGenest=new ArrayList<Tak>();
		for(Tak t:takkenjaa) {
			fixActiviteitenPerTak( t, entity);
			takkenKopie.add(fixKinderenPerTak(Integer.toString(t.getIdtak()),kinderenVulLijst, t,entity));
			
			takkenGenest.add(t);
			
		}
		cg.setTakken(takkenGenest);////////////
		c.setKinderen(kinderenVulLijst);
		c.setTakken(takkenKopie);
		
		List<Object> eee=new ArrayList<Object>();
		eee.add(c);
		eee.add(cg);
		return eee;
	}
	
	public Tak fixKinderenPerTak(String idtak,List<Kind> kinderenVulLijst,Tak t,HttpEntity entity){
		String kinderen ="https://medicamp-so.appspot.com/api/tak/"+idtak+"/kind";	
		
		ResponseEntity<List<Kind>> ee = restTemplate.exchange(kinderen,HttpMethod.GET,entity,new ParameterizedTypeReference<List<Kind>>() {
	    });
		
		List<Kind> kinderenjaa = ee.getBody();
		//aan het loopen over takken, dus als laatste tak geen kinderen heeft iss kinderen in C leeg (altijd overschreven)
		//c.setKinderen(kinderenjaa);
		Tak newTak=new Tak();
		newTak.setIdtak(t.getIdtak());
		newTak.setNaam(t.getNaam());
		newTak.setOmschrijving(t.getOmschrijving());
		List<String>kinderenids=new ArrayList<String>();
		for(Kind k : kinderenjaa) {
			kinderenids.add(Integer.toString(k.getIdkind()));
			k.addTakId(Integer.toString(t.getIdtak()));
			 fixVoogdenPerKind( k,entity);
			 fixZiektesPerKind( k,entity);
			 fixMedicatiesPerKind( k,entity);
			 fixDieetenPerKind( k,entity);
		}
		t.setKinderen(kinderenjaa);/////////
		kinderenVulLijst.addAll(kinderenjaa);
		newTak.setKinderenids(kinderenids);
		return newTak;
	}
	
	public void fixVoogdenPerKind(Kind k,HttpEntity entity){
		String voogden ="https://medicamp-so.appspot.com/api/kind/"+k.getIdkind()+"/voogd";	
		
		ResponseEntity<List<Voogd>> ee = restTemplate.exchange(voogden,HttpMethod.GET,entity,new ParameterizedTypeReference<List<Voogd>>() {
	    });
		
		List<Voogd> voogdenjaa = ee.getBody();
		k.setVoogden(voogdenjaa);
	}
	public void fixZiektesPerKind(Kind k,HttpEntity entity){
		String ziektes ="https://medicamp-so.appspot.com/api/kind/"+k.getIdkind()+"/ziekte";	
		
		ResponseEntity<List<Ziekte>> ee = restTemplate.exchange(ziektes,HttpMethod.GET,entity,new ParameterizedTypeReference<List<Ziekte>>() {
	    });
		
		List<Ziekte> ziektesjaa = ee.getBody();
		k.setZiektes(ziektesjaa);
	}
	public void fixMedicatiesPerKind(Kind k,HttpEntity entity){
		String medicaties ="https://medicamp-so.appspot.com/api/kind/"+k.getIdkind()+"/medicatie";	
		
		ResponseEntity<List<Medicatie>> ee = restTemplate.exchange(medicaties,HttpMethod.GET,entity,new ParameterizedTypeReference<List<Medicatie>>() {
	    });
		
		List<Medicatie> medicatiesjaa = ee.getBody();
		k.setMedicaties(medicatiesjaa);
	}
	public void fixDieetenPerKind(Kind k,HttpEntity entity){
		String dieeten ="https://medicamp-so.appspot.com/api/kind/"+k.getIdkind()+"/dieet";	
		
		ResponseEntity<List<Dieet>> ee = restTemplate.exchange(dieeten,HttpMethod.GET,entity,new ParameterizedTypeReference<List<Dieet>>() {
	    });
		
		List<Dieet> dieetenjaa = ee.getBody();
		k.setDieeten(dieetenjaa);
	}
	
	public void fixActiviteitenPerTak(Tak t,HttpEntity entity){
		String activiteiten ="https://medicamp-so.appspot.com/api/tak/"+t.getIdtak()+"/activiteit";	
		
		ResponseEntity<List<Activiteit>> ee = restTemplate.exchange(activiteiten,HttpMethod.GET,entity,new ParameterizedTypeReference<List<Activiteit>>() {
	    });
		
		List<Activiteit> takkenjaa = ee.getBody();
		t.setActiviteiten(takkenjaa);
	}
	
	
	
	
	
	
	
	
	
	
	
	public void fixGroepen(String user,CompleteClass c){
		String takken ="https://medicamp-so.appspot.com/api/user/"+user+"/tak/";	
		
		ResponseEntity<List<Tak>> ee = restTemplate.exchange(takken,HttpMethod.GET,null,new ParameterizedTypeReference<List<Tak>>() {
	    });
		
		List<Tak> takkenjaa = ee.getBody();
		c.setTakken(takkenjaa);
	}
	public void fixVoogden(String user,CompleteClass c){
		String takken ="https://medicamp-so.appspot.com/api/user/"+user+"/tak/";	
		
		ResponseEntity<List<Tak>> ee = restTemplate.exchange(takken,HttpMethod.GET,null,new ParameterizedTypeReference<List<Tak>>() {
	    });
		
		List<Tak> takkenjaa = ee.getBody();
		c.setTakken(takkenjaa);
	}
	
	
	
	
	
	
}
