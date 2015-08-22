package ld33.zenominator.screen;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import org.flowutils.Check;
import org.flowutils.service.ServiceProviderBase;

import java.util.ArrayList;
import java.util.List;

import static org.flowutils.Check.notNull;

/**
 * Manages screens and services.
 */
public class AdvancedGame<G extends AdvancedGame> extends ServiceProviderBase implements ApplicationListener {

    private final List<Screen<G>> screens = new ArrayList<>();
    private Screen<G> currentScreen;

    public G getGame() {
        return (G) this;
    }

    /**
     * @param newScreen screen to add.  It will be disposed when the application is disposed.
     * @return the added screen.
     */
    public final <S extends Screen<G>> S addScreen(S newScreen) {
        notNull(newScreen, "newScreen");
        Check.notContained(newScreen, screens, "screens");

        screens.add(newScreen);
        newScreen.setGame(getGame());

        return newScreen;
    }

    /**
     * @param newScreen new screen to show.
     */
    public final void setCurrentScreen(Screen<G> newScreen) {
        // Add to screens if we don't have it
        if (newScreen != null && !screens.contains(newScreen)) addScreen(newScreen);

        // Hide current if we have one
        if (currentScreen != null) currentScreen.hide();

        // Update screen
        currentScreen = newScreen;

        // Show new screen if it was not null
        if (currentScreen != null) {
            currentScreen.show();
            currentScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
    }

    /**
     * @return the currently visible screen, or null if none.
     */
    public final Screen<G> getCurrentScreen() {
        return currentScreen;
    }

    /**
     * Stops and quits the game.
     */
    public final void quit() {
        Gdx.app.exit();
    }

    @Override public final void pause () {
        if (currentScreen != null) currentScreen.pause();
    }

    @Override public final void resume () {
        if (currentScreen != null) currentScreen.resume();
    }

    @Override public final void render () {
        if (currentScreen != null) currentScreen.render(Gdx.graphics.getDeltaTime());
    }

    @Override public final void resize (int width, int height) {
        if (currentScreen != null) currentScreen.resize(width, height);
    }

    @Override public final void create() {
        // Initialize services
        init();

        // Call game onCreate
        onCreate();
    }

    @Override public final void dispose () {
        // Hide current screen
        if (currentScreen != null) currentScreen.hide();

        // Dispose screens
        for (Screen screen : screens) {
            screen.dispose();
        }

        // Allow descendant to dispose things
        onDispose();

        // Shutdown services
        shutdown();
    }

    /**
     * Called when the game is created, after services are initialized.
     */
    protected void onCreate() {
    }

    /**
     * Called when game is shut down.
     */
    protected void onDispose() {
    }

}
