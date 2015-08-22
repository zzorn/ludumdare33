package ld33.zenominator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.GeometryUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import ld33.zenominator.components.Place;
import ld33.zenominator.components.Position;
import ld33.zenominator.components.Renderable;
import ld33.zenominator.processors.RenderingProcessor;
import ld33.zenominator.screen.AdvancedGame;
import ld33.zenominator.screens.GameScreen;
import ld33.zenominator.screens.MenuScreen;
import ld33.zenominator.services.SpeechService;
import org.entityflow.entity.Entity;
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
    public final GameScreen gameScreen;
    public final RenderingProcessor renderingProcessor;
    public Skin skin;
    public TextureAtlas textureAtlas;
    public final MenuScreen menuScreen;


    public Zenominator() {
        world = new ConcurrentWorld();

        // Create services
        speechService = addService(new SpeechService());

        // Create entity and other processors to the world
        renderingProcessor = world.addProcessor(new RenderingProcessor());

        // Create and add screens
        menuScreen = addScreen(new MenuScreen());
        gameScreen = addScreen(new GameScreen());
    }


    @Override protected void onCreate() {
        // Load skin and texture atlas
        textureAtlas = new TextureAtlas(Gdx.files.internal(Main.TEXTURE_ATLAS_PATH));
        skin = new Skin(Gdx.files.internal(Main.SKIN_PATH));

        // Initialize world
        world.init();
        setupWorld(world);

        // Set the initial screen to show
        setCurrentScreen(menuScreen);
    }



    @Override protected void onDispose() {
        // Shutdown world
        world.shutdown();
    }


    private void setupWorld(World world) {
        ModelBuilder modelBuilder = new ModelBuilder();
        Model model = modelBuilder.createSphere(1f, 1f, 1f, 16, 16,
                                       new Material(
                                               TextureAttribute.createDiffuse(textureAtlas.findRegion("xeno_joint"))
                                       ),
                                       VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);


        final Entity room = world.createEntity(new Place());
        world.createEntity(new Position(new Vector3(-1, -1, -1)),
                           new Renderable(model));
        world.createEntity(new Position(new Vector3(1, -1, -1)),
                           new Renderable(model));
        world.createEntity(new Position(new Vector3(1, -1, 3)),
                           new Renderable(model));


    }
}
