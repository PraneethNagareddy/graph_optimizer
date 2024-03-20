package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Vehicle {
    private String make;
    private String model;
    private int pricePerKm;
    private int maxCapacity;

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        Vehicle otherVehicle = (Vehicle) other;

        return this.make.equals(otherVehicle.make) && this.model.equals(otherVehicle.model);
    }

    @Override
    public int hashCode() {
        return (make + model).hashCode();
    }
}
