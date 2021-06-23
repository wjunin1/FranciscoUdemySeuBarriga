package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.SeuBarrigaLogin;
import pages.SeuBarrigaMenu;
import utils.DSL;
import utils.Screenshot;

import static org.junit.Assert.assertEquals;
import static utils.DriverFactory.getDriver;
import static utils.DriverFactory.killDriver;
import static utils.Variaveis.*;

public class seuBarrigaTests extends Screenshot {

    DSL dsl;
    private SeuBarrigaLogin login;
    public String nomeMetodo = Thread.currentThread().getStackTrace()[1].getMethodName();


    @BeforeClass
    public void setUp() throws InterruptedException {
        getDriver();
        dsl = new DSL();
        login = new SeuBarrigaLogin();
        login.logar();

    }

    //}
    @AfterClass
    public void Close() {
        killDriver();
    }

    @Test
    public void a_contaJaCadastrada() throws InterruptedException {
        test = extentReports.startTest("a_contaJaCadastrada");
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[5]/a");
        dsl.clickXpath("//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[2]/a");
        Thread.sleep(200);
        dsl.escreveId("nome", "Wellington Junior");
        dsl.escreveId("email", usuarioSeuBarriga);
        dsl.escreveId("senha", senhaSeuBarriga);
        dsl.clickXpath("/html/body/div[2]/form/input");
        assertEquals("Endereço de email já utilizado", dsl.validarTextoXpath("/html/body/div[1]"));
    }

    @Test
    public void b_logar() throws InterruptedException {
        test = extentReports.startTest(nomeMetodo);
        dsl.url("https://seubarriga.wcaquino.me/login");
        dsl.escreveId("email", usuarioSeuBarriga);
        dsl.escreveId("senha", senhaSeuBarriga);
        dsl.clickXpath("/html/body/div[2]/form/button"); // entrar
        Thread.sleep(2000);
        assertEquals("Bem vindo, wellington junior!", getDriver().findElement(By.xpath("/html/body/div[1]")).getText());
    }

    @Test
    public void c_criarConta() throws InterruptedException {
        test = extentReports.startTest(nomeMetodo);
        //dropdown
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/a"); //Menu Contas
        Thread.sleep(500);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/ul/li[1]/a");//Submenu adicionar
        dsl.escreveId("nome", nomeContaBarriga);
        getDriver().findElement(By.xpath("/html/body/div[2]/form/div[2]/button")).click();


        assertEquals("Já existe uma conta com esse nome!", dsl.validarTextoXpath("/html/body/div[1]"));
    }

    @Test
    public void d_validarConta() throws InterruptedException {
        test = extentReports.startTest(nomeMetodo);
        SeuBarrigaMenu.menuContasAdicionar();
        dsl.escreveId("nome", nomeContaBarriga);
        getDriver().findElement(By.xpath("/html/body/div[2]/form/div[2]/button")).click();
        assertEquals("Já existe uma conta com esse nome!", dsl.validarTextoXpath("/html/body/div[1]"));
        SeuBarrigaMenu.menuHome();
    }

    @Test
    public void e_listarContas() throws InterruptedException {
        test = extentReports.startTest(nomeMetodo);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/a"); //Menu Contas
        Thread.sleep(500);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/ul/li[2]/a");//Submenu Listar

        assertEquals("Conta itau", dsl.validarTextoXpath("//*[@id=\"tabelaContas\"]/tbody/tr/td[1]")); //validar conta = Conta bradesco
    }

    @Test
    public void f_alterarContas() throws InterruptedException {
        test = extentReports.startTest(nomeMetodo);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/a"); //Menu Contas
        Thread.sleep(500);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/ul/li[2]/a");//Submenu Listar

        dsl.clickXpath("//*[@id=\"tabelaContas\"]/tbody/tr/td[2]/a[1]/span");
        assertEquals("Conta itau", dsl.pegarAtributoValue("nome", "value"));
        dsl.clickXpath("/html/body/div[2]/form/div[2]/button");
        assertEquals("Conta alterada com sucesso!", dsl.validarTextoXpath("/html/body/div[1]")); //validar conta alterada
    }

    @Test
    public void g_excluirConta() throws InterruptedException {
        test = extentReports.startTest(nomeMetodo);
        // criar conta
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/a"); //Menu Contas
        Thread.sleep(500);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/ul/li[1]/a");//Submenu adicionar
        dsl.escreveId("nome", contaTemporaria);
        getDriver().findElement(By.xpath("/html/body/div[2]/form/div[2]/button")).click();
        assertEquals("Já existe uma conta com esse nome!", dsl.validarTextoXpath("/html/body/div[1]"));
        Thread.sleep(500);
        // apagar conta
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/a"); //Menu Contas
        Thread.sleep(500);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[2]/ul/li[2]/a");//Submenu Listar
        dsl.clickXpath("//*[@id=\"tabelaContas\"]/tbody/tr[2]/td[2]/a[2]/span");
        assertEquals("Conta em uso na movimentações", dsl.validarTextoXpath("/html/body/div[1]"));
    }

    @Test
    public void h_adicionarMovimentacaoReceitaPendente() {
        test = extentReports.startTest(nomeMetodo);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[3]/a"); // menu criar movimentacao

        //Seleciona o tipo de movimentacao
        WebElement elementoTipoMovimentacao = getDriver().findElement(By.id("tipo"));
        Select comboReceita = new Select(elementoTipoMovimentacao);
        comboReceita.selectByVisibleText("Receita");

        dsl.escreveId("data_transacao", "20/04/2021");
        dsl.escreveId("data_pagamento", "20/04/2021");
        dsl.escreveId("descricao", "Consultoria");
        dsl.escreveId("interessado", "RM");
        dsl.escreveId("valor", "100");

        //Seleciona a conta
        WebElement conta = getDriver().findElement(By.id("tipo"));
        Select comboConta = new Select(conta);
        comboConta.selectByIndex(1);

        //Situacao
        dsl.clickId("status_pendente");
        dsl.clickXpath("/html/body/div[2]/form/div[4]/button");
        assertEquals("Movimentação adicionada com sucesso!", dsl.validarTextoXpath("/html/body/div[1]"));
    }

    @Test
    public void i_adicionarMovimentacaoReceitaPago() {
        test = extentReports.startTest(nomeMetodo);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[3]/a"); // menu criar movimentacao

        //Seleciona o tipo de movimentacao
        WebElement elementoTipoMovimentacao = getDriver().findElement(By.id("tipo"));
        Select comboReceita = new Select(elementoTipoMovimentacao);
        comboReceita.selectByVisibleText("Receita");

        dsl.escreveId("data_transacao", "20/04/2021");
        dsl.escreveId("data_pagamento", "20/04/2021");
        dsl.escreveId("descricao", "Salario");
        dsl.escreveId("interessado", "TTY");
        dsl.escreveId("valor", "3000");

        //Seleciona a conta
        WebElement conta = getDriver().findElement(By.id("tipo"));
        Select comboConta = new Select(conta);
        comboConta.selectByIndex(1);

        //Situacao
        dsl.clickId("status_pago");
        dsl.clickXpath("/html/body/div[2]/form/div[4]/button");

        assertEquals("Movimentação adicionada com sucesso!", dsl.validarTextoXpath("/html/body/div[1]"));

    }

    @Test
    public void j_adicionarMovimentacaoDespesaPendente() {
        test = extentReports.startTest(nomeMetodo);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[3]/a"); // menu criar movimentacao

        //Seleciona o tipo de movimentacao
        WebElement elementoTipoMovimentacao = getDriver().findElement(By.id("tipo"));
        Select comboReceita = new Select(elementoTipoMovimentacao);
        comboReceita.selectByVisibleText("Despesa");

        dsl.escreveId("data_transacao", "20/04/2021");
        dsl.escreveId("data_pagamento", "20/04/2021");
        dsl.escreveId("descricao", "Pagamento de contas");
        dsl.escreveId("interessado", "CEB");
        dsl.escreveId("valor", "135");

        //Seleciona a conta
        WebElement conta = getDriver().findElement(By.id("tipo"));
        Select comboConta = new Select(conta);
        comboConta.selectByIndex(1);

        //Situacao
        dsl.clickId("status_pendente");
        dsl.clickXpath("/html/body/div[2]/form/div[4]/button");
        assertEquals("Movimentação adicionada com sucesso!", dsl.validarTextoXpath("/html/body/div[1]"));

    }

    @Test
    public void h_adicionarMovimentacaoDespesaPago() {
        test = extentReports.startTest(nomeMetodo);
        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[3]/a"); // menu criar movimentacao

        //Seleciona o tipo de movimentacao
        WebElement elementoTipoMovimentacao = getDriver().findElement(By.id("tipo"));
        Select comboReceita = new Select(elementoTipoMovimentacao);
        comboReceita.selectByVisibleText("Despesa");

        dsl.escreveId("data_transacao", "20/04/2021");
        dsl.escreveId("data_pagamento", "20/04/2021");
        dsl.escreveId("descricao", "Pagamento de aluguel");
        dsl.escreveId("interessado", "Falkao");
        dsl.escreveId("valor", "1300");

        //Seleciona a conta
        WebElement conta = getDriver().findElement(By.id("tipo"));
        Select comboConta = new Select(conta);
        comboConta.selectByIndex(1);

        //Situacao
        dsl.clickId("status_pago");
        dsl.clickXpath("/html/body/div[2]/form/div[4]/button");

        assertEquals("Movimentação adicionada com sucesso!", dsl.validarTextoXpath("/html/body/div[1]"));

    }

    @Test
    public void k_apagarContaResumo() {
        test = extentReports.startTest(nomeMetodo);

        dsl.clickXpath("//*[@id=\"navbar\"]/ul/li[4]/a");
        dsl.clickXpath("//*[@id=\"tabelaExtrato\"]/tbody/tr/td[6]/a/span");
        assertEquals("Movimentação removida com sucesso!", dsl.validarTextoXpath("/html/body/div[1]"));
    }


}
