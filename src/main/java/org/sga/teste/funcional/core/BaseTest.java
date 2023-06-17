package org.sga.teste.funcional.core;


import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.sga.teste.funcional.pages.LoginPage;

import java.io.File;
import java.io.IOException;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;
import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getEncerrarNavegadorSessao;

public class BaseTest {
    public static String nomePasta = "";
    private static LoginPage loginPage = new LoginPage();
    @Rule
    public TestName name = new TestName();
    private static DSL dsl = new DSL();

    @BeforeClass
    public static void acessoValidoParaLogin() {
        getComunicacaoDriverChrome().get("http://192.168.2.20/atendimento/index.xhtml");

        loginPage.setUsuario("teste");
        loginPage.setSenha("12345678");

        loginPage.botaoDeLogin();

    }

    @AfterClass
    public static void encerrarComunicacao() {
        //--PROCESSO PARA FAZER O LOGOUT--INICIA
        dsl.clicarBotao(By.xpath("//span[@class='hidden-xs']"));
        dsl.clicarBotao(By.xpath("//span[@class='ui-button-icon-left ui-icon ui-c fa fa-sign-out']"));
        dsl.clicarBotao(By.xpath("//span[.='Logout']//../../div//span[.='Sim']"));
        //--PROCESSO PARA FAZER O LOGOUT--ENCERRADO
        //--PROCESSO INICIA PARA FINALIZAR O NAVEGADOR
        if (Propriedades.FINALIZA) {
            getEncerrarNavegadorSessao();
        }

    }

    @After
    public void gerarEvidencias() throws IOException {

        TakesScreenshot screenshot = (TakesScreenshot) getComunicacaoDriverChrome();
        File arquivo = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo, new File(this.nomePasta + File.separator + "screenshot" + File.separator + name.getMethodName() + ".jpg"));

    }

    public String getNomePasta() {
        return this.nomePasta;
    }

    public void setNomePasta(String nomePasta) {
        this.nomePasta = nomePasta;
    }


}
