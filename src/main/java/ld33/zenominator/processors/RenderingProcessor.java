package ld33.zenominator.processors;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import ld33.zenominator.components.Position;
import ld33.zenominator.components.Renderable;
import org.entityflow.component.Component;
import org.entityflow.entity.Entity;
import org.entityflow.processors.EntityProcessorBase;
import org.flowutils.time.Time;

/**
 * Renders renderable things.
 */
public final class RenderingProcessor extends EntityProcessorBase {

    public RenderingProcessor() {
        super(Position.class, Renderable.class);
    }

    @Override protected void doProcess(Time systemTime) {
        // Do nothing, we use render method instead.
    }

    @Override protected void processEntity(Time time, Entity entity) {
        // Do nothing, we use render method instead.
    }

    public void render(ModelBatch modelBatch, Environment environment) {
        for (Entity entity : getHandledEntities()) {
            final Renderable renderable = entity.get(Renderable.class);
            if (renderable.modelInstance != null) {
                // Apply position to model instance
                final Position position = entity.get(Position.class);
                final Matrix4 transform = renderable.modelInstance.transform;
                transform.set(position.direction);
                transform.translate(position.position);

                // Render model instance
                modelBatch.render(renderable.modelInstance, environment);
            }
        }
    }
}
