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
import org.sga.teste.funcional.pages.MenuPage;
import org.sga.teste.funcional.pages.TriagemAlternativaPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestePaginaTriagemAlternativa extends BaseTest {

    private DSL dsl;
    private TriagemAlternativaPage triagemAlternativaPage;
    private MenuPage menuPage;

    @Before
    public void inicializar() {
        menuPage = new MenuPage();
        dsl = new DSL();

        triagemAlternativaPage = new TriagemAlternativaPage();
        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("Triagem Alternativa");
        //clicar no botao da Nova Triagem Alternativa
        triagemAlternativaPage.botaoNovo();

        BaseTest.nomePasta = "TestePaginaTriagemAlternativa";
    }

    @Test
    public void teste01DeveVerificarAInsercaoDaTriagemAlternativa() throws InterruptedException {

        triagemAlternativaPage.setNomeTriagemAlternativa("TESTE");
        triagemAlternativaPage.setSelecionarTipoServico("TESTE");
        triagemAlternativaPage.setSelecionarLocaldoServico("TESTE");
        triagemAlternativaPage.setSelecionarStatusAtivo();
        triagemAlternativaPage.botaoSalvar();

        Assert.assertEquals("TRIAGEM ALTERNATIVA INSERIDO COM SUCESSO!!!", triagemAlternativaPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste02DeveVerificarCampoVaziodaTriagemAlternativa() {

        triagemAlternativaPage.botaoSalvar();

        Assert.assertTrue(Arrays.asList("INFORME A SIGLA ALTERNATIVA"
                , "INFORME O SERVIÇO PARA A TRIAGEM ALTERNATIVA"
                , "INFORME O LOCAL PARA A TRIAGEM ALTERNATIVA").containsAll(triagemAlternativaPage.identificarErros()));

    }

    @Test
    public void teste03DeveVerificarCampoDeDigitosMenoreMaiordaTriagemAlternativa() throws InterruptedException {


        //---Valores menores -- Tecnica de Particao de Equivalencia
        dsl.limparCampo("siglatriagemalternativa");

        //--utilizando tecnica de particao de equivalencia, mas sem inserir o valores
        ///----A SIGLA ALTERNATIVA SÓ DEVE TER 2 E 10 DE DÍGITOS
        ///---- 2 e 10 -> 1
        triagemAlternativaPage.setNomeTriagemAlternativa("t");
        triagemAlternativaPage.setSelecionarTipoServico("TESTE");
        triagemAlternativaPage.setSelecionarLocaldoServico("TESTE");
        triagemAlternativaPage.setSelecionarStatusAtivo();
        triagemAlternativaPage.botaoSalvar();


        Assert.assertTrue(Arrays.asList("A SIGLA ALTERNATIVA SÓ DEVE TER 2 E 10 DE DÍGITOS"
                        , "INFORME O SERVIÇO PARA A TRIAGEM ALTERNATIVA"
                        , "INFORME O LOCAL PARA A TRIAGEM ALTERNATIVA")
                .containsAll(triagemAlternativaPage.identificarErros()));

        Thread.sleep(3000);

        ///----A SIGLA ALTERNATIVA SÓ DEVE TER 2 E 10 DE DÍGITOS
        ///---- 2 e 10 -> 12
        triagemAlternativaPage.setNomeTriagemAlternativa("testetestes");
        triagemAlternativaPage.setSelecionarTipoServico("TESTE");
        triagemAlternativaPage.setSelecionarLocaldoServico("TESTE");
        triagemAlternativaPage.setSelecionarStatusAtivo();

        triagemAlternativaPage.botaoSalvar();


        Assert.assertTrue(Arrays.asList("A SIGLA ALTERNATIVA SÓ DEVE TER 2 E 10 DE DÍGITOS"
                        , "INFORME O SERVIÇO PARA A TRIAGEM ALTERNATIVA"
                        , "INFORME O LOCAL PARA A TRIAGEM ALTERNATIVA")
                .containsAll(triagemAlternativaPage.identificarErros()));

    }

    @Test
    public void teste04DeveValidarSeExisteaTriagemAlternativa() {
        dsl.limparCampo("siglatriagemalternativa");
        triagemAlternativaPage.setNomeTriagemAlternativa("TESTE");
        triagemAlternativaPage.botaoSalvar();
        Assert.assertEquals("A SIGLA ALTERNATIVA JÁ EXISTE", triagemAlternativaPage.mensagemDeErroCampoNaoPreenchidoComSucesso());
    }

    @Test
    public void teste05DeveVerificarSeExisteaTriagemAlternativa() {

        //selecionar link de Serviço
        menuPage.selecionarMenuPageEsquerdo("Triagem Alternativa");

        //Informa nome do Teste
        triagemAlternativaPage.setNomeBuscar("Teste");
        //Clicar para buscar o campo
        triagemAlternativaPage.botaoBuscar();

        Assert.assertEquals("CONSULTADO COM SUCESSO!!!", triagemAlternativaPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste06DeveVerificarQueNaoExisteOServico() {

        //---Processo que nao necessita procurar os dados por  colunas e linhas na tabela.

        //selecionar link de Serviço
        menuPage.selecionarMenuPageEsquerdo("Triagem Alternativa");

        //Informa nome do Teste
        triagemAlternativaPage.setNomeBuscar("Quaisquer nome");
        //Clicar para buscar o campo
        triagemAlternativaPage.botaoBuscar();


        Assert.assertEquals("Nenhum Registro Encontrado.", triagemAlternativaPage.mensagemDeNaoExisteElementoNaTabelaLocal());

    }


    /**
     * --Processo que forca a procura atraves do trabalho nas linhas, colunas e paginacao
     */
    @Test
    public void teste07DeveVerificarAAtualizacaoDaTriagemAlternativa() {

        menuPage.selecionarMenuPageEsquerdo("Triagem Alternativa");

        //--Elemento tipo input para Sigla encontrasse com espacos na devida edição
        dsl.clicarBotaoTabelaEditareDeletarePaginar("consultar:overviewTableUserTriagemAlternativa", "Sigla", "TESTE", "Opções", "Editar");

        getComunicacaoDriverChrome().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        dsl.limparCampo("siglatriagemalternativa");

        triagemAlternativaPage.setNomeTriagemAlternativa("testando13");
        triagemAlternativaPage.setSelecionarTipoServico("TESTE");
        triagemAlternativaPage.setSelecionarLocaldoServico("GUICHÊ 1 - RECEPÇÃO RAIO X");

        triagemAlternativaPage.botaoSalvar();

        Assert.assertEquals("TRIAGEM ALTERNATIVA ALTERADO COM SUCESSO!!!", triagemAlternativaPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste08DeveVerificarAExcluirOServico() throws InterruptedException {
        //--Processo que forca a procura atraves do trabalho nas linhas, colunas e paginacao

        //selecionar link de Serviço
        menuPage.selecionarMenuPageEsquerdo("Triagem Alternativa");

        //informar qual e o tipo de servico para Editar
        dsl.clicarBotaoTabelaEditareDeletarePaginar("consultar:overviewTableUserTriagemAlternativa", "Sigla", "TESTANDO13", "Opções", "Excluir");

        Thread.sleep(3000);

        List<WebElement> webElementList = getComunicacaoDriverChrome().findElements(By.xpath("//tbody//tr"));

        Assert.assertEquals(10, webElementList.size());
    }

    @Test
    public void teste09DeveVerificarAInsercaoDoServico() {

        dsl.limparCampo("siglatriagemalternativa");

        triagemAlternativaPage.setNomeTriagemAlternativa("TESTE");
        triagemAlternativaPage.setSelecionarTipoServico("TESTE");
        triagemAlternativaPage.setSelecionarLocaldoServico("TESTE");
        triagemAlternativaPage.setSelecionarStatusAtivo();


        triagemAlternativaPage.botaoSalvar();

        Assert.assertEquals("TRIAGEM ALTERNATIVA INSERIDO COM SUCESSO!!!", triagemAlternativaPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }


}
