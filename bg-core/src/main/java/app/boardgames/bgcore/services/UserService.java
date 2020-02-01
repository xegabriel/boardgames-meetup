package app.boardgames.bgcore.services;

import app.boardgames.bgcore.dao.CompactUserRepository;
import app.boardgames.bgcore.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private CompactUserRepository compactUserRepository;

    public void incrementNumberOfAttendances(User user) {
        user.incrementAttendancesNumber();
        compactUserRepository.save(user);
    }
}
