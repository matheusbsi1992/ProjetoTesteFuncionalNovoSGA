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
import org.sga.teste.funcional.pages.CargoPage;
import org.sga.teste.funcional.pages.MenuPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestePaginaCargo extends BaseTest {

    private DSL dsl;
    private CargoPage cargoPage;
    private MenuPage menuPage;

    @Before
    public void inicializar() {
        menuPage = new MenuPage();
        dsl = new DSL();

        cargoPage = new CargoPage();

        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("Cargo");

        //clicar no botao da Nova Triagem Alternativa
        cargoPage.botaoNovo();

        BaseTest.nomePasta = "TestePaginaCargo";

    }

    @Test
    public void teste01DeveVerificarCampoVaziodoCargo() {

        cargoPage.botaoSalvar();
        cargoPage.botaoGeral();

        Assert.assertTrue(Arrays.asList("INFORME O NOME DO CARGO"
                , "INFORME DESCRIÇÃO DO CARGO"
        ).containsAll(cargoPage.identificarErros()));

    }

    @Test
    public void teste02DeveVerificarCampoVaziodoCargo() {
        cargoPage.botaoSalvar();
        cargoPage.botaoPermissoes();

        Assert.assertTrue(Arrays.asList("INFORME PERMISSÃO"
        ).containsAll(cargoPage.identificarErros()));

    }

    @Test
    public void teste03DeveVerificarAInsercaoDoCargo() {

        cargoPage.botaoGeral();
        cargoPage.setNomeCargo("Testando");
        cargoPage.setDescricaoCargo("Cargo de Testando a Aplicação com Selenium WebDriver");
        cargoPage.setSelecionarStatusAtivo();

        cargoPage.botaoPermissoes();
        cargoPage.retornarListadeValoresPermissao();

        cargoPage.botaoSalvar();

        Assert.assertEquals("CARGO INSERIDO COM SUCESSO!!!", cargoPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste04DeveValidarSeExisteOCargo() {
        dsl.limparCampo("formulariocargo:tabview:nomecargo");
        cargoPage.setNomeCargo("TESTANDO");
        cargoPage.botaoSalvar();
        Assert.assertEquals("O CARGO JÁ EXISTE", cargoPage.mensagemDeErroCampoNaoPreenchidoComSucesso());
    }

    @Test
    public void teste05DeveVerificarCampoDeDigitosMaiordoCargo() throws InterruptedException {

        //---Valores menores -- Tecnica de Particao de Equivalencia
        dsl.limparCampo("formulariocargo:tabview:nomecargo");

        //--utilizando tecnica de particao de equivalencia, mas sem inserir o valores
        ///----A SIGLA ALTERNATIVA SÓ DEVE TER 4 E 30 DE DÍGITOS
        ///---- 4 e 30 -> 44
        cargoPage.setNomeCargo("* Particionamento em classes de equivalência");
        ///---- 4 e 30 -> 44
        ///----A SIGLA ALTERNATIVA SÓ DEVE TER 4 E 30 DE DÍGITOS
        ///---- 4 e 30 -> 250
        cargoPage.setDescricaoCargo("* Particionamento em classes de equivalência : e.g., informado a descrição com a que pode ser validada em seguida.  Podendo ser dividido em partes o nome do cargo representado por classe e\n" +
                "determinar se um dado é válido, além da descrição do serviço.");
        ///---- 4 e 30 -> 250
        cargoPage.setSelecionarStatusAtivo();
        cargoPage.botaoSalvar();

        Assert.assertTrue(Arrays.asList("O NOME DO CARGO SÓ DEVE TER 4 E 30 DE DÍGITOS"
                , "A DESCRIÇÃO DO CARGO SÓ DEVE TER 4 E 200 DE DÍGITOS"
        ).containsAll(cargoPage.identificarErros()));

    }

    @Test
    public void teste06DeveVerificarSeExisteOCargo() {

        //selecionar link de Serviço
        menuPage.selecionarMenuPageEsquerdo("Cargo");

        //Informa nome do Teste
        cargoPage.setNomeBuscar("TESTANDO");
        //Clicar para buscar o campo
        cargoPage.botaoBuscar();

        Assert.assertEquals("CONSULTADO COM SUCESSO!!!", cargoPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste07DeveVerificarQueNaoExisteOCargo() {

        //---Processo que nao necessita procurar os dados por  colunas e linhas na tabela.

        //selecionar link de Serviço
        menuPage.selecionarMenuPageEsquerdo("Cargo");

        //Informa nome do Teste
        cargoPage.setNomeBuscar("Quaisquer nome");
        //Clicar para buscar o campo
        cargoPage.botaoBuscar();

        Assert.assertEquals("Nenhum Registro Encontrado.", cargoPage.mensagemDeNaoExisteElementoNaTabelaCargo());

    }

    @Test
    public void teste08DeveVerificarAAtualizacaoDoCargo() {
        //---Processo que necessita procurar os dados por  colunas e linhas na tabela.

        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("Cargo");

        //Informa Quaisquer nome
        cargoPage.setNomeBuscar("TESTANDO");

        //Clicar para buscar o campo
        cargoPage.botaoBuscar();

        Assert.assertEquals("CONSULTADO COM SUCESSO!!!", cargoPage.mensagemDeExitoCampoPreenchidoComSucesso());

        dsl.clicarBotaoTabelaEditar("consultar:overviewTableUserCargo:", "Permissão", "TESTANDO", "Opções", ":j_idt187");

        getComunicacaoDriverChrome().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        //limpar campo
        dsl.limparCampo("formulariocargo:tabview:nomecargo");
        cargoPage.setNomeCargo("TESTANDO13");
        cargoPage.botaoSalvar();

        Assert.assertEquals("CARGO ALTERADO COM SUCESSO!!!", cargoPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste09DeveExcluirOCargo() throws InterruptedException {

        //---Processo que necessita procurar os dados por  colunas e linhas na tabela.

        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("Cargo");

        //Informa Quaisquer nome
        cargoPage.setNomeBuscar("TESTANDO13");
        //Clicar para buscar o campo
        cargoPage.botaoBuscar();
        //identificar tabela, coluna principal com o seu respectivo valor e a opcao, apos remover o item correspondente
        dsl.clicarBotaoTabelaDeletar("consultar:overviewTableUserCargo:", "Permissão", "TESTANDO13", "Opções", ":j_idt187");

        Thread.sleep(3000);

        List<WebElement> webElementList = getComunicacaoDriverChrome().findElements(By.xpath("//tbody//tr"));

        Assert.assertEquals(12, webElementList.size());

    }

    @Test
    public void teste10DeveVerificarAInsercaoDoCargo() {

        cargoPage.botaoGeral();
        cargoPage.setNomeCargo("Testando");
        cargoPage.setDescricaoCargo("Cargo de Testando a Aplicação com Selenium WebDriver");
        cargoPage.setSelecionarStatusAtivo();

        cargoPage.botaoPermissoes();
        cargoPage.retornarListadeValoresPermissao();

        cargoPage.botaoSalvar();

        Assert.assertEquals("CARGO INSERIDO COM SUCESSO!!!", cargoPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

}