package v2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class Location {
    private String id;
    private String name;
    private float longitude;
    private float latitude;

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        Location otherLocation = (Location) other;

        return this.id.equals(otherLocation.id)
                && this.name.equals(otherLocation.name)
                && this.latitude == otherLocation.latitude
                && this.longitude == otherLocation.longitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Location: " + name + "["+ id +"]";
    }
}
