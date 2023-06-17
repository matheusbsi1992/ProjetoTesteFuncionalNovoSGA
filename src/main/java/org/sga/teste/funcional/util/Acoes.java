package org.sga.teste.funcional.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;

public class Acoes {

    public void acaoScroll(WebElement webElement) {
        Actions actions = new Actions(getComunicacaoDriverChrome());
        actions.scrollToElement(webElement).perform();
    }


}
