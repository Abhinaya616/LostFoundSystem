import java.sql.Date;

public class LostItem {
    private int id;  // optional if you track IDs from DB
    private String personName;
    private String itemName;
    private String description;
    private String locationLost;
    private Date dateLost;
    private String status;

    // Full constructor
    public LostItem(int id, String personName, String itemName, String description, String locationLost, Date dateLost, String status) {
        this.id = id;
        this.personName = personName;
        this.itemName = itemName;
        this.description = description;
        this.locationLost = locationLost;
        this.dateLost = dateLost;
        this.status = status;
    }

    // Constructor without id (for inserting new item where id is auto-generated)
    public LostItem(String personName, String itemName, String description, String locationLost, Date dateLost, String status) {
        this.personName = personName;
        this.itemName = itemName;
        this.description = description;
        this.locationLost = locationLost;
        this.dateLost = dateLost;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getPersonName() {
        return personName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getLocationLost() {
        return locationLost;
    }

    public Date getDateLost() {
        return dateLost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // toString method for readable print output
    @Override
    public String toString() {
        return "LostItem {" +
               "Person Name='" + personName + '\'' +
               ", Item Name='" + itemName + '\'' +
               ", Description='" + description + '\'' +
               ", Location Lost='" + locationLost + '\'' +
               ", Date Lost=" + dateLost +
               ", Status='" + status + '\'' +
               '}';
    }
}
