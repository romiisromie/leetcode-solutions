import java.util.ArrayList;
import java.util.List;

public class MyCalendarTwo {
    private final List<int[]> bookings;
    private final List<int[]> overlaps;

    public MyCalendarTwo() {
        this.bookings = new ArrayList<>();
        this.overlaps = new ArrayList<>();
    }

    public boolean book(int startTime, int endTime) {
        for (int[] overlap : overlaps) {
            if (Math.max(startTime, overlap[0]) < Math.min(endTime, overlap[1])) {
                return false;
            }
        }

        for (int[] booking : bookings) {
            int subStart = Math.max(startTime, booking[0]);
            int subEnd = Math.min(endTime, booking[1]);
            if (subStart < subEnd) {
                overlaps.add(new int[]{subStart, subEnd});
            }
        }

        bookings.add(new int[]{startTime, endTime});
        return true;
    }
}