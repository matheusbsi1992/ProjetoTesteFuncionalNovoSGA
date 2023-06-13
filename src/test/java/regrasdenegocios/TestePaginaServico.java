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
import org.sga.teste.funcional.pages.ServicoPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestePaginaServico extends BaseTest {

    private DSL dsl;
    private ServicoPage servicoPage;
    private MenuPage menuPage;

    @Before
    public void inicializar() {
        menuPage = new MenuPage();
        dsl = new DSL();
        servicoPage = new ServicoPage();
        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("Serviço");
        //clicar no botao de Novo Servico
        servicoPage.botaoNovo();
        BaseTest.nomePasta = "TestePaginaServico";
    }

    @Test
    public void teste01DeveVerificarAInsercaoDoServico() {

        //Informa nome do servico: teste
        servicoPage.setNomeServico("Teste");
        //Informa o nome da descricao
        servicoPage.setDescricaoServico("Teste em uso, com Selenium WebDriver");
        //Informar o status
        servicoPage.setSelecionarStatusAtivo();

        servicoPage.botaoSalvar();

        Assert.assertEquals("SERVIÇO INSERIDO COM SUCESSO!!!", servicoPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste02DeveVerificarCampoVaziodoServico() {

        servicoPage.setNomeServico("");
        servicoPage.setDescricaoServico("");

        servicoPage.botaoSalvar();


        Assert.assertEquals("INFORME O NOME DO SERVIÇO", servicoPage.identificarErros().get(0).toString());
        Assert.assertEquals("INFORME A DESCRIÇÃO DO SERVIÇO", servicoPage.identificarErros().get(1).toString());

    }

    @Test
    public void teste03DeveVerificarCampoDeDigitosMenoreMaiordoServico() {

        //---Valores menores -- Tecnica de Particao de Equivalencia
        dsl.limparCampo("nomeservico");
        dsl.limparCampo("descricaoservico");

        //--utilizando tecnica de particao de equivalencia
        //--3 e 30 -> 2
        servicoPage.setNomeServico("ON");
        //--5 e 200 -> 3
        servicoPage.setDescricaoServico("sfsa");

        servicoPage.botaoSalvar();

        Assert.assertEquals("O NOME DO SERVIÇO SÓ DEVE TER 3 E 30 DE DÍGITOS", servicoPage.identificarErros().get(0).toString());
        Assert.assertEquals("A DESCRIÇÃO DO SERVIÇO SÓ DEVE TER 5 E 200 DE DÍGITOS", servicoPage.identificarErros().get(1).toString());

        //---Valores maiores -- Tecnica de Particao de Equivalencia
        dsl.limparCampo("nomeservico");
        dsl.limparCampo("descricaoservico");

        //--utilizando tecnica de particao de equivalencia
        //--3 e 30 -> 31
        servicoPage.setNomeServico("tecnicadeparticaodeequivalencia".toUpperCase());
        //--5 e 200 -> 201
        servicoPage.setDescricaoServico("A DESCRIÇÃO DO SERVIÇO SÓ DEVE TER 5 E 200 DE DÍGITOS" +
                "A DESCRIÇÃO DO SERVIÇO SÓ DEVE TER 5 E 200 DE DÍGITOSA DESCRIÇÃO DO " +
                "SERVIÇO SÓ DEVE TER 5 E 200 DE DÍGITOSA DESCRIÇÃO DO SERVIÇO SÓ DEVE TER 5 E 200");

        servicoPage.botaoSalvar();

        Arrays.asList("O NOME DO SERVIÇO SÓ DEVE TER 3 E 30 DE DÍGITOS"
                        , "A DESCRIÇÃO DO SERVIÇO SÓ DEVE TER 5 E 200 DE DÍGITOS")
                .containsAll(servicoPage.identificarErros());

    }

    @Test
    public void teste04DeveValidarSeExisteOServico() {

        dsl.limparCampo("nomeservico");
        dsl.limparCampo("descricaoservico");

        servicoPage.setNomeServico("teste");
        servicoPage.setDescricaoServico("Descricao de teste realizado");

        servicoPage.botaoSalvar();

        Assert.assertEquals("O SERVIÇO JÁ EXISTE", servicoPage.identificarErros().get(0).toString());

    }

    @Test
    public void teste05DeveVerificarSeExisteOServico() {

        //selecionar link de Serviço
        menuPage.selecionarMenuPageEsquerdo("Serviço");

        //Informa nome do Teste
        servicoPage.setNomeServicoBuscar("Teste");
        //Clicar para buscar o campo
        servicoPage.botaoBuscar();

        Assert.assertEquals("CONSULTADO COM SUCESSO!!!", servicoPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste06DeveVerificarQueNaoExisteOServico() {

        //---Processo que nao necessita procurar os dados por  colunas e linhas na tabela.

        //selecionar link de Serviço
        menuPage.selecionarMenuPageEsquerdo("Serviço");

        //Informa Quaisquer nome
        servicoPage.setNomeServicoBuscar("Quaisquer nome");
        //Clicar para buscar o campo
        servicoPage.botaoBuscar();

        Assert.assertEquals("Nenhum Registro Encontrado.", servicoPage.mensagemDeNaoExisteElementoNaTabelaLocal());

    }

    @Test
    public void teste07DeveVerificarAAtualizacaoDoServico() {
        //--Processo que forca a procura atraves do trabalho nas linhas, colunas e paginacao

        //selecionar link de Serviço
        menuPage.selecionarMenuPageEsquerdo("Serviço");

        //informar qual e o tipo de servico para Editar
        dsl.clicarBotaoTabelaEditareDeletarePaginar("consultar:overviewTableUserServico", "Serviço", "TESTE", "Opções", "Editar");

        getComunicacaoDriverChrome().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //--limpar os campos
        dsl.limparCampo("nomeservico");
        dsl.limparCampo("descricaoservico");

        servicoPage.setNomeServico("TesteAgora");
        servicoPage.setDescricaoServico("Descricao de teste realizado");

        servicoPage.botaoSalvar();

        Assert.assertEquals("SERVIÇO ALTERADO COM SUCESSO!!!", servicoPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste08DeveVerificarAExcluirOServico() throws InterruptedException {
        //--Processo que forca a procura atraves do trabalho nas linhas, colunas e paginacao

        //selecionar link de Serviço
        menuPage.selecionarMenuPageEsquerdo("Serviço");

        //informar qual e o tipo de servico para Editar
        dsl.clicarBotaoTabelaEditareDeletarePaginar("consultar:overviewTableUserServico", "Serviço", "TesteAgora", "Opções", "Excluir");

        Thread.sleep(3000);

        List<WebElement> webElementList = getComunicacaoDriverChrome().findElements(By.xpath("//tbody//tr"));

        Assert.assertEquals(1, webElementList.size());
    }

    @Test
    public void teste09DeveVerificarAInsercaoDoServico() {

        //---Valores menores -- Tecnica de Particao de Equivalencia
        dsl.limparCampo("nomeservico");
        dsl.limparCampo("descricaoservico");

        //Informa nome do servico: teste
        servicoPage.setNomeServico("Teste");
        //Informa o nome da descricao
        servicoPage.setDescricaoServico("Teste em uso, com Selenium WebDriver");
        //Informar o status
        servicoPage.setSelecionarStatusAtivo();

        servicoPage.botaoSalvar();

        Assert.assertEquals("SERVIÇO INSERIDO COM SUCESSO!!!", servicoPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }


}
