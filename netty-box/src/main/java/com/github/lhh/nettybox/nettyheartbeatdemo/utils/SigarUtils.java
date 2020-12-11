package com.github.lhh.nettybox.nettyheartbeatdemo.utils;

import org.hyperic.sigar.Sigar;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.MissingResourceException;

public class SigarUtils {

    static {
        String osName = System.getProperty("os.name", "generic").toLowerCase();
        String splitSymbol = osName.contains("win") ? ";" : ":";
        URL sigarUrl = SigarUtils.class.getResource("/sigar");
        if (null == sigarUrl) {
            throw new MissingResourceException("miss classpath:/sigar folder", SigarUtils.class.getName(), "classpath:/sigar");
        }
        File classPath = new File(sigarUrl.getFile());
        String oldLibPath = System.getProperty("java.library.path");
        try {
            String newLibPath = oldLibPath + splitSymbol + classPath.getCanonicalPath();
            System.setProperty("java.library.path", newLibPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Sigar getInstance() {
        return SingleSigar.SIGAR;
    }

    private static class SingleSigar {
        private static final Sigar SIGAR = new Sigar();
    }
}
