package org.sga.teste.funcional.comunicacao;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sga.teste.funcional.core.Propriedades;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class AcessoComunicacao {

    private static WebDriver webDriver;

    private static final String USERNAME = "oauth-matheusbsi1992-b65f5";
    private static final String ACESS_KEY = "4e5f8d0e-8134-4a57-ae72-aaedec5ba3e9";
    private static final String URL = "https://" + USERNAME + ":" + ACESS_KEY + "@ondemand.us-west-1.saucelabs.com:443/wd/hub";
    private static RemoteWebDriver remoteWebDriver = null;
    private static URL url = null;

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

//    private static ChromeOptions opcaoDriverChromeNuvem() {
//        ChromeOptions chromeOptions = new ChromeOptions();
//        //browserOptions.setPlatformName("Windows 10");
//        chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
//        chromeOptions.addArguments("disable-infobars"); // disabling infobars
//        chromeOptions.addArguments("--disable-extensions"); // disabling extensions
//        chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
//        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model
//        chromeOptions.addArguments("--allowed-ips");
//        chromeOptions.addArguments("--remote-allow-origins=*", "ignore-certificate-errors");
//        chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
//        //browserOptions.setBrowserVersion("latest");
////        Map<String, Object> sauceOptions = new HashMap<>();
////        sauceOptions.put("username", "oauth-matheusbsi1992-b65f5");
////        sauceOptions.put("accessKey", "4e5f8d0e-8134-4a57-ae72-aaedec5ba3e9");
////        sauceOptions.put("build", "selenium-build-WEBLD");
////        sauceOptions.put("name", "matheusbsi1992@gmail.com");
////        chromeOptions.setCapability("sauce:options", sauceOptions);
//
//
//        return chromeOptions;
//    }

    public static WebDriver getComunicacaoDriverChrome() {
        if (Propriedades.tipodeExecucao == Propriedades.TipoExecucao.LOCAL) {
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
        if (Propriedades.tipodeExecucao == Propriedades.TipoExecucao.GRID) {
            if (webDriver == null) {
                switch (Propriedades.browser) {
                    case FIREFOX:
                        webDriver = new FirefoxDriver();
                    case CHROME:
                        try {

                            webDriver = new RemoteWebDriver(url, opcaoDriverChrome());
                        } catch (Exception e) {
                            System.out.println("Falha Com a Conexão para ´|||´=´|||´Grid´||||´=´||||´");
                            e.printStackTrace();
                        }
                        //webDriver = new ChromeDriver(opcaoDriverChromeNuvem());
                }
            }
            //webDriver.manage().window().setSize(new Dimension(1200,1200));
        }
        if (Propriedades.tipodeExecucao == Propriedades.TipoExecucao.NUVEM) {
            if (webDriver == null) {
                switch (Propriedades.browser) {
                    case FIREFOX:
                        webDriver = new FirefoxDriver();
                    case CHROME:
                        try {
                            url = new URL(URL);
                            webDriver = new RemoteWebDriver(url, opcaoDriverChrome());
                        } catch (MalformedURLException e) {
                            System.out.println("Falha Com a Conexão para ´{´,´}´Nuvem´{´,´}´ SAUCELABS");
                            e.printStackTrace();
                        }
                        //webDriver = new ChromeDriver(opcaoDriverChromeNuvem());
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
