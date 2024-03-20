package v2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class PickupPoint {
    private String id;
    private String name;
    private Location location;


    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        PickupPoint otherPickupPoint = (PickupPoint) other;

        return this.id.equals(otherPickupPoint.id)
                && this.name.equals(otherPickupPoint.name)
                && this.location.equals(otherPickupPoint.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, location);
    }

    @Override
    public String toString() {
        return "Pickup Point: " + name + "["+ id +"]. Location:" + location;
    }
}
