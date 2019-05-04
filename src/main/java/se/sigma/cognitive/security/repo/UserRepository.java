package se.sigma.cognitive.security.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.sigma.cognitive.security.entity.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findUserByEmail(String email);
    UserEntity findByUserId(String userId);

}


