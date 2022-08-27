package org.framework.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class webDriverChrome {


    public WebDriver initialize(){

        System.setProperty("webdriver.chrome.driver","webdriver/chromedriver.exe");
        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless");
        WebDriver driver = new ChromeDriver(opts);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }

}
