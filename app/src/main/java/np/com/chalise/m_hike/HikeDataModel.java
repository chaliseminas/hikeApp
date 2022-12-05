package np.com.chalise.m_hike;

public class HikeDataModel {

    private String id;
    private String name;
    private String location;
    private String date;
    private String available;
    private String length;
    private String difficulty;
    private String accommodation;
    private String limitation;
    private String description;

    public HikeDataModel() {}

    public HikeDataModel(String id, String name, String location, String date, String available, String length, String difficulty, String accommodation, String limitation, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.date = date;
        this.available = available;
        this.length = length;
        this.difficulty = difficulty;
        this.accommodation = accommodation;
        this.limitation = limitation;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }

    public String getLimitation() {
        return limitation;
    }

    public void setLimitation(String limitation) {
        this.limitation = limitation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
