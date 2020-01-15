package app.boardgames.bgcore.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "events")
public class Event {
    @Id
    private ObjectId id;

    private String title;

    private Date startDate;

    private Date endDate;

    private String location;

    private String fullAddress;

    private String description;

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
}
