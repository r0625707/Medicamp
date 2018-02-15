package medicamp.api;

import java.util.ArrayList;
import java.util.List;

import medicamp.model.Groep;
import medicamp.model.Kind;
import medicamp.model.Voogd;

public class Service {
	// private Database db;
	public List<Kind> getKinderen(String groepID, String takNaam) {
		// ID voor tak/naam van groep miss beter om te zoeken?
		// return db.getKinderenGroepEnTak(groepID,takNaam);
		return new ArrayList<Kind>();
	}

	public void addGroep(Groep groep) {
		// TODO Auto-generated method stub

	}

	public void addKindtoTak(String takID, Kind kind) {
		// TODO Auto-generated method stub

	}

	public void addKind(Kind kind) {
		// TODO Auto-generated method stub

	}

	public void addVoogd(Voogd voogd) {
		// TODO Auto-generated method stub

	}
}
