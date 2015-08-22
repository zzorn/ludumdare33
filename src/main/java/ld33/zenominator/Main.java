package ld33.zenominator;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import org.flowutils.LogUtils;

/**
 * Main entrypoint.
 * Desktop only this time.
 */
public class Main {
    public static final String ASSET_SOURCE_DIR = "asset-sources/";
    public static final String ASSET_TARGET_DIR = "assets/";
    public static final String TEXTURE_SUBDIR = "textures/";
    public static final String SKINS_SUBDIR = "skins/";
    public static final String TEXTURE_ATLAS_EXTENSION = ".atlas";

    public static final String TEXTURE_ATLAS_NAME = "TextureAtlas";
    public static final String SKIN_NAME = "uiskin.json";

    public static final String TEXTURE_ATLAS_PATH = ASSET_TARGET_DIR + TEXTURE_SUBDIR + TEXTURE_ATLAS_NAME + TEXTURE_ATLAS_EXTENSION;
    public static final String SKIN_PATH = ASSET_TARGET_DIR + SKINS_SUBDIR + SKIN_NAME;

    public static final int MAX_TEXTURE_SIZE = 512;

    public static void main(String[] args) {
        final CommandLineParameters commandLineParameters = CommandLineParameters.parseCommandLineParameters(args, Zenominator.TITLE, Zenominator.DESCRIPTION);

        // Pack textures if requested
        if (commandLineParameters.isUpdateTextures()) packTextures();

        // Create desktop 3D window
        final LwjglApplicationConfiguration configuration = new LwjglApplicationConfiguration();
        configuration.title = Zenominator.TITLE;
        configuration.width = 1000;
        configuration.height = 700;

        // Fix broken defaults buffer sizes (at least on linux the small default buffers result in mangled sound)
        configuration.audioDeviceBufferSize = 4*2048;
        configuration.audioDeviceBufferCount = 6;

        // Create view
        new LwjglApplication(new Zenominator(), configuration);
    }


    /**
     * This will load textures from the asset-sources/textures directory and merge them into bigger textures
     * to the assets/textures directory, along with an atlas file that contains the coordinates of the subtextures
     * inside the new combined texture.
     */
    public static void packTextures() {
        LogUtils.getLogger().info("Updating textures.");

        final TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.maxWidth = MAX_TEXTURE_SIZE;
        settings.maxHeight = MAX_TEXTURE_SIZE;
        settings.paddingX = 2;
        settings.paddingY = 2;
        TexturePacker.process(settings,
                              ASSET_SOURCE_DIR + TEXTURE_SUBDIR,
                              ASSET_TARGET_DIR + TEXTURE_SUBDIR,
                              TEXTURE_ATLAS_NAME);

        LogUtils.getLogger().debug("Textures updated.");

    }

    private Main() {
    }
}
