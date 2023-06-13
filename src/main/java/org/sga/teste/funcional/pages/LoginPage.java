package org.sga.teste.funcional.pages;


import org.openqa.selenium.By;
import org.sga.teste.funcional.core.DSL;

public class LoginPage {


    private DSL dsl;

    public LoginPage() {
        dsl = new DSL();
    }

    public void setUsuario(String usuario) {
        dsl.escreve("formlogin:usuario", usuario);
    }

    public void setSenha(String senha) {
        dsl.escreve("formlogin:senha", senha);
    }

    public void botaoDeLogin() {
        //dsl.clicarBotao(By.xpath("//span[.='Entrar']"));
        dsl.clicarBotao(By.cssSelector("span.ui-button-text"));
    }


}
