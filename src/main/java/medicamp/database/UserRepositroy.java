package medicamp.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import medicamp.model.Groep;
@Component
public interface UserRepositroy extends CrudRepository<Groep, Long> {

}
