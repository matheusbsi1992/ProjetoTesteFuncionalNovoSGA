package org.sga.teste.funcional.core;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.sga.teste.funcional.util.Sincronismo;

import java.util.ArrayList;
import java.util.List;

import static org.sga.teste.funcional.comunicacao.AcessoComunicacao.getComunicacaoDriverChrome;

public class DSL {


    public DSL() {

    }


    public void escreve(String idCampo, String valorCampo) {
        escreve(By.id(idCampo), valorCampo);
    }

    public void escreve(By by, String valorCampo) {
        getComunicacaoDriverChrome()
                .findElement(by)
                .sendKeys(valorCampo);
    }

    public void limparCampo(String idCampo) {
        getComunicacaoDriverChrome().findElement(By.id(idCampo))
                .clear();
    }

    public void limparCampo(By by) {
        getComunicacaoDriverChrome().findElement(by)
                .clear();
    }

    public String obterValordoCampo(String idCampo) {
        String retornoValor = getComunicacaoDriverChrome()
                .findElement(By.id(idCampo))
                .getAttribute("value");
        return retornoValor;
    }

    public String obterValordoCampo(By by) {
        String retornoValor = getComunicacaoDriverChrome()
                .findElement(by)
                .getAttribute("value");
        return retornoValor;
    }

    public void clicarRadioButton(String idCampo) {
        clicarBotao(idCampo);
    }

    public void isRetornarCorretoIncorretoRadioButton(By by) {
        List<WebElement> nova = getComunicacaoDriverChrome()
                .findElements(by);
        boolean outraAcerto = false;
        outraAcerto = nova.get(0).isSelected();
        if (outraAcerto == true) {
            nova.get(0).click();
        } else {
            nova.get(1).click();
        }
    }

    public boolean isRetornarCorretoIncorretoRadioButton(String idCampo) {
        return getComunicacaoDriverChrome()
                .findElement(By.id(idCampo))
                .isSelected();
    }

    public void selecionarComboPrime(String radical, String valor) {
        clicarBotao(By.xpath("//*[@id='" + radical + ":option_input']/../..//span"));
//        getComunicacaoDriverChrome()Wait wait = new getComunicacaoDriverChrome()Wait(getComunicacaoDriverChrome(), Duration.ofMillis(10000));
//        //wait.until(ExpectedConditions.alertIsPresent());

        clicarBotao(By.xpath("//*[@id='" + radical + ":option_items']//li[.='" + valor + "']"));
    }

    public Select selecionarCombo(By by, String valorCampo) {
        WebElement webElement = getComunicacaoDriverChrome()
                .findElement(by);
        Select select = new Select(webElement);
        //select.selectByIndex(1);
        //select.selectByVisibleText(valorCampo);
        select.selectByValue(valorCampo);
        return select;
    }

    public Select selecionarListarCombo(By by, String valorCampo) {

        List<WebElement> webElement = getComunicacaoDriverChrome().findElements(By.xpath("//*[@id='servico_items']//li"));

        for (WebElement webElemento : webElement) {
            Select select = new Select(webElemento);
            if (webElemento.getText().equalsIgnoreCase(valorCampo)) {
                select.selectByVisibleText(valorCampo);
                break;
            }
            return select;
            //select.selectByIndex(1);
        }
        //select.selectByValue(valorCampo);
        return null;
    }

    public Select selecionarCombo(String xPath, String valorCampo) {
        WebElement webElement = getComunicacaoDriverChrome()
                .findElement(By.xpath(xPath));
        Select select = new Select(webElement);

        //select.selectByVisibleText(valorCampo);
        select.selectByIndex(2);
        //select.selectByValue(valorCampo);
        return select;
    }

    public boolean isRetornarCombo(String idCampo, String valorCampo) {
        return selecionarCombo(idCampo, valorCampo)
                .getOptions()
                .stream()
                .anyMatch(webElement -> webElement.getText().equalsIgnoreCase(valorCampo));
    }

    public void clicarBotao(By by) {
        getComunicacaoDriverChrome().findElement(by).click();
    }

    public void clicarBotao(String idCampo) {
        clicarBotao(By.id(idCampo));
    }

    public void clicarLink(String idCampo) {
        getComunicacaoDriverChrome().findElement(By.linkText(idCampo)).click();
    }

    public void clicarLink(By by) {
        getComunicacaoDriverChrome().findElement(by).click();
    }

    public String obterTexto(By by) {
        String nomeTexto = getComunicacaoDriverChrome().findElement(by).getText();
        return nomeTexto;
    }

    public String obterTexto(String idCampo) {
        return obterTexto(By.id(idCampo));
    }

    /**
     * Alertas
     */
    public String alertarObterTexto() {
        Alert alert = getComunicacaoDriverChrome().switchTo().alert();

        String nomeCampo = alert.getText();
        return nomeCampo;
    }

    public String alertarAceitarComTexto() {
        //getComunicacaoDriverChrome()Wait wait = new getComunicacaoDriverChrome()Wait(getComunicacaoDriverChrome(), Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.alertIsPresent());
        String nomeCampo = null;
        Alert alert = getComunicacaoDriverChrome().switchTo().alert();
        nomeCampo = alert.getText();
        alert.accept();
        return nomeCampo;
    }

    public String alertarNegarComTexto() {
        Alert alert = getComunicacaoDriverChrome().switchTo().alert();
        String nomeCampo = alert.getText();
        alert.dismiss();
        return nomeCampo;
    }

    public void alertarEscrever(String valorCampo) {
        Alert alert = getComunicacaoDriverChrome().switchTo().alert();
        alert.sendKeys(valorCampo);
        alert.accept();
    }

    /**
     * Frames e Janelas
     */
    public void entrarJanela(String idCampo) {
        getComunicacaoDriverChrome().switchTo().frame(idCampo);
    }

    public void sairFrame() {
        getComunicacaoDriverChrome().switchTo().defaultContent();
    }

    public void trocarJanela(String idCampo) {
        getComunicacaoDriverChrome().switchTo().window(idCampo);
    }

    //****** JS ********//
    public Object executarJS(String cmd, Object... param) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getComunicacaoDriverChrome();
        return javascriptExecutor.executeScript(cmd, param);
    }

    //*********** Tabela **********//
    public void clicarBotaoTabelaDeletar(String idTabela, String colunaTabela, String valor, String colunaBotao, String radical) {

        new Sincronismo().sincronismoExplicito(By.xpath("//table//thead//tr"));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //---procurar coluna do registro
        WebElement tabela = getComunicacaoDriverChrome().findElement(By.xpath("//table//thead//tr"));

        int idColuna = obterIndiceColuna(colunaTabela, tabela);

        System.out.println(tabela.getText() + " " + idColuna);

        //---encontrar a linha do registro
        int idLinha = obterIndiceLinha(valor, tabela, idTabela, idColuna);
        //---procurar coluna do botao
        int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
        //---clicar no botao da celula encontrada
        WebElement celula;

        if (idColunaBotao == -1) {
            celula = tabela.findElement(By.xpath("//tr//td//button[.='Excluir']"));
            celula.click();
        } else {
            celula = tabela.findElement(By.xpath("//tr[" + idLinha + "]//td[" + idColunaBotao + "]//button[.='Excluir']"));
            celula.click();
        }
        //*[@id='consultar:overviewTableUserLocal:4:j_idt181']
        clicarBotao(By.xpath("//*[@id='" + idTabela + "" + (idLinha - 1) + "" + radical + "']"));

        //deleta item correspondente

        //celula.findElement(By.xpath("//span[.='Excluir']")).click();
        //celula.findElement(By.id("consultar:overviewTableUserLocal:"+idLinha+":idexcluir")).click();
        //celula.findElement(By.cssSelector("span.glyphicon.glyphicon-remove-circle")).click();
    }

    public void clicarBotaoTabelaEditar(String idTabela, String colunaTabela, String valor, String colunaBotao, String radical) {

        //---procurar coluna do registro
        WebElement tabela = getComunicacaoDriverChrome().findElement(By.xpath("//table//thead//tr"));
        int idColuna = obterIndiceColuna(colunaTabela, tabela);

        System.out.println(tabela.getText() + " " + idColuna);

        //---encontrar a linha do registro
        int idLinha = obterIndiceLinha(valor, tabela, idTabela, idColuna);
        //---procurar coluna do botao
        int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);
        //---clicar no botao da celula encontrada
        WebElement celula;
        if (idColunaBotao == -1) {
            celula = tabela.findElement(By.xpath("//tr//td//button[.='Editar']"));
            celula.click();
        } else {
            celula = tabela.findElement(By.xpath("//tr[" + idLinha + "]//td[" + idColunaBotao + "]//button[.='Editar']"));
            celula.click();
        }


        //---//*[@id='consultar:overviewTableUserLocal:4:j_idt181']
        clicarBotao(By.xpath("//*[@id='" + idTabela + "" + (idLinha - 1) + "" + radical + "']"));


        //deleta item correspondente

        //celula.findElement(By.xpath("//span[.='Excluir']")).click();
        //celula.findElement(By.id("consultar:overviewTableUserLocal:"+idLinha+":idexcluir")).click();
        //celula.findElement(By.cssSelector("span.glyphicon.glyphicon-remove-circle")).click();

    }

    public void clicarBotaoTabelaEditareDeletarePaginar(String idTabela, String colunaTabela, String valor, String colunaBotao, String editar) {

        //desce a barra de rolagem
        WebElement webDriverElement = getComunicacaoDriverChrome().findElement(By.id(idTabela));
        executarJS("window.scrollBy(0,arguments[0])", webDriverElement.getLocation().y);

        //---procurar coluna do registro
        WebElement tabela = getComunicacaoDriverChrome().findElement(By.xpath("//table/thead/tr"));
        int idColuna = obterIndiceColuna(colunaTabela, tabela);

        Sincronismo sincronismo = new Sincronismo();
        sincronismo.sincronismoExplicito(By.xpath("//table/thead/tr"));

        System.out.println(tabela.getText() + " " + idColuna);

        //---encontrar a linha do registro
        int idLinha = obterIndiceLinha(valor, tabela, idTabela, idColuna);

        while (idLinha == -1) {
            // clicarBotao("//span[@class='ui-paginator-pages']//a");
            idLinha = obterIndiceLinha(valor, tabela, idTabela, idColuna);
        }
        //---procurar coluna do botao
        int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

        WebElement celula = null;
        if (editar.equalsIgnoreCase("Editar")) {
            //---clicar no botao da celula encontrada
            celula = tabela.findElement(By.xpath("//tr[" + idLinha + "]//td[" + idColunaBotao + "]//button[.='Editar']"));
            celula.click();

            //---//*[@id='consultar:overviewTableUserLocal:4:j_idt181']
            //clicarBotao(By.xpath("//*[@id='"+idTabela+""+(idLinha - 1)+""+radical+"']"));
            clicarBotao(By.xpath("//span[.='Edição']//..//..//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-left btn btn-primary ui-confirmdialog-yes']"));
        } else {
            celula = tabela.findElement(By.xpath("//tr[" + idLinha + "]//td[" + idColunaBotao + "]//button[.='Excluir']"));
            celula.click();

            clicarBotao(By.xpath("//span[.='Exclusão']//..//..//button[@class='ui-button ui-widget ui-state-default ui-corner-all ui-button-text-icon-left btn btn-primary ui-confirmdialog-yes']"));
        }
        //deleta item correspondente

        //celula.findElement(By.xpath("//span[.='Excluir']")).click();
        //celula.findElement(By.id("consultar:overviewTableUserLocal:"+idLinha+":idexcluir")).click();
        //celula.findElement(By.cssSelector("span.glyphicon.glyphicon-remove-circle")).click();

    }

    /**
     * public String reconhecerValorTabela(String idTabela, String colunaTabela, String valor, String colunaValor) {
     * //---procurar coluna do registro
     * WebElement tabela = getComunicacaoDriverChrome().findElement(By.xpath("//table[@id='" + idTabela + "']//thead//tr"));
     * int idColuna = obterIndiceColuna(colunaTabela, tabela);
     * <p>
     * System.out.println(tabela.getText() + " " + idColuna);
     * <p>
     * //---encontrar a linha do registro
     * int idLinha = obterIndiceLinha(valor, tabela, idTabela, idColuna);
     * //---procurar coluna do botao
     * int idColunaBotao = obterIndiceColuna(colunaValor, tabela);
     * //---clicar no botao da celula encontrada
     * WebElement celula = tabela.findElement(By.xpath("//*[@id='" + idTabela + "']//tr[" + idLinha + "]//td[" + idColunaBotao + "]"));
     * //celula.findElement(By.xpath(".//span")).click();
     * return celula.getText();
     * //celula.findElement(By.cssSelector("span.glyphicon.glyphicon-remove-circle")).click();
     * }
     */


    private int obterIndiceLinha(String valor, WebElement tabela, String idTabela, int idColuna) {

        List<WebElement> linhas = new ArrayList<WebElement>();
        //WebElement webElement  =tabela.findElement(By.xpath("//tr["+idLinhaEspecifica+"]//td["+ idColuna +"]"));

        linhas = getComunicacaoDriverChrome().findElements(By.xpath("//table//tbody/tr/td[" + idColuna + "]"));

        //System.out.println(webElement.getText());

        //List<WebElement> linhas=new ArrayList<WebElement>();
        //linhas.add(webElement);

        int idLinha = -1;

        List<WebElement> listaElementoPaginacao = getComunicacaoDriverChrome().findElements(By.xpath("//span[@class='ui-paginator-pages']//a")
                //By.cssSelector("a.ui-paginator-page ui-state-default ui-state-active ui-corner-all")
        );
        //---a.ui-paginator-page ui-state-default ui-state-active ui-corner-all

        for (int i = 0; i < linhas.size(); i++) {

            if (linhas.get(i).getText().equalsIgnoreCase(valor)) {
                idLinha = (i + 1);
                break;
            } else {
                if (idLinha == -1) {
                    for (WebElement elementPrincipal : listaElementoPaginacao) {

                        if (linhas.get(i).getText().equalsIgnoreCase(valor)) {
                            idLinha = (i + 1);
                            break;
                        } else {

                            elementPrincipal.click();
                        }
                    }
                    new Sincronismo().sincronismoExplicito(By.xpath("//table//tbody/tr/td[" + idColuna + "]"));
                    linhas = getComunicacaoDriverChrome().findElements(By.xpath("//table//tbody/tr/td[" + idColuna + "]"));
                }
            }
        }
        return idLinha;
    }

    private int obterIndiceColuna(String colunaTabela, WebElement tabela) {
        //new Sincronismo().sincronismoExplicito(By.xpath("//table/thead/tr//th"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> colunas =getComunicacaoDriverChrome().findElements(By.xpath("//table/thead/tr//th"));
        // = tabela.findElements(By.xpath(".//th"));
        int idColuna = -1;

        for (int i = 0; i < colunas.size(); i++) {
            if (colunas.get(i).getText().equalsIgnoreCase(colunaTabela)) {
                idColuna = (i + 1);
                break;
            }
        }
        return idColuna;
    }

}