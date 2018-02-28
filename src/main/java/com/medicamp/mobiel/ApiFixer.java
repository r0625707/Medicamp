package com.medicamp.mobiel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiFixer {

	RestTemplate restTemplate=new RestTemplate();
	
	public List<Object> fix(String user) {
		
		CompleteClass c=new CompleteClass();
		CompleteClassGenest cg=new CompleteClassGenest();
		
		
		
		return startAfhaal( user, c,cg);
		
		
		}
	
	public List<Object> startAfhaal(String user,CompleteClass c,CompleteClassGenest cg){
		String mijnUser ="https://medicamp-so.appspot.com/api/user/"+user+"/";	
		
		ResponseEntity<User> userEntity = restTemplate.exchange(mijnUser,HttpMethod.GET,null,User.class);
		
		User gevondenUser = userEntity.getBody();
		c.setUser(gevondenUser);
		cg.setUser(gevondenUser);
		return fixTakken( user, c,cg);
	}
	
	public List<Object> fixTakken(String user,CompleteClass c,CompleteClassGenest cg){
		String takken ="https://medicamp-so.appspot.com/api/user/"+user+"/tak/";	
		
		ResponseEntity<List<Tak>> ee = restTemplate.exchange(takken,HttpMethod.GET,null,new ParameterizedTypeReference<List<Tak>>() {
	    });
		
		List<Tak> takkenjaa = ee.getBody();
		//takkenjaa doorlopen, van elke tak kind fixen
		//1 in lijst kinderen zetten
		//2 kindIDS toevoegen aan lijst in TAK
		//3 in completeClass miss ook een geneste databank maken
		// ==> in TakObject ook een lijst kinderen zetten ipv enkel met ids werken
		
		
		
		
		
		
		//pas hier takken doorlopen en Tak aanpassen want CompleteClass willen we puur houden
		List<Tak> takkenKopie=new ArrayList<Tak>();
		List<Kind> kinderenVulLijst=new ArrayList<Kind>();
		for(Tak t:takkenjaa) {
			takkenKopie.add(fixKinderenPerTak(Integer.toString(t.getIdtak()),kinderenVulLijst, t));
		}
		c.setKinderen(kinderenVulLijst);
		c.setTakken(takkenKopie);
		cg.setTakken(takkenjaa);
		List<Object> eee=new ArrayList<Object>();
		eee.add(c);
		eee.add(cg);
		return eee;
	}
	
	public Tak fixKinderenPerTak(String idtak,List<Kind> kinderenVulLijst,Tak t){
		String kinderen ="https://medicamp-so.appspot.com/api/tak/"+idtak+"/kind";	
		
		ResponseEntity<List<Kind>> ee = restTemplate.exchange(kinderen,HttpMethod.GET,null,new ParameterizedTypeReference<List<Kind>>() {
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
		}
		kinderenVulLijst.addAll(kinderenjaa);
		newTak.setKinderenids(kinderenids);
		return newTak;
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
