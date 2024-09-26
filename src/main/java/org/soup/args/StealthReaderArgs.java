package org.soup.args;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class StealthReaderArgs {
    @Parameter(
            names = {"-m", "--message"},
            description = "The path to the binary message",
            arity = 1
    )
    String messageFilename;

    @Parameter(
            names = {"-s", "--stego", "--stegocontainer"},
            description = "The path to the output stegocontainer file",
            arity = 1
    )
    String stegoContainerFilename;

    @Parameter(
            names = {"-c", "--container"},
            description = "The path to the text container",
            arity = 1,
            required = true
    )
    String containerFilename;

    @Parameter(
            names = {"-h", "--help"},
            description = "Help",
            help = true
    )
    boolean help;
}
