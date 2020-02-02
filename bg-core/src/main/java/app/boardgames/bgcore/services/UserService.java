package app.boardgames.bgcore.services;

import app.boardgames.bgcore.dao.CompactUserRepository;
import app.boardgames.bgcore.dao.EventRepository;
import app.boardgames.bgcore.dao.UserRepository;
import app.boardgames.bgcore.domain.*;
import app.boardgames.bgcore.domain.badges.Badge;
import app.boardgames.bgcore.exceptions.EventNotFoundException;
import app.boardgames.bgcore.exceptions.IllegalVotingException;
import app.boardgames.bgcore.exceptions.UserNotFoundException;
import app.boardgames.bgcore.util.BadgeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private CompactUserRepository compactUserRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    public void incrementNumberOfAttendances(User user) {
        user.incrementAttendancesNumber();
        compactUserRepository.save(user);
    }

    public Badge votePlayer(String email, String eventTitle, String voteType, String votedUserEmail) {
        CompactUser voterUser = compactUserRepository.findByEmail(email);
        User votedUser = userRepository.findByEmail(votedUserEmail);
        Event event = eventRepository.findByTitle(eventTitle);
        if (voterUser == null) {
            throw new UserNotFoundException("User " + email + " does not exist!");
        } else if (votedUser == null) {
            throw new UserNotFoundException("User " + votedUserEmail + " does not exist!");
        } else if (event == null) {
            throw new EventNotFoundException("Event " + eventTitle + " does not exist!");
        } else if (voterUser.equals(votedUser)) {
            throw new IllegalVotingException("You can not vote yourself!");
        } else if (event.isEventStillAvailableForRegistration()) {
            throw new IllegalVotingException("You can not vote until the event starts!");
        } else if (!event.getInterestedPlayers().stream().map(InterestedUser::getUser).collect(Collectors.toSet()).contains(voterUser)) {
            throw new IllegalVotingException("You have to participate in an event to be able to vote!");
        } else if (!event.getInterestedPlayers().stream().map(InterestedUser::getUser).collect(Collectors.toSet()).contains(votedUser)) {
            throw new IllegalVotingException("The user you want to vote did not participated in this event!");
        }
        for (InterestedUser interestedUser : event.getInterestedPlayers()) {
            if (interestedUser.getBadges() != null && interestedUser.getBadges().stream().map(BadgeByVoter::getVoter).collect(Collectors.toSet()).contains(voterUser)) {
                throw new IllegalVotingException("You can not vote twice! ");
            }
        }
        BadgeFactory badgeFactory = new BadgeFactory();
        Badge badge = badgeFactory.getBadge(voteType);
        event.pushBadge(votedUser, new BadgeByVoter(badge, voterUser));
        votedUser.pushBadge(badge);
        eventRepository.save(event);
        userRepository.save(votedUser);
        return badge;
    }
}
