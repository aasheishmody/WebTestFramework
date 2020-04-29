package util;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.picocontainer.classname.ClassName;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

public class ReportGenerator {
    private static Logger logger = Logger.getLogger(ClassName.class.getName());

    public static void main(String[] args) throws Throwable {
        ReportGenerator reportGenerator = new ReportGenerator();
        reportGenerator.mergeReports();
    }

    private void mergeReports() throws Exception {
        logger.info("Merging reports");
        AtomicReference<File> reportOutputDirectory;
        reportOutputDirectory = new AtomicReference<>(new File((System.getProperty("user.dir") + "/target")));
        List<String> list = Arrays.asList(reportOutputDirectory.get().list((dir, name) -> name.endsWith(".json")));
        List<String> jsonReportFiles = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++) {
            jsonReportFiles.add((System.getProperty("user.dir") + "/target/" + i + ".json"));
        }
        logger.info("Reports merged");
        WebConnector.getInstance().setProperties();
        Configuration configuration;
        configuration = new Configuration(reportOutputDirectory.get(), "Test Report - Web " + "(" + WebConnector.getInstance().getProperties().getProperty("browser").toUpperCase() + " - " + WebConnector.getInstance().getProperties().getProperty("environment") + ")");
        ReportBuilder reportBuilder = new ReportBuilder(jsonReportFiles, configuration);
        logger.info("Generating report");
        try {
            reportBuilder.generateReports();
            logger.info("Report generated");
        } catch (Throwable exception) {
            exception.printStackTrace();
            logger.severe(exception.getMessage());
        }

        /*File oldFile = new File(String.valueOf(reportOutputDirectory + "/cucumber-html-reports"));
        boolean created = oldFile.createNewFile();
        System.out.println("File created? " + created);
        String timeStamp = new SimpleDateFormat("dd.MM.yyyy.HH.mm.ss").format(new Date());
        // Creates the target file.
        File newFile = new File(String.valueOf(oldFile) + timeStamp);

        // The renameTo() method renames file or directory to a
        // new name by passing the new destination file.
        boolean renamed = oldFile.renameTo(newFile);
        System.out.println("File renamed? " + renamed);*/

    }
}