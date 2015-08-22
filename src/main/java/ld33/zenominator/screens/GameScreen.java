package ld33.zenominator.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import ld33.zenominator.Zenominator;
import ld33.zenominator.screen.ScreenBase;

/**
 *
 */
public class GameScreen extends ScreenBase<Zenominator> {

    private static final int CAMERA_FIELD_OF_VIEW_Y = 67;
    private static final float CAMERA_NEAR_CLIP = 0.1f;
    private static final float CAMERA_FAR_CLIP = 1000f;

    public PerspectiveCamera camera;
    public ModelBatch modelBatch;
    public Environment environment;

    @Override protected void createScreen(Zenominator game, int width, int height) {
        modelBatch = new ModelBatch();

        // Setup camera
        camera = new PerspectiveCamera(CAMERA_FIELD_OF_VIEW_Y, width, height);
        camera.near = CAMERA_NEAR_CLIP;
        camera.far = CAMERA_FAR_CLIP;
        camera.position.set(10f, 10f, 10f);
        camera.lookAt(0, 0, 0);
        camera.update();

        // Lighting etc
        // TODO: Should be provided by entities?
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));
    }

    @Override protected InputProcessor getInputProcessor() {
        return null;
    }

    @Override protected void doRender(Zenominator game, float deltaTime) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // Update world
        game.world.update();

        // Update camera
        camera.update();

        // Render entities
        modelBatch.begin(camera);
        game.renderingProcessor.render(modelBatch, environment);
        modelBatch.end();
    }

    @Override protected void onShow() {
        super.onShow();
    }

    @Override protected void onHide() {
        super.onHide();
    }

    @Override protected void onResize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override public void dispose() {
        if (modelBatch != null) modelBatch.dispose();
    }
}
