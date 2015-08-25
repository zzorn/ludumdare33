package ld33.zenominator.processors;

import ld33.zenominator.components.Physical;
import ld33.zenominator.components.Position;
import org.entityflow.entity.Entity;
import org.entityflow.processors.EntityProcessorBase;
import org.flowutils.time.Time;

/**
 *
 */
public final class PhysicsProcessor extends EntityProcessorBase {

    private static final double TIME_STEP_SECONDS = 0.01;
    private static final int COLLISION_CONSTRAINT_ITERATIONS = 5;

    public PhysicsProcessor() {
        super(TIME_STEP_SECONDS, Physical.class, Position.class);

        // Physics is best run in fixed timesteps
        setFixedTimeStep(true);
    }

    @Override protected void processEntity(Time time, Entity entity) {
        final Physical physical = entity.get(Physical.class);
        final Position position = entity.get(Position.class);

        // Apply gravitation
        // TODO

        // Apply other accumulated force impulses
        // TODO

        // Apply velocity
        // TODO

        // Iterate constraints a few times:
        for (int i = 0; i < COLLISION_CONSTRAINT_ITERATIONS; i++) {
            // Collide against other particles in the same room
            // TODO

            // Collide against the walls of this place and any nearby place if the entity is crossing a portal
            // TODO
        }
    }
}
