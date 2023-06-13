package org.sga.teste.funcional.comunicacao;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sga.teste.funcional.core.Propriedades;

import java.util.Arrays;

public class AcessoComunicacao {

    private static WebDriver webDriver;

    private AcessoComunicacao() {
    }



    private static ChromeOptions opcaoDriverChrome() {
        //System.setProperty("webdriver.chrome.driver", "C:\\Users\\matheus.andrade\\Documents\\ProjetoTesteFuncionalSGA\\api\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
        chromeOptions.addArguments("disable-infobars"); // disabling infobars
        chromeOptions.addArguments("--disable-extensions"); // disabling extensions
        chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
        chromeOptions.addArguments("--allowed-ips");
        chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

        return chromeOptions;
    }

    public static WebDriver getComunicacaoDriverChrome() {
        if (webDriver == null) {
            switch (Propriedades.browser) {
                case FIREFOX:
                    webDriver = new FirefoxDriver();
                case CHROME:
                    webDriver = new ChromeDriver(opcaoDriverChrome());
            }
            //webDriver.manage().window().setSize(new Dimension(1200,1200));
        }
        return webDriver;
    }

    public static void getEncerrarNavegadorSessao() {
        if (webDriver != null) {
            webDriver.quit();
            webDriver = null;
        }

    }


}
