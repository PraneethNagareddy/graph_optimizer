package v2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class Stop {
    private LocalTime arrivalTime;
    private LocalTime departTime;
    private Action action;

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        Stop otherStop = (Stop) other;

        return this.arrivalTime.equals(otherStop.arrivalTime)
                && this.departTime.equals(otherStop.departTime)
                && this.action.equals(otherStop.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrivalTime, departTime, action);
    }
}
