package ld33.zenominator.components;

import com.badlogic.gdx.math.Vector3;
import org.entityflow.component.ComponentBase;

/**
 * For verlet physics.
 */
public final class Physical extends ComponentBase {

    public double mass;
    public double radius;
    public final Vector3 previousPosition = new Vector3();
    public boolean previousPositionInitialized = false;

}
