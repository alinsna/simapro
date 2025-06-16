package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;

    /**
     * Mengambil instance tunggal dari ExtentReports (Singleton Pattern).
     * Jika instance belum ada, maka akan dibuat terlebih dahulu.
     * @return instance ExtentReports.
     */
    public static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    /**
     * Membuat instance ExtentReports dan mengkonfigurasi reporter.
     * Metode ini hanya dipanggil sekali.
     */
    private static void createInstance() {
        // Menentukan path dan nama file report
        String reportPath = System.getProperty("user.dir") + "/test-output/AutomationTestReport.html";

        // Mengkonfigurasi tampilan report (Spark Reporter)
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Automation Test Report");
        sparkReporter.config().setReportName("LSP UGM Test Results");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setEncoding("utf-8");

        // Membuat objek utama ExtentReports
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Menambahkan informasi sistem ke dalam report
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("QA", "Olaf"); // Ganti dengan nama Anda
        extent.setSystemInfo("Environment", "Staging");
    }

    public static void endReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}