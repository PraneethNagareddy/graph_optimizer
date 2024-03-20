package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class Candidate {
    private String id;
    private String name;
    private LocalTime earliestPickupTime;
    private LocalTime earliestDropTime;
    private LocalTime latestDropTime;
    private LocalTime latestPickupTime;
    private SubscriptionTier subscriptionTier;

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        Candidate otherCandidate = (Candidate) other;

        return this.id.equals(otherCandidate.id)
                && this.name.equals(otherCandidate.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return name + "["+ id +"]" + " Pickup Window:" + earliestPickupTime + " - " + latestPickupTime + ". DropWindow:" + earliestDropTime + " - " + latestDropTime;
    }
}
