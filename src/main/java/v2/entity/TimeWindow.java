package v2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class TimeWindow {
    private LocalTime earliestTime;
    private LocalTime latestTime;

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        TimeWindow otherTimeWindow = (TimeWindow) other;

        return this.earliestTime.equals(otherTimeWindow.earliestTime)
                && this.latestTime.equals(otherTimeWindow.latestTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(earliestTime, latestTime);
    }

    @Override
    public String toString() {
        return "Time Window: " + earliestTime + "-" + latestTime;
    }
}
