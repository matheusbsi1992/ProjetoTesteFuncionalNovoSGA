package org.sga.teste.funcional.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;

public class Sincronismo {

    public void sincronismoExplicito(By by) {
        WebDriverWait webDriverWait = new WebDriverWait(getComunicacaoDriverChrome(), Duration.ofSeconds(30));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        webDriverWait.pollingEvery(Duration.ofSeconds(0));
    }

    public void sincronismoExplicitoInterno(By by) {
        //--Se aplica as condicoes em selecoes nao indica a relacao de visualizacao
        WebDriverWait webDriverWait = new WebDriverWait(getComunicacaoDriverChrome(), Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        webDriverWait.pollingEvery(Duration.ofSeconds(0));
    }

    public void sincronismoExplicitoClicar(By by){
        WebDriverWait wait = new WebDriverWait(getComunicacaoDriverChrome(), Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }



}
