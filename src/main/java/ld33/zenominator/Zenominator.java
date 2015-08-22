package ld33.zenominator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ld33.zenominator.screen.AdvancedGame;
import ld33.zenominator.screens.MenuScreen;
import ld33.zenominator.services.SpeechService;
import org.entityflow.world.ConcurrentWorld;
import org.entityflow.world.World;

/**
 *
 */
public final class Zenominator extends AdvancedGame<Zenominator> {

    public static final String TITLE = "Zenominator";
    public static final String DESCRIPTION = "A fast paced action adventure procedural buzzword alien xenomorph shooter";

    public final World world;
    public final SpeechService speechService;
    public Skin skin;
    public TextureAtlas textureAtlas;
    public final MenuScreen menuScreen;


    public Zenominator() {
        world = new ConcurrentWorld();

        // Create services
        speechService = addService(new SpeechService());

        // Create entity and other processors to the world
        // TODO

        // Create and add screens
        menuScreen = addScreen(new MenuScreen());
    }


    @Override protected void onCreate() {
        // Initialize world
        world.init();

        // Load skin and texture atlas
        textureAtlas = new TextureAtlas(Gdx.files.internal(Main.TEXTURE_ATLAS_PATH));
        skin = new Skin(Gdx.files.internal(Main.SKIN_PATH));

        // Set the initial screen to show
        setCurrentScreen(menuScreen);
    }

    @Override protected void onDispose() {
        // Shutdown world
        world.shutdown();
    }
}
