package v2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class DropPoint {
    private String id;
    private String name;
    private Location location;
}
