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
import org.sga.teste.funcional.pages.UsuarioPage;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestePaginaUsuario extends BaseTest {

    private DSL dsl;
    private UsuarioPage usuarioPage;
    private MenuPage menuPage;

    @Before
    public void inicializar() {
        menuPage = new MenuPage();
        dsl = new DSL();

        usuarioPage = new UsuarioPage();

        //selecionar link de Usuário
        menuPage.selecionarMenuPageEsquerdo("Usuário");

        //clicar no botao de Novo Usuário
        usuarioPage.botaoNovo();

        BaseTest.nomePasta = "TestePaginaUsuario";

    }

    @Test
    public void teste01DeveVerificarCampoVaziodoUsuario() {

        usuarioPage.botaoSalvar();
        usuarioPage.botaoGeral();

        Assert.assertTrue(Arrays.asList("INFORME USUÁRIO"
                , "INFORME O NOME DO USUÁRIO"
                ,"INFORME SENHA"
                ,"INFORME A UNIDADE"
                ,"INFORME O CARGO"
        ).containsAll(usuarioPage.identificarErros()));

    }
    @Test
    public void teste02DeveVerificarCampoVaziodoUsuarip() {
        usuarioPage.botaoSalvar();
        usuarioPage.botaoServicos();

        Assert.assertTrue(Arrays.asList("INFORME SERVIÇO"
        ).containsAll(usuarioPage.identificarErros()));

    }
    @Test
    public void teste03DeveVerificarAInsercaoDoUsuario() {

        usuarioPage.botaoGeral();
        usuarioPage.setUsuario("Testando");
        usuarioPage.setNomeUsuario("Testando");
        usuarioPage.setPassword("12345678");
        usuarioPage.setConfirmacaoPassword("12345678");
        usuarioPage.setSelecionarUnidade();
        usuarioPage.setSelecionarCargo();
        usuarioPage.setSelecionarStatusAtivo();

        usuarioPage.botaoServicos();
        usuarioPage.retornarListadeValoresServico();

        usuarioPage.botaoSalvar();

        Assert.assertEquals("USUÁRIO INSERIDO COM SUCESSO!!!", usuarioPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste04DeveValidarSeExisteOUsuario() {
        dsl.limparCampo("formulariousuario:tabview:usuario");
        usuarioPage.setUsuario("TESTANDO");
        usuarioPage.botaoSalvar();
        Assert.assertEquals("USUÁRIO EXISTE", usuarioPage.mensagemDeErroCampoNaoPreenchidoComSucesso());
    }

    @Test
    public void teste05DeveVerificarCampoDeDigitosMaiordoUsuario() throws InterruptedException {

        //---Valores menores -- Tecnica de Particao de Equivalencia
        dsl.limparCampo("formulariousuario:tabview:usuario");
        dsl.limparCampo("formulariousuario:tabview:sobrenomeusuario");

        //--utilizando tecnica de particao de equivalencia, mas sem inserir o valores
        ///---- 5 e 200 -> 225
        usuarioPage.setUsuario("* Particionamento em classes de equivalência|* Particiona* Particionamento em classes de equivalência|mento em classes de equivalência|* Particionamento em classes de equivalência|* Particionamento em classes de equivalência|");
        ///---- 5 e 200 -> 225
        usuarioPage.setNomeUsuario("* Particionamento em classes de equivalência|* Particiona* Particionamento em classes de equivalência|mento em classes de equivalência|* Particionamento em classes de equivalência|* Particionamento em classes de equivalência|");
        ///---- 5 e 200 -> 225
        usuarioPage.botaoSalvar();

        Assert.assertTrue(Arrays.asList("O USUÁRIO SÓ DEVE TER 5 E 200 DE DÍGITOS"
                , "O NOME DO USUÁRIO SÓ DEVE TER 5 E 200 DE DÍGITOS"
        ).containsAll(usuarioPage.identificarErros()));

    }
    @Test
    public void teste06DeveVerificarSeExisteOUsuario() {

        //selecionar link de Usuario
        menuPage.selecionarMenuPageEsquerdo("Usuário");

        //Informa nome do Testando
        usuarioPage.setNomeBuscar("TESTANDO");
        //Clicar para buscar o campo
        usuarioPage.botaoBuscar();

        Assert.assertEquals("CONSULTADO COM SUCESSO!!!", usuarioPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }
    @Test
    public void teste07DeveVerificarQueNaoExisteOUsuario() {

        //---Processo que nao necessita procurar os dados por  colunas e linhas na tabela.

        //selecionar link de Usuario
        menuPage.selecionarMenuPageEsquerdo("Usuário");

        //Informa nome do Teste
        usuarioPage.setNomeBuscar("Quaisquer nome");
        //Clicar para buscar o campo
        usuarioPage.botaoBuscar();

        Assert.assertEquals("Nenhum Registro Encontrado.", usuarioPage.mensagemDeNaoExisteElementoNaTabelaUsuario());

    }

    @Test
    public void teste08DeveVerificarAAtualizacaoDoUsuario() {
        //---Processo que necessita procurar os dados por  colunas e linhas na tabela.

        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("Usuário");

        //Informa Quaisquer nome
        usuarioPage.setNomeBuscar("TESTANDO");

        //Clicar para buscar o campo
        usuarioPage.botaoBuscar();

        Assert.assertEquals("CONSULTADO COM SUCESSO!!!", usuarioPage.mensagemDeExitoCampoPreenchidoComSucesso());

        dsl.clicarBotaoTabelaEditar("consultar:overviewTableUserUsuario:", "Usuário, Cargo e seus Serviços", "TESTANDO", "Opções", ":j_idt187");

        getComunicacaoDriverChrome().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        dsl.limparCampo("formulariousuario:tabview:usuario");
        dsl.limparCampo("formulariousuario:tabview:sobrenomeusuario");

        usuarioPage.botaoSalvar();

        Assert.assertEquals("USUÁRIO ALTERADO COM SUCESSO!!!", usuarioPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

    @Test
    public void teste09DeveExcluirOUsuario() throws InterruptedException {

        //---Processo que necessita procurar os dados por  colunas e linhas na tabela.

        //selecionar link de Local
        menuPage.selecionarMenuPageEsquerdo("usuário");

        //Informa Quaisquer nome
        usuarioPage.setNomeBuscar("TESTANDO13");
        //Clicar para buscar o campo
        usuarioPage.botaoBuscar();
        //identificar tabela, coluna principal com o seu respectivo valor e a opcao, apos remover o item correspondente
        dsl.clicarBotaoTabelaDeletar("consultar:overviewTableUserUsuario:", "Usuário, Cargo e seus Serviços", "TESTANDO13", "Opções", ":j_idt187");

        Thread.sleep(3000);

        List<WebElement> webElementList = getComunicacaoDriverChrome().findElements(By.xpath("//tbody//tr"));

        Assert.assertEquals(12, webElementList.size());

    }

    @Test
    public void teste10DeveVerificarAInsercaoDoUsuario() {

        usuarioPage.botaoGeral();
        usuarioPage.setUsuario("Testando");
        usuarioPage.setNomeUsuario("Testando");
        usuarioPage.setPassword("12345678");
        usuarioPage.setConfirmacaoPassword("12345678");
        usuarioPage.setSelecionarUnidade();
        usuarioPage.setSelecionarCargo();
        usuarioPage.setSelecionarStatusAtivo();

        usuarioPage.botaoServicos();
        usuarioPage.retornarListadeValoresServico();

        usuarioPage.botaoSalvar();

        Assert.assertEquals("USUÁRIO INSERIDO COM SUCESSO!!!", usuarioPage.mensagemDeExitoCampoPreenchidoComSucesso());

    }

}