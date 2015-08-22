package ld33.zenominator.components;

import com.badlogic.gdx.utils.Array;
import org.entityflow.component.ComponentBase;

import static org.flowutils.Check.notNull;

/**
 * Some space that entities can be in.
 * Used for collisions and portal travel to other spaces.
 */
public final class Place extends ComponentBase {

    private final Array<Position> containedEntityPositions = new Array<>();

    public void addEntityPosition(Position position) {
        notNull(position, "position");
        if (!containedEntityPositions.contains(position, true)) {
            containedEntityPositions.add(position);
            if (position.getPlace() != this) position.setPlace(this);
        }
    }

    public void removeEntityPosition(Position position) {
        notNull(position, "position");
        if (containedEntityPositions.contains(position, true)) {
            containedEntityPositions.removeValue(position, true);
            if (position.getPlace() != null) position.setPlace(null);
        }
    }

    /**
     * @return entity positions that are currently located in this place.
     */
    public Array<Position> getContainedEntityPositions() {
        return containedEntityPositions;
    }
}
