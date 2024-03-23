public class Program {
    String channel;
    String title;
    BroadcastsTime time;

    public Program(String channel, String title, BroadcastsTime time) {
        this.channel = channel;
        this.title = title;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Программа: '" + title + "' на канале: " +
                channel + " в " + time;
    }
}
