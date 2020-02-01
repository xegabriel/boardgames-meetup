package app.boardgames.bgdashboard.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Set;

@Document(collection = "events")
public class Event {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String title;

    private Date startDate;

    private Date endDate;

    private String location;

    private String fullAddress;

    private String description;

    private int maximumPlayers;

    private boolean isEventStillAvailableForRegistration;

    private Set<AvailableGame> availableGames;

    private Set<String> proposedGames;

    private Set<InterestedUser> interestedPlayers;

    public ObjectId getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getLocation() {
        return location;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getDescription() {
        return description;
    }

    public int getMaximumPlayers() {
        return maximumPlayers;
    }

    public boolean isEventStillAvailableForRegistration() {
        return isEventStillAvailableForRegistration;
    }

    public Set<AvailableGame> getAvailableGames() {
        return availableGames;
    }

    public Set<String> getProposedGames() {
        return proposedGames;
    }

    public Set<InterestedUser> getInterestedPlayers() {
        return interestedPlayers;
    }

    public void stopRegistration() {
        isEventStillAvailableForRegistration = false;
    }

    public void startRegistration() {
        isEventStillAvailableForRegistration = true;
    }
}
