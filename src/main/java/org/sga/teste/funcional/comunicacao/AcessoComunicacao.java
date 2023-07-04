package org.sga.teste.funcional.comunicacao;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.sga.teste.funcional.core.Propriedades;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AcessoComunicacao {

    private static WebDriver webDriver;

    private AcessoComunicacao() {
    }


    private static ChromeOptions opcaoDriverChrome() {

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

    private static ChromeOptions opcaoDriverChromeNuvem(){
        ChromeOptions chromeOptions = new ChromeOptions();
        //browserOptions.setPlatformName("Windows 10");
        chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
        chromeOptions.addArguments("disable-infobars"); // disabling infobars
        chromeOptions.addArguments("--disable-extensions"); // disabling extensions
        chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
        chromeOptions.addArguments("--allowed-ips");
        chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
        chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
        //browserOptions.setBrowserVersion("latest");
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "oauth-matheusbsi1992-b65f5");
        sauceOptions.put("accessKey", "b0e082dc-afd3-47fd-82b5-b01fbb7caf2c");
        sauceOptions.put("build", "selenium-build-WEBLD");
        sauceOptions.put("name", "matheusbsi1992@gmail.com");
        chromeOptions.setCapability("sauce:options", sauceOptions);

        return chromeOptions;
    }

    public static WebDriver getComunicacaoDriverChrome() {
        if(Propriedades.TipoExecucao.LOCAL== Propriedades.TipoExecucao.LOCAL) {
            if (webDriver == null) {
                switch (Propriedades.browser) {
                    case FIREFOX:
                        webDriver = new FirefoxDriver();
                    case CHROME:
                        webDriver = new ChromeDriver(opcaoDriverChrome());
                }
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
