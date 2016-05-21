package de.goldmann.comercio.server.jms;

import java.io.File;

import org.junit.BeforeClass;
import org.springframework.util.FileSystemUtils;

public class JmsCleanUp
{
    @BeforeClass
    public static void cleanUp() throws Exception
    {
        // Clean out any ActiveMQ data from a previous run
        FileSystemUtils.deleteRecursively(new File("../temp"));
    }

}
