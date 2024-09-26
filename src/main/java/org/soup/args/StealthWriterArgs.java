package org.soup.args;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class StealthWriterArgs {
    @Parameter(
            names = {"-m", "--message"},
            description = "The path to the output binary message",
            arity = 1
    )
    String messageFilename;

    @Parameter(
            names = {"-s", "--stego", "--stegocontainer"},
            description = "The path to the stegocontainer file",
            arity = 1
    )
    String stegoContainerFilename;

    @Parameter(
            names = {"-h", "--help"},
            description = "Help",
            help = true
    )
    boolean help;
}
