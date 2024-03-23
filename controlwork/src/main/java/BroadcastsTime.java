public class BroadcastsTime implements Comparable<BroadcastsTime> {
    private final byte hour;
    private final byte minutes;
    public BroadcastsTime(String time) {
        String[] timeArray = time.strip().split(":");
        hour = Byte.parseByte(timeArray[0]);
        minutes = Byte.parseByte(timeArray[1]);
    }

    public byte hour() {
        return hour;
    }

    public byte minutes() {
        return minutes;
    }

    public boolean after(BroadcastsTime time) {
        if (time.hour() != this.hour()) return this.hour() > time.hour();
        else return this.minutes() > time.minutes();
    }

    public boolean before(BroadcastsTime time) {
        if (time.hour() != this.hour()) return this.hour() < time.hour();
        else return this.minutes() < time.minutes();
    }

    public boolean between(BroadcastsTime firstTime, BroadcastsTime secondTime) {
        return this.before(secondTime) && this.after(firstTime);
    }
    @Override
    public int compareTo(BroadcastsTime o) {
        if (this.after(o)) return 1;
        if (this.before(o)) return -1;
        return 0;
    }

    @Override
    public String toString() {
        String hours = String.valueOf(hour);
        if (hours.length() == 1) hours = "0" + hours;
        String minut = String.valueOf(minutes);
        if (minut.length() == 1) minut = "0" + minut;
        return hours + ":" + minut;
    }
}
