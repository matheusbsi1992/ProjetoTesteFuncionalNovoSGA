package org.sga.teste.funcional.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sga.teste.funcional.core.DSL;

import java.util.List;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;

public class MenuPage {

    private DSL dsl;

    public MenuPage() {
        dsl = new DSL();
    }


    public void selecionarMenuPageEsquerdo(String valorMenu) {

        List<WebElement> menuPageList = getComunicacaoDriverChrome().findElements(By.xpath("//section[@class='sidebar']//li//a//span"));

        for (WebElement valorMenuSelecionado : menuPageList) {
            //System.out.println(valorMenuSelecionado.getText());
            if (valorMenuSelecionado.getText().equalsIgnoreCase("Gráficos")) {
                dsl.clicarBotao(By.xpath("//span[@class='pull-right-container']"));
                dsl.clicarLink(By.xpath("//span[.='Gráficos']"));
                break;
            } else {
                if (valorMenuSelecionado.getText().equalsIgnoreCase("Relatórios")) {
                    dsl.clicarBotao(By.xpath("//span[@class='pull-right-container']"));
                    dsl.clicarLink(By.xpath("//span[.='Relatórios']"));
                    break;
                } else {
                    if (valorMenuSelecionado.getText().equalsIgnoreCase(valorMenu)) {
                        valorMenuSelecionado.click();
                        break;
                    }
                }
            }
        }
    }
}