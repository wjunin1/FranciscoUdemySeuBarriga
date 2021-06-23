package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.DriverFactory.getDriver;

public class DSL {

    //Metodos publicos

    public void url(String campo) {
        getDriver().get(campo);
    }

    //id = id registrado no elemento
    //name = nome registrado no elemento
    //xpath = caminho montado para chegar ao elemento
    //linkText = texto que contem dentro do link do elemento
    //tagName = retorna o conteudo dentro da tag
    //ClassName = retorna pelo nome da class

    /**************** Escrever ********************/
    public void escreveId(String campo, String texto) {
        getDriver().findElement(By.id(campo)).sendKeys(texto);
    }

    public void escreveNome(String campo, String texto) {
        getDriver().findElement(By.name(campo)).sendKeys(texto);
    }

    public void escreveXpath(String campo, String texto) {
        getDriver().findElement(By.xpath(campo)).sendKeys(texto);
    }

    public void escreveLinkText(String campo, String texto) {
        getDriver().findElement(By.linkText(campo)).sendKeys(texto);
    }

    /**************** Clicar ********************/

    public void clickName(String campo) {
        getDriver().findElement(By.name(campo)).click();
    }

    public void clickclass(String campo) {
        getDriver().findElement(By.className(campo)).click();
    }

    public void clickXpath(String campo) {
        getDriver().findElement(By.xpath(campo)).click();
    }

    public void clickId(String campo) {
        getDriver().findElement(By.id(campo)).click();
    }

    /**************** Validar campo ********************/

    public void validaTitulo(String campo) {
        assertEquals(getDriver().getTitle(), campo);
    }

    public String validarTextoXpath(String campo) {
        return getDriver().findElement(By.xpath(campo)).getText();
    }

    public String validaTextoId(String campo) {
        return getDriver().findElement(By.id(campo)).getText();
    }

    /**************** Pegar atributo ********************/

    public String pegarAtributoValue(String idcampo, String campo) {
        return getDriver().findElement(By.id(idcampo)).getAttribute(campo);
    }

    /**************** Pegar texto ********************/

    public void buscarTextoNaPagina(String tagCampo, String containTexto) {
        //retorna os valores dentro da tag e valida se contem o texto informado. Pode ser usado dentro de qualquer tagname
        //quando é uma tag que repete várias vezes, ele retorna a primeira.. Altera o findelements pra resolver
        System.out.println(getDriver().findElement(By.tagName(tagCampo)).getText());
        assertTrue(getDriver().findElement(By.tagName(tagCampo)).getText().contains(containTexto));
    }

    /**************** link ********************/

    public void interagirLinks(String link) {
        //quando é um campo href de link, busca pelo texto dele na tela
        getDriver().findElement(By.linkText(link)).click();
    }

    /**************** Combobox ********************/

    public void selecionaValorComboBoxVisibleText(String elementoCampo, String textoVisivel) {
        //pegar o elemento do comboBox pelo texto visivel
        WebElement element = getDriver().findElement(By.id(elementoCampo));
        //incluir ele no select
        Select combo = new Select(element);
        //buscar o combo pelas opcoes, abaixo como texto visivel
        combo.selectByVisibleText(textoVisivel);
    }

    public void selecionaValorComboBoxIndex(String elementoCampo, int indexCampo) {
        //pegar o elemento do comboBox pelo index(número na ordenação do combo)
        WebElement element = getDriver().findElement(By.id(elementoCampo));
        //incluir ele no select
        Select combo = new Select(element);
        //Index = ordem dos valores dentro do box. Ex: 1 Receita - 2 Despesa.. as vezes o index começa no 0
        combo.selectByIndex(indexCampo);
    }

    public void selecionaValorComboBoxValue(String elementoCampo, String valueCampo) {
        //pegar o elemento do comboBox pelo valor
        WebElement element = getDriver().findElement(By.id(elementoCampo));
        //incluir ele no select
        Select combo = new Select(element);
        //Value = valor informado no inspecionar
        combo.selectByValue(valueCampo);
    }

    public void validarTamanhoDoCombo(String elementoCampo) {
        // pega a lista de elementos e faz a comparação da quantidade de opcoes
        WebElement element = getDriver().findElement(By.id(elementoCampo));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        //passa a quantidade esperada e compara com o size
        assertEquals(2, options.size());
    }

    public void selecionaMultiplaEscolhaComboBox(String elementoCampo) {
        // para ser usado em combo de multipla escolha
        WebElement element = getDriver().findElement(By.id(elementoCampo));
        Select combo = new Select(element);
        //seleciona pelo texto visivel a quantidade que for querida
        combo.selectByVisibleText("texto 1");
        combo.selectByVisibleText("texto 2");
        combo.selectByVisibleText("texto 3");
        //usado para deselecionar o item do combo
        combo.deselectByVisibleText("texto 1");
    }

}
