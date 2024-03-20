package entity;

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

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        PickupPoint otherPickupPoint = (PickupPoint) other;

        return this.id.equals(otherPickupPoint.id)
                && this.name.equals(otherPickupPoint.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
