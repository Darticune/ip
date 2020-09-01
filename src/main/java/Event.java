public class Event extends Todo{

    public Event(String details, String duration, String taskType) {
        super(details, duration, taskType);
    }

    public String getTimeline() {
        return (" (at: " + timeline + ")");
    }
}
