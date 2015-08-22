package ld33.zenominator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

/**
 * Parameters passed to the program when it was started.
 */
public class CommandLineParameters {

    @Parameter(names = {"-h", "--help"}, description = "Print this help message.", help = true)
    private boolean help = false;

    @Parameter(names = {"--update-textures"}, description = "Updates the texture atlas from source images.  Use during development.", hidden = true)
    private boolean updateTextures = false;

    public static CommandLineParameters parseCommandLineParameters(String[] args, String title, String desc) {
        // Parse command line parameters with JCommander, the parameters will be saved in a CommandLineParameters instance.
        final CommandLineParameters commandLineParameters = new CommandLineParameters();
        final JCommander jCommander = new JCommander(commandLineParameters, args);

        // Print help if requested
        if (commandLineParameters.isHelpRequested()) {
            jCommander.setProgramName(title);
            System.out.println(desc);
            jCommander.usage();
            System.exit(0);
        }
        return commandLineParameters;
    }

    public boolean isHelpRequested() {
        return help;
    }

    public boolean isUpdateTextures() {
        return updateTextures;
    }
}
