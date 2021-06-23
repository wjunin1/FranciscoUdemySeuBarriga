package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

import static utils.DriverFactory.getDriver;

public class Screenshot {

    protected ExtentReports extentReports;
    protected ExtentTest test;


    final String filePath = "./relatorio/Relatório_de_Testes_Automatizados_"
            .concat(DataHora.getDate()) + "_"
            .concat(DataHora.getTimeHHmm()) + ".html";


    public static String screen(WebDriver nav, String destScreeshot) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File scr = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty
                ("user.dir") + "/relatorio/" + destScreeshot;
        File destination = new File(path);

        try {
            FileUtils.copyFile(scr, destination);
        } catch (Exception e) {
            System.out.println("Erro ao copiar" + e.getMessage());
        }
        return destScreeshot;
    }

    @AfterMethod
    protected void afterMethod(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(LogStatus.FAIL, "Test Fail"
                    +
                    test.addScreenCapture(screen(getDriver(),
                            result.getMethod().getMethodName() + ".jpg")));
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, "Test skipped " +
                    result.getThrowable() +
                    test.addScreenCapture(screen(getDriver(),
                            result.getMethod().getMethodName() + ".jpg")));
        } else {
            test.log(LogStatus.PASS, "Test Passed" +
                    test.addScreenCapture(screen(getDriver(),
                            result.getMethod().getMethodName() + ".jpg")));
        }
        extentReports.endTest(test);
        extentReports.flush();
    }

    @BeforeSuite
    public void beforeSuite() {
        extentReports = ExtentManager.getReporter(filePath);
    }

    @AfterSuite
    protected void afterSuite() {
        extentReports.close();
    }

    public static class ExtentManager {
        private static ExtentReports extent;

        public synchronized static ExtentReports getReporter(String filePath) {
            if (extent == null) {
                extent = new ExtentReports(filePath, true);

                extent
                        .addSystemInfo("Sistema", "Renda Fixa - SIIFX")
                        .addSystemInfo("Ambiente", "Desenvolvimento")
                        .addSystemInfo("Autor", "Wellington Júnior")
                        .addSystemInfo("Squad", "Loki");
            }
            return extent;
        }
    }
}
