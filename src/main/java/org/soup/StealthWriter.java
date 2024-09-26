package org.soup;

import com.beust.jcommander.JCommander;
import org.soup.args.IOUtils;
import org.soup.args.StealthWriterArgs;
import org.soup.utils.SteganographyUtils;

public class StealthWriter {
    public static void main(String[] args) {
        StealthWriterArgs stealthWriterArgs = new StealthWriterArgs();
        JCommander jc = JCommander.newBuilder()
                .addObject(stealthWriterArgs).build();
        jc.parse(args);

        if (stealthWriterArgs.isHelp()) {
            jc.usage();
            return;
        }

        String containerText = IOUtils.readFileOrStdInToString(stealthWriterArgs.getStegoContainerFilename());
        byte[] messageBytes = SteganographyUtils.decode(containerText);

        IOUtils.writeBytesToFileOrStdOut(stealthWriterArgs.getMessageFilename(), messageBytes);
    }
}
