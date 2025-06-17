package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtil {

    /**
     * Mengambil screenshot dari halaman saat ini dan mengembalikannya
     * sebagai string yang di-encode dengan Base64.
     *
     * @param driver WebDriver instance yang sedang berjalan.
     * @return String Base64 dari gambar screenshot.
     */
    public static String takeScreenshotAsBase64(WebDriver driver) {
        if (driver == null) {
            System.err.println("WebDriver is null, cannot take screenshot.");
            return null;
        }
        try {
            // Langsung ambil screenshot sebagai Base64
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.err.println("Failed to take screenshot as Base64: " + e.getMessage());
            return null;
        }
    }
}