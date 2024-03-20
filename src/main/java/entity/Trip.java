package entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.LinkedList;
import java.util.Objects;

@Getter
@Setter
public class Trip implements Comparable<Trip>{
    private LinkedList<Stop> stops;
    private float earnings;
    private float distanceTravelled;
    private LocalTime startTime;
    private LocalTime endTime;

    public Trip() {
        stops = new LinkedList<>();
        earnings = Float.MIN_VALUE;
        distanceTravelled = Float.MIN_VALUE;
        startTime = LocalTime.MIN;
        endTime = LocalTime.MIN;
    }

    public void addStop(Stop stop) {
        this.stops.add(stop);
    }

    public int timeElapsedInMinutes() {
        final int startTimeInMinutes =  (int) (this.startTime.toSecondOfDay() / 60);
        final int endTimeInMinutes = (int) (this.endTime.toSecondOfDay() / 60);
        return endTimeInMinutes - startTimeInMinutes;
    }

    public Trip clone() {
        Trip clone = new Trip();
        clone.setEarnings(earnings);
        clone.setDistanceTravelled(distanceTravelled);
        clone.setStartTime(startTime);
        clone.setEndTime(endTime);
        for(Stop stop : stops) {
            clone.addStop(stop);
        }
        return clone;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        Trip otherTrip = (Trip) other;

        return this.earnings == otherTrip.earnings
                && this.distanceTravelled == otherTrip.distanceTravelled
                && this.startTime.equals(otherTrip.startTime)
                && this.endTime.equals(otherTrip.endTime)
                && this.stops.equals(otherTrip.stops);

    }

    @Override
    public int hashCode() {
        return Objects.hash(stops, earnings, distanceTravelled);
    }

    @Override
    public int compareTo(Trip o) {
        if (this.earnings > o.earnings) return 1;
        if(this.earnings < o.earnings) return -1;

        if(this.distanceTravelled < o.distanceTravelled) return 1;
        if(this.distanceTravelled > o.distanceTravelled) return -1;
        return 0;
    }
}
