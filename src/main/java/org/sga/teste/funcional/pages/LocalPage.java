package org.sga.teste.funcional.pages;

import org.openqa.selenium.By;
import org.sga.teste.funcional.util.Sincronismo;
import org.sga.teste.funcional.core.DSL;


public class LocalPage {

    private DSL dsl;

    private Sincronismo sincronismo;

    public LocalPage() {
        sincronismo = new Sincronismo();
        dsl = new DSL();
    }

    //Campo contido na pagina de Insercao e de Alteracao
    public void setNomeLocal(String nomeLocal) {
        dsl.escreve("nomelocal", nomeLocal);
    }

    //campo contido para buscar o Texto inserido a seguir
    public void setNomeBuscar(String nomeBuscar) {
        //dsl.escreve(By.id("consultar:j_idt171"), nomeBuscar);
        dsl.escreve(By.xpath("//div[@class='ui-panel ui-widget ui-widget-content ui-corner-all ui-g-12']//input"), nomeBuscar);
    }

    //Obter mensagem de erro no campo de span na validacao do campo vazio
    public String mensagemDeErroCampoNaoPreenchidoComSucesso() {
        sincronismo.sincronismoExplicito(By.xpath("//span[@class='ui-message-error-detail']"));
        return dsl.obterTexto(By.xpath("//span[@class='ui-message-error-detail']"));
    }

    //Obter mensagem de exito ao inserir ou alterar o Local
    public String mensagemDeExitoCampoPreenchidoComSucesso() {
        sincronismo.sincronismoExplicito(By.xpath("//span[@class='ui-messages-info-summary']"));
        return dsl.obterTexto(By.xpath("//span[@class='ui-messages-info-summary']"));
    }

    public String mensagemDeNaoExisteElementoNaTabelaLocal() {
        //sincronismo.sincronismoExplicito(By.xpath("//span[@class='ui-messages-info-summary']"));
        return dsl.obterTexto(By.xpath("//td[.='Nenhum Registro Encontrado.']"));
    }

    //Botao contido na pagina de Listar
    public void botaoNovo() {
       //sincronismo.sincronismoExplicitoClicar(By.xpath("//span[.='Novo']"));
       dsl.clicarBotao(By.xpath("//span[.='Novo']"));
    }

    //Botao contido na pagina de Listar
    public void botaoSalvar() {
        dsl.clicarBotao(By.xpath("//span[.='Salvar']"));
    }

    public void botaoBuscar() {
        //dsl.clicarBotao("consultar:j_idt172");
        //dsl.clicarBotao(By.cssSelector("span.ui-button-icon-left ui-icon ui-c fa fa-search"));
        dsl.clicarBotao(By.xpath("//div[@class='ui-panel ui-widget ui-widget-content ui-corner-all ui-g-12']//button"));
    }


}