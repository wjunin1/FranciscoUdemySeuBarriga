package pages;

import utils.DSL;

import static utils.Variaveis.*;

public class SeuBarrigaLogin {


    DSL dsl = new DSL();

    public void logar() throws InterruptedException {
        dsl.url("https://seubarriga.wcaquino.me/login");
        dsl.escreveId("email", usuarioSeuBarriga);
        dsl.escreveId("senha", senhaSeuBarriga);
        dsl.clickXpath("/html/body/div[2]/form/button"); // entrar
        Thread.sleep(1000);
    }
}
