package org.sga.teste.funcional.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sga.teste.funcional.util.Acoes;
import org.sga.teste.funcional.util.Sincronismo;
import org.sga.teste.funcional.core.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;


public class TriagemAlternativaPage {

    private DSL dsl;

    private Sincronismo sincronismo;

    private Acoes  acoes;

    public TriagemAlternativaPage() {
        sincronismo = new Sincronismo();
        dsl = new DSL();
        acoes = new Acoes();
    }

    //Campos contidos na pagina de Insercao, Alteracao e Remoção

    //--informar o campo de triagemAlternativa
    public void setNomeTriagemAlternativa(String nomeTriagemAlternativa) {
        dsl.escreve("siglatriagemalternativa", nomeTriagemAlternativa);
    }

    //--selecionar o tipo de servico
    public void setSelecionarTipoServico(String tipoServico) {

        dsl.clicarBotao(By.xpath("//*[@id='servico']//span"));
        sincronismo.sincronismoExplicitoInterno(By.xpath("//*[@id='servico_items']//li"));
        WebElement webDriverElement = getComunicacaoDriverChrome().findElement(By.xpath("//*[@id='servico_items']//li[.='" + tipoServico + "']"));
//        dsl.executarJS("var objDiv = document.getElementById(\"servico_items\");\n" +
//                "objDiv.scrollTop = objDiv.scrollHeight;",webDriverElement.getLocation().y);
        //dsl.executarJS("arguments[0].scrollTop = arguments[1];",webDriverElement,100);
        acoes.acaoScroll(webDriverElement);
//        List<WebElement> webElement = getComunicacaoDriverChrome().findElements(By.xpath("//*[@id='servico_items']//li"));
//        for (WebElement webElemento: webElement) {
//            if(webElemento.getText().equalsIgnoreCase(valorServico)){
//                dsl.clicarBotao(By.R(webElemento.getText()));
//                break;
//            }
//        }
        //dsl.selecionarListarCombo(By.id("servico_12"),"Teste".toUpperCase());
        dsl.clicarBotao(By.xpath("//li[.='" + tipoServico + "']"));
    }

    //--selecionar o tipo de local do servico
    public void setSelecionarLocaldoServico(String localdoServico) {
        //dsl.clicarBotao(By.xpath("//*[@id='local']//span"));
        //sincronismo.sincronismoExplicitoInterno(By.xpath("//*[@id='local_items']//li"));
        sincronismo.sincronismoExplicitoClicar(By.xpath("//*[@id='local']//span"));
        //WebElement webDriverElement = getComunicacaoDriverChrome().findElement(By.id("local_items"));
        //dsl.executarJS("var objDiv = document.getElementById(\"servico_items\");\n" +
        //"objDiv.scrollTop = objDiv.scrollHeight;",webDriverElement.getLocation().y);
        //dsl.executarJS("arguments[0].scrollTop = arguments[1];",webDriverElement,100);
        //dsl.executarJS("window.scrollBy(0,arguments[0])",webDriverElement.getLocation().y);
        //dsl.clicarBotao(By.xpath("//*[@id='local_items']//li[.='" + localdoServico + "']"));
        sincronismo.sincronismoExplicitoClicar(By.xpath("//*[@id='local_items']//li[.='" + localdoServico + "']"));
    }

    //--selecionar o tipo de status
    public void setSelecionarStatusAtivo() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
         // sincronismo.sincronismoExplicito(By.id("status_panel"));

          sincronismo.sincronismoExplicitoClicar(By.xpath("//div[@id='status']"));
//        WebElement webDriverElement = getComunicacaoDriverChrome().findElement(By.xpath("//*[@id='servico_items']//li[.='" + localdoServico + "']"));
//        dsl.executarJS("var objDiv = document.getElementById(\"servico_items\");\n" +
//       "objDiv.scrollTop = objDiv.scrollHeight;",webDriverElement.getLocation().y);
//        dsl.executarJS("arguments[0].scrollTop = arguments[1];",webDriverElement,100);
//        Actions actions = new Actions(getComunicacaoDriverChrome());
//        actions.scrollToElement(webDriverElement).perform();
//        WebElement webDriverElement = getComunicacaoDriverChrome().findElement(By.id("status_panel"));
//        dsl.executarJS("window.scrollBy(0,arguments[0])",webDriverElement.getLocation().y);
        //dsl.clicarBotao(By.id("status_1"));
        sincronismo.sincronismoExplicitoClicar(By.id("status_1"));
        //Terra
        //dsl.selecionarCombo(By.id("status_input"),"A");
        //dsl.selecionarListarCombo(By.id("status_input"),"Ativo");
    }

    //campo contido para buscar o Texto inserido a seguir
    public void setNomeTriagemBuscar(String nomeServicoBuscarBuscar) {
        dsl.escreve(By.id("consultar:j_idt171"), nomeServicoBuscarBuscar);
    }

    //campo contido para buscar o Texto inserido a seguir
    public void setNomeBuscar(String nomeBuscar) {
        //dsl.escreve(By.id("consultar:j_idt172"), nomeBuscar);
        dsl.escreve(By.xpath("//div[@class='ui-panel ui-widget ui-widget-content ui-corner-all ui-g-12']//input"), nomeBuscar);
    }

    //Obter mensagem de erro no campo de span na validacao do campo vazio
    public String mensagemDeErroCampoNaoPreenchidoComSucesso() {
        sincronismo.sincronismoExplicito(By.xpath("//span[@class='ui-message-error-detail']"));
        return dsl.obterTexto(By.xpath("//span[@class='ui-message-error-detail']"));
    }

    //Obter lista de identificacoes de mensagens de erro em cada campo correspondente
    public List<String> identificarErros() {
        List<WebElement> listadeElementosErros = getComunicacaoDriverChrome().findElements(By.xpath("//span[@class='ui-message-error-detail']"));
        List<String> errors = new ArrayList<>();
        for (WebElement element : listadeElementosErros) {
            errors.add(element.getText());
        }
        return errors;
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