package regrasdenegocios;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.sga.teste.funcional.core.BaseTest;
import org.sga.teste.funcional.core.DSL;
import org.sga.teste.funcional.pages.LocalPage;
import org.sga.teste.funcional.pages.MenuPage;

import java.time.Duration;
import java.util.List;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestePaginaLocal extends BaseTest {

    private DSL dsl;
    private LocalPage localPage;
    private MenuPage menuPage;


    @Before
    public void inicializar() {
        menuPage = new MenuPage();
        dsl = new DSL();
        localPage = new LocalPage();

        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("Local");
        //clicar no botao de Novo Local
        localPage.botaoNovo();
        BaseTest.nomePasta = "TestePaginaLocal";
    }

    @Test()
    public void teste01DeveVerificarAInsercaoDoLocal() {

        //Informa nome do Teste
        localPage.setNomeLocal("Teste");

        localPage.botaoSalvar();

        //getComunicacaoDriverChrome().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        Assert.assertEquals("LOCAL INSERIDO COM SUCESSO!!!", localPage.mensagemDeExitoCampoPreenchidoComSucesso());

        //getComunicacaoDriverChrome().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

    }

    @Test()
    public void teste02DeveVerificarCampoVaziodoLocal() {

        //Informa nome do Teste
        localPage.setNomeLocal("");

        localPage.botaoSalvar();

        Assert.assertEquals("INFORME NOME DO LOCAL", localPage.mensagemDeErroCampoNaoPreenchidoComSucesso());

    }

    @Test
    public void teste03DeveVerificarCampoDeDigitosMenoreMaiordoLocal() {

        //limpar campo
        dsl.limparCampo("nomelocal");

        //Informa nome do Teste
        localPage.setNomeLocal("tes");

        localPage.botaoSalvar();

        Assert.assertEquals("O NOME DO LOCAL SÓ DEVE TER 4 E 30 DE DÍGITOS", localPage.mensagemDeErroCampoNaoPreenchidoComSucesso());

        dsl.limparCampo("nomelocal");

        //Informa nome do Teste
        localPage.setNomeLocal("tesdddddddddddddddddddffffffffffffffffffffffffff");

        localPage.botaoSalvar();

        Assert.assertEquals("O NOME DO LOCAL SÓ DEVE TER 4 E 30 DE DÍGITOS", localPage.mensagemDeErroCampoNaoPreenchidoComSucesso());

    }

    @Test
    public void teste04DeveValidarSeExisteOLocal() {

        //limpar campo
        dsl.limparCampo("nomelocal");

        //Informa nome do Teste
        localPage.setNomeLocal("Teste");

        localPage.botaoSalvar();

        Assert.assertEquals("O LOCAL JÁ EXISTE", localPage.mensagemDeErroCampoNaoPreenchidoComSucesso());

    }

    @Test
    public void teste05DeveVerificarSeExisteOLocal() {

        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("Local");

        //Informa nome do Teste
        localPage.setNomeBuscar("Teste");
        //Clicar para buscar o campo
        localPage.botaoBuscar();

        Assert.assertEquals("CONSULTADO COM SUCESSO!!!", localPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste06DeveVerificarQueNaoExisteOLocal() {

        //---Processo que nao necessita procurar os dados por  colunas e linhas na tabela.

        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("Local");

        //Informa Quaisquer nome
        localPage.setNomeBuscar("Quaisquer nome");
        //Clicar para buscar o campo
        localPage.botaoBuscar();

        Assert.assertEquals("Nenhum Registro Encontrado.", localPage.mensagemDeNaoExisteElementoNaTabelaLocal());

    }

    @Test
    public void teste07DeveAtualizarOLocal() {
        //---Processo que necessita procurar os dados por  colunas e linhas na tabela.

        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("Local");

        //Informa Quaisquer nome
        localPage.setNomeBuscar("Teste");

        //Clicar para buscar o campo
        localPage.botaoBuscar();

        Assert.assertEquals("CONSULTADO COM SUCESSO!!!", localPage.mensagemDeExitoCampoPreenchidoComSucesso());

        dsl.clicarBotaoTabelaEditar("consultar:overviewTableUserLocal:", "Local", "TESTE", "Opções", ":j_idt181");

        getComunicacaoDriverChrome().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //limpar campo
        dsl.limparCampo("nomelocal");

        //Informa nome do Teste
        localPage.setNomeLocal("Teste Agora");

        localPage.botaoSalvar();

        Assert.assertEquals("LOCAL ALTERADO COM SUCESSO!!!", localPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste08DeveExcluirOLocal() throws InterruptedException {

        //---Processo que necessita procurar os dados por  colunas e linhas na tabela.

        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("Local");

        //Informa Quaisquer nome
        localPage.setNomeBuscar("Removido ---> TESTE AGORA");

        //identificar tabela, coluna principal com o seu respectivo valor e a opcao, apos remover o item correspondente
        dsl.clicarBotaoTabelaDeletar("consultar:overviewTableUserLocal:", "Local", "Teste Agora", "Opções", ":j_idt181");

        Thread.sleep(3000);

        List<WebElement> webElementList = getComunicacaoDriverChrome().findElements(By.xpath("//tbody//tr"));

        Assert.assertEquals(4, webElementList.size());

    }

    @Test
    public void teste09DeveVerificarAInsercaoDoLocal() {

        dsl.limparCampo(By.id("nomelocal"));
        //Informa nome do Teste
        localPage.setNomeLocal("Teste");

        localPage.botaoSalvar();

        //getComunicacaoDriverChrome().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        Assert.assertEquals("LOCAL INSERIDO COM SUCESSO!!!", localPage.mensagemDeExitoCampoPreenchidoComSucesso());

        //getComunicacaoDriverChrome().manage().timeouts().implicitlyWait(Duration.ofSeconds(0));

    }


}