package pages;

import org.openqa.selenium.By;
import utils.DSL;

import static utils.DriverFactory.getDriver;

public class SeuBarrigaMenu {

    private static DSL dsl = new DSL();


    public static void menuHome() {
        getDriver().findElement(By.xpath("//*[@id=\"navbar\"]/ul/li[1]/a")).click();
    }

    public static void menuContasAdicionar() throws InterruptedException {
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/a"); //Menu Contas
        Thread.sleep(500);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/ul/li[1]/a");//Submenu adicionar
    }


}
