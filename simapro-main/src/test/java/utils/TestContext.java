package utils;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private WebDriver driver;
    private ExtentTest test;

    // === TAMBAHAN UNTUK BERBAGI DATA ANTAR LANGKAH ===
    private final Map<String, Object> scenarioContext;

    public TestContext() {
        // Inisialisasi map saat TestContext dibuat untuk menghindari NullPointerException
        scenarioContext = new HashMap<>();
    }
    // ===============================================

    // Getters and Setters untuk Driver dan Test (sudah ada)
    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public ExtentTest getTest() {
        return test;
    }

    public void setTest(ExtentTest test) {
        this.test = test;
    }

    // === METODE BARU YANG DIPERLUKAN ===

    /**
     * Menyimpan data ke dalam context skenario dengan sebuah key.
     * @param key Kunci untuk identifikasi data (contoh: "newStakeholderName").
     * @param value Nilai data yang ingin disimpan (contoh: "Stakeholder 12345").
     */
    public void set(String key, Object value) {
        scenarioContext.put(key, value);
    }

    /**
     * Mengambil data dari context skenario menggunakan key.
     * @param key Kunci data yang ingin diambil.
     * @return Nilai data yang tersimpan.
     */
    public Object get(String key) {
        return scenarioContext.get(key);
    }
}