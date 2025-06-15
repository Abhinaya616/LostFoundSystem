import java.sql.Date;

public class FoundItem {
    private int id;
    private String finderName;
    private String itemName;
    private String description;
    private String locationFound;
    private Date dateFound;
    private String status;
    private Integer matchedToLostItemId;  // Nullable if unmatched

    // Full constructor
    public FoundItem(int id, String finderName, String itemName, String description, String locationFound, Date dateFound, String status, Integer matchedToLostItemId) {
        this.id = id;
        this.finderName = finderName;
        this.itemName = itemName;
        this.description = description;
        this.locationFound = locationFound;
        this.dateFound = dateFound;
        this.status = status;
        this.matchedToLostItemId = matchedToLostItemId;
    }

    // Constructor without id and matchedToLostItemId (for inserting new item)
    public FoundItem(String finderName, String itemName, String description, String locationFound, Date dateFound, String status) {
        this.finderName = finderName;
        this.itemName = itemName;
        this.description = description;
        this.locationFound = locationFound;
        this.dateFound = dateFound;
        this.status = status;
        this.matchedToLostItemId = null;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getFinderName() {
        return finderName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getLocationFound() {
        return locationFound;
    }

    public Date getDateFound() {
        return dateFound;
    }

    public String getStatus() {
        return status;
    }

    public Integer getMatchedToLostItemId() {
        return matchedToLostItemId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMatchedToLostItemId(Integer matchedToLostItemId) {
        this.matchedToLostItemId = matchedToLostItemId;
    }

    // toString method for readable print output
    @Override
    public String toString() {
        return "FoundItem {" +
               "Finder Name='" + finderName + '\'' +
               ", Item Name='" + itemName + '\'' +
               ", Description='" + description + '\'' +
               ", Location Found='" + locationFound + '\'' +
               ", Date Found=" + dateFound +
               ", Status='" + status + '\'' +
               ", Matched Lost Item ID=" + matchedToLostItemId +
               '}';
    }
}
