/**
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package medicamp.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import medicamp.database.UserRepositroy;
import medicamp.model.Groep;
import medicamp.model.Kind;
import medicamp.model.Voogd;

@RestController
public class HelloworldController {
	@Autowired
	private Service service;
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Voogd get(@PathVariable String id) {
		Long l = Long.valueOf(id);
	return service.getVoogd(l);
	}
	// of groepsnaam
	@RequestMapping("/{groepID}/{takNaam}")
	public List<Kind> getKinderen(@PathVariable String groepID, String takNaam) {
		//return service.getKinderen(groepID, takNaam);
		return null;
	}

	@RequestMapping(value = "/groep", method = RequestMethod.POST)
	public void addGroep(@RequestBody Groep groep) {
		//userRepository.save(groep);
	//	service.addGroep(groep);
	}

	@RequestMapping(value = "/voogd", method = RequestMethod.POST)
	public void addVoogd(@RequestBody Voogd voogd) {
		//service.addVoogd(voogd);
	}

	@RequestMapping(value = "/kind", method = RequestMethod.POST)
	public void addKind(@RequestBody Kind kind) {

		// in service kind.getVoogdID
		//service.addKind(kind);
	}

	// DEES IS OM RELATIE TE LEGGEN TUSSEN MANYTOMANY, MOETK NOG TEGOEI BEZIEN
	@RequestMapping(value = "/tak/{takID}/kind", method = RequestMethod.PUT)
	public void setKindtoTak(@RequestBody Kind kind, @PathVariable String takID) {
		// Variabele groepID checken of dat zo kan

	//	service.addKindtoTak(takID, kind);
	}
	
	@RequestMapping(value = "/mobile/{login}", method = RequestMethod.GET)
	public String getMobileDb(@PathVariable String login) {
		return "connectie werkt";
	}

	@GetMapping("/")
	public String hello() {
		return "Welcome to Medicamp API!";
	}
}
