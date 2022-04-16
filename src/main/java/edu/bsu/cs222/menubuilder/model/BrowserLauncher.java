package edu.bsu.cs222.menubuilder.model;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.util.Locale;

public class BrowserLauncher {
    public static void openBrowserToURI(URI uri) throws IOException {
        if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("linux")) {
            openBrowserOnLinux(uri);
        } else if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(uri);
        } else {
            throw new IOException("Opening a web browser is not supported by your system.");
        }
    }

    public static void openBrowserOnLinux(URI uri) throws IOException {
        // Workaround for Linux because "Desktop.getDesktop().browse()" doesn't work on some Linux implementations
        if (Runtime.getRuntime().exec(new String[]{"which", "xdg-open"}).getInputStream().read() != -1) {
            Runtime.getRuntime().exec(new String[]{"xdg-open", uri.toString()});
        } else {
            throw new IOException("Can't launch default web browser; xdg-open not supported!");
        }
    }
}
