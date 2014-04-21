package ro.dialogdata.jug.backend.repos;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import ro.dialogdata.jug.common.model.Message;

/**
 * Simple message Repository
 *
 */
public interface MessageRepository extends CrudRepository<Message, Long>{

	@Query("select m from Message m order by m.date desc")
	public List<Message> findMostRecent(Pageable page);
	
}
