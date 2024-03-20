package optimizer.entity;

import entity.DropPoint;
import entity.PickupPoint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class Node {
    private PickupPoint pickupPoint;
    private DropPoint dropPoint;

    public boolean isPickupPoint() {
        return pickupPoint != null && dropPoint == null;
    }

    public boolean isDropPoint() {
        return dropPoint != null && pickupPoint == null;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        Node otherNode = (Node) other;

        if(pickupPoint == null && otherNode.pickupPoint != null) return false;
        if(dropPoint == null && otherNode.dropPoint != null) return false;

        if(pickupPoint != null && otherNode.pickupPoint == null) return false;
        if(dropPoint != null && otherNode.dropPoint == null) return false;

        if(pickupPoint != null && otherNode.pickupPoint != null && !pickupPoint.equals(otherNode.pickupPoint)) return false;
        if(dropPoint != null && otherNode.dropPoint != null && !dropPoint.equals(otherNode.dropPoint)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pickupPoint, dropPoint);
    }
}
