package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class DropPoint {
    private String id;
    private String name;

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        DropPoint otherDropPoint = (DropPoint) other;

        return this.id.equals(otherDropPoint.id)
                && this.name.equals(otherDropPoint.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
