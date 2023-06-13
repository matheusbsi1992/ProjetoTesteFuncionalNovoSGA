package org.sga.teste.funcional.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sga.teste.funcional.util.Sincronismo;
import org.sga.teste.funcional.core.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;


public class ServicoPage {

    private DSL dsl;

    private Sincronismo sincronismo;

    public ServicoPage() {
        sincronismo = new Sincronismo();
        dsl = new DSL();
    }

    //Campos contidos na pagina de Insercao, Alteracao e Remoção
    public void setNomeServico(String nomeServico) {
        dsl.escreve("nomeservico", nomeServico);
    }

    //--Escrever o tipo de descricao no campo de descricaoservico
    public void setDescricaoServico(String nomeDescricaoServico) {
        dsl.escreve("descricaoservico", nomeDescricaoServico);
    }

    //--selecionar o tipo de status
    public void setSelecionarStatusAtivo() {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        sincronismo.sincronismoExplicito(By.id("status"));
        dsl.clicarBotao(By.xpath("//*[@id='status']//span"));
        dsl.clicarBotao(By.id("status_1"));
    }

    //campo contido para buscar o Texto inserido a seguir
    public void setNomeServicoBuscar(String nomeServicoBuscarBuscar) {
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