package np.com.chalise.m_hike;

public class Observations {

    private String id;
    private String hikeId;
    private String observation;
    private String timeOfObservation;
    private String additionalComment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHikeId() {
        return hikeId;
    }

    public void setHikeId(String hikeId) {
        this.hikeId = hikeId;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getTimeOfObservation() {
        return timeOfObservation;
    }

    public void setTimeOfObservation(String timeOfObservation) {
        this.timeOfObservation = timeOfObservation;
    }

    public String getAdditionalComment() {
        return additionalComment;
    }

    public void setAdditionalComment(String additionalComment) {
        this.additionalComment = additionalComment;
    }

    public Observations() {}

    public Observations(String id, String hikeId, String observation, String timeOfObservation, String additionalComment) {
        this.id = id;
        this.hikeId = hikeId;
        this.observation = observation;
        this.timeOfObservation = timeOfObservation;
        this.additionalComment = additionalComment;
    }

}
