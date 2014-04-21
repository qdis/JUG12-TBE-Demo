package ro.dialogdata.jug.backend.repos;

import org.springframework.data.repository.CrudRepository;

import ro.dialogdata.jug.common.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

	public User findByUsernameIgnoreCase(String username);
}
