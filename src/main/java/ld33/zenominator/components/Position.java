package ld33.zenominator.components;

import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import org.entityflow.component.ComponentBase;

/**
 *
 */
public final class Position extends ComponentBase {

    public final Vector3 position = new Vector3();
    public final Quaternion direction = new Quaternion();

    private Place place;


    public Position() {
        this(Vector3.Zero);
    }

    public Position(Vector3 pos) {
        this(pos, null);
    }

    public Position(Vector3 pos, Quaternion dir) {
        position.set(pos);
        if (dir != null) direction.set(dir);
    }

    /**
     * Place that this entity is located in, or null for the void.  Provides collision boundaries.
     */
    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        final Place oldPlace = this.place;
        this.place = place;

        if (oldPlace != null) this.place.removeEntityPosition(this);
        if (this.place != null) this.place.addEntityPosition(this);
    }
}
