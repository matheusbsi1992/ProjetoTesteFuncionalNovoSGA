package org.sga.teste.funcional.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sga.teste.funcional.core.DSL;
import org.sga.teste.funcional.util.Sincronismo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;


public class UsuarioPage {

    private DSL dsl;

    private Sincronismo sincronismo;

    public UsuarioPage() {
        sincronismo = new Sincronismo();
        dsl = new DSL();
    }

    //Campos contidos na pagina de Insercao, Alteracao e Remoção
    public void setUsuario(String usuario) {
        dsl.escreve("formulariousuario:tabview:usuario", usuario);
    }

    //--Escrever o nome do usuario
    public void setNomeUsuario(String nomeUsuario) {
        dsl.escreve("formulariousuario:tabview:sobrenomeusuario", nomeUsuario);
    }
    //-Escrever a senha do usuario
    public void setPassword(String password) {
        dsl.escreve("formulariousuario:tabview:password", password);
    }
    //-Escrever a confirmacao da senha do usuario
    public void setConfirmacaoPassword(String confirmacaoPassword) {
        dsl.escreve("formulariousuario:tabview:senha", confirmacaoPassword);
    }
    //--selecionar o tipo de status
    public void setSelecionarStatusAtivo() {
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        sincronismo.sincronismoExplicito(By.id("formulariousuario:tabview:status"));
        dsl.clicarBotao(By.xpath("//*[@id='formulariousuario:tabview:status']//span"));
        dsl.clicarBotao(By.id("formulariousuario:tabview:status_1"));
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
            if (!element.getText().equalsIgnoreCase("")) {
                errors.add(element.getText());
            }
        }
        //forca a lista de String remover espaco em branco
        if (errors.toString().equalsIgnoreCase("")) {
            errors.remove("");
        }

        return errors;
    }

    public void retornarListadeValoresPermissao() {

        List<Object> listaPermissao = Arrays.asList("ATENDIMENTO"
                , "CARGO"
                , "ESTATÍSTICA"
                , "LOCAL"
                , "MONITOR"
                , "PAINEL DE ATENDIMENTO"
                , "REINICIAR SENHA"
                , "REINICIAR SENHA POR SERVIÇO"
                , "SERVIÇO"
                , "TRIAGEM ALTERNATIVA"
                , "TRIAGEM CHAMADACLIENTE"
                , "UNIDADE"
                , "USUÁRIO");

        for (Object objetoPermissao :
                listaPermissao) {
            sincronismo.sincronismoExplicitoClicar(By.xpath("//input[@value='" + objetoPermissao.toString() + "']//../..//span"));
        }

    }

    //Obter mensagem de exito ao inserir ou alterar o Local
    public String mensagemDeExitoCampoPreenchidoComSucesso() {
        sincronismo.sincronismoExplicito(By.xpath("//span[@class='ui-messages-info-summary']"));
        return dsl.obterTexto(By.xpath("//span[@class='ui-messages-info-summary']"));
    }

    public String mensagemDeNaoExisteElementoNaTabelaCargo() {
        sincronismo.sincronismoExplicito(By.xpath("//td[.='Nenhum Registro Encontrado.']"));
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

    //Botao contido na pagina de insercao para a Aba Geral
    public void botaoGeral() {
        //sincronismo.sincronismoExplicitoClicar(By.xpath("//a[.='Geral']"));
        dsl.clicarBotao(By.xpath("//a[.='Geral']"));
    }

    //Botao contido na pagina de insercao para a Aba Geral
    public void botaoPermissoes() {
        sincronismo.sincronismoExplicitoClicar(By.xpath("//a[.='Permissões']"));
        //dsl.clicarBotao(By.xpath("//a[.='Permissões']"));
    }

    public void botaoBuscar() {
        //dsl.clicarBotao("consultar:j_idt172");
        //dsl.clicarBotao(By.cssSelector("span.ui-button-icon-left ui-icon ui-c fa fa-search"));
        dsl.clicarBotao(By.xpath("//div[@class='ui-panel ui-widget ui-widget-content ui-corner-all ui-g-12']//button"));
    }


}