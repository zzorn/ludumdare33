package ld33.zenominator.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.InputProcessor;

/**
 *
 */
public interface Screen<G extends AdvancedGame> {

    /**
     * @param game the game that this screen belongs to.
     *             Also provides various services, and ability to switch screens.
     */
    void setGame(G game);

    /**
     * Called when this screen becomes the current screen for a {@link Game}.
     */
    void show();

    /**
     * Called when the screen should render itself.
     */
    void render(float deltaTime);

    /**
     * @see ApplicationListener#resize(int, int)
     */
    void resize(int width, int height);

    /** @see ApplicationListener#pause() */
    void pause();

    /** @see ApplicationListener#resume() */
    void resume();

    /** Called when this screen is no longer the current screen for a {@link Game}. */
    void hide();

    /** Called when this screen should release all resources. */
    void dispose();

}
