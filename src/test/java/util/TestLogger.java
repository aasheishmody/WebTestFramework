package util;

import org.picocontainer.classname.ClassName;

import java.util.logging.Logger;

public class TestLogger {

    private static Logger logger;

    public static void setLogger() {
        TestLogger.logger = Logger.getLogger(ClassName.class.getName());
    }

    public static Logger getLogger() {
        return logger;
    }

    public static void navigating(String name, Runnable function) {
        getLogger().info("Navigating " + name);
        function.run();
        getLogger().info("Navigated " + name);
    }

    public static void searching(String name, Runnable function) {
        getLogger().info("Searching " + name);
        function.run();
        getLogger().info("Searched " + name);
    }

    public static void asserting(String name, Runnable function) {
        getLogger().info("Asserting " + name);
        function.run();
        getLogger().info("Asserted " + name);
    }

    public static void clicking(String name, Runnable function) {
        getLogger().info("Tapping " + name);
        function.run();
        getLogger().info("Tapped " + name);
    }

    public static void removing(String name, Runnable function) {
        getLogger().info("Removing " + name);
        function.run();
        getLogger().info("Removed " + name);
    }

    public static void installing(String name, Runnable function) {
        getLogger().info("Installing " + name);
        function.run();
        getLogger().info("Installed " + name);
    }

    public static void launching(String name, Runnable function) {
        getLogger().info("Launching " + name);
        function.run();
        getLogger().info("Launched " + name);
    }

    public static void swiping(String name, Runnable function) {
        getLogger().info("Swiping " + name);
        function.run();
        getLogger().info("Swiped " + name);
    }

    public static void entering(String name, Runnable function) {
        getLogger().info("Entering " + name);
        function.run();
        getLogger().info("Entered " + name);
    }

    public static void selecting(String name, Runnable function) {
        getLogger().info("Selecting " + name);
        function.run();
        getLogger().info("Selected " + name);
    }

    public static void dismissing(String name, Runnable function) {
        getLogger().info("Dismissing " + name);
        function.run();
        getLogger().info("Dismissed " + name);
    }

    public static void refreshing(String name, Runnable function) {
        getLogger().info("Refreshing " + name);
        function.run();
        getLogger().info("Refreshed " + name);
    }
}