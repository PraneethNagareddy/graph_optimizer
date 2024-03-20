package v2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Setter
@Getter
public class Action {
    private ActionType actionType;
    private List<Candidate> candidateList;
    private Location location;

    public void addCandidate(Candidate candidate) {
        candidateList.add(candidate);
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) return true;
        if(other == null || other.getClass() != this.getClass()) return false;

        Action otherAction = (Action) other;

        return this.actionType.equals(otherAction.actionType)
                && this.candidateList.equals(otherAction.candidateList)
                && this.location.equals(otherAction.location);

    }

    @Override
    public int hashCode() {
        return Objects.hash(actionType, candidateList, location);
    }
}
