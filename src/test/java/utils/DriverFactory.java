package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static utils.Variaveis.*;

public class DriverFactory {

    private static WebDriver navegador;

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (navegador == null) {
            System.setProperty(property, chromedriver);
            navegador = new ChromeDriver();
            navegador.manage().window().maximize();
        }
        return navegador;
    }

    public static void killDriver() {
        if (navegador != null) {
            navegador.quit();
            navegador = null;
        }
    }

}
