package ld33.zenominator.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

import static org.flowutils.Check.notNull;

/**
 *
 */
public abstract class ScreenBase<G extends AdvancedGame> implements Screen<G> {

    private G game;
    private int width;
    private int height;
    private boolean initialized = false;

    @Override public final void setGame(G game) {
        notNull(game, "game");
        this.game = game;
    }

    public final G getGame() {
        return game;
    }

    /**
     * @return current width, or zero if not yet specified.
     */
    public final int getWidth() {
        return width;
    }

    /**
     * @return current height, or zero if not yet specified.
     */
    public final int getHeight() {
        return height;
    }

    @Override public final void resize(int width, int height) {
        this.width = width;
        this.height = height;

        if (initialized) {
            onResize(width, height);
        }
    }

    @Override public final void render(float deltaTime) {
        if (!initialized) {
            createScreen(game, width, height);
            initialized = true;
            show();
            resize(width, height);
        }

        doRender(game, deltaTime);
    }

    @Override public final void show() {
        if (initialized) {
            setInputProcessor(getInputProcessor());
            onShow();
        }
    }

    @Override public final void hide() {
        onHide();
        setInputProcessor(null);
    }

    @Override public void pause() {
    }

    @Override public void resume() {
    }

    /**
     * Called when the screen is shown.
     */
    protected void onShow() {
    }

    /**
     * Called when the screen is hidden.
     */
    protected void onHide() {
    }

    protected abstract void createScreen(G game, int width, int height);


    /**
     * @return the input processor that input events should be routed to when this screen is visible.
     * This method is only called after create has been called.
     */
    protected abstract InputProcessor getInputProcessor();

    protected abstract void doRender(G game, float deltaTime);

    protected abstract void onResize(int width, int height);

    @Override public abstract void dispose();

    private void setInputProcessor(InputProcessor inputProcessor) {
        Gdx.input.setInputProcessor(inputProcessor);
    }



}
