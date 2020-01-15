package app.boardgames.bgauthentication.dao;

import app.boardgames.bgauthentication.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long> {
    User findFirstByEmail(String email);
}
