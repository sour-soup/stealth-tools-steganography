package org.soup;

import com.beust.jcommander.JCommander;
import org.soup.args.IOUtils;
import org.soup.args.StealthReaderArgs;
import org.soup.utils.SteganographyUtils;

public class StealthReader {
    public static void main(String[] args) {
        StealthReaderArgs stealthReaderArgs = new StealthReaderArgs();
        JCommander jc = JCommander.newBuilder()
                .addObject(stealthReaderArgs).build();
        jc.parse(args);

        if (stealthReaderArgs.isHelp()) {
            jc.usage();
            return;
        }

        byte[] messageBytes = IOUtils.readFileOrStdInToByteArray(stealthReaderArgs.getMessageFilename());
        String containerText = IOUtils.readFileOrStdInToString(stealthReaderArgs.getContainerFilename());
        String encodedText = SteganographyUtils.encode(containerText, messageBytes);
        IOUtils.writeStringToFileOrStdOut(stealthReaderArgs.getStegoContainerFilename(), encodedText);
    }

}
