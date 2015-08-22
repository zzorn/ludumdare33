package ld33.zenominator.components;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import org.entityflow.component.ComponentBase;

/**
 * Something with a model instance that can be rendered.
 */
public final class Renderable extends ComponentBase {

   public ModelInstance modelInstance;

    public Renderable() {
        this((ModelInstance) null);
    }

    public Renderable(Model model) {
        this(new ModelInstance(model));
    }

    public Renderable(ModelInstance modelInstance) {
        this.modelInstance = modelInstance;
    }
}
