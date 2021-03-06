package br.com.koin;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public void abreBrowser() {
		try {
			System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}
		catch(IllegalStateException e){
			JOptionPane.showMessageDialog(null, "Ops!\nParece que voc� n�o tem o chromedriver em sua m�quina!"
					+ "\nPara utilizar esta aplica��o, baixe a vers�o mais recente do chromedriver no link abaixo:"
					+ "\n<html><span style ='color:blue'>https://sites.google.com/a/chromium.org/chromedriver/downloads</span></html>"
					+ "\nClique em OK e o link ser� copiado para sua �rea de transfer�ncia, ap�s isso, "
					+ "apenas cole (Ctrl+V) na p�gina do seu navegador."
					+ "\nAp�s baixar o arquivo compactado, extraia o arquivo chromedriver.exe no diret�rio C:", "ATEN��O", JOptionPane.WARNING_MESSAGE);
			String link = "https://sites.google.com/a/chromium.org/chromedriver/downloads";
			StringSelection selectlLink = new StringSelection(link);
			Clipboard areaTransfer = Toolkit.getDefaultToolkit().getSystemClipboard();
			areaTransfer.setContents(selectlLink, null);
			System.exit(0);
		}
	}
	
	public void consultaRelatorio() {
		try{
			driver.get("https://www.dimepkairos.com.br/");
			WebElement usuario = driver.findElement(By.id("LogOnModel_UserName"));
			usuario.sendKeys(PageVO.getUser());
			WebElement senha = driver.findElement(By.id("LogOnModel_Password"));
			senha.sendKeys(PageVO.getPass());
			senha.submit();
			
			boolean loginOK = driver.getPageSource().contains("Contatos e info");
			
			if (loginOK == true) {
				PageVO.setLoginOK(loginOK);
				WebElement ponto = driver.findElement(By.id("UserProfilePonto"));
				ponto.click();
				WebElement relatorios = driver.findElement(By.id("Tab8"));
				relatorios.click();
				wait  = new WebDriverWait(driver, 120);
				
				WebElement botaoAtend = wait.until(ExpectedConditions.elementToBeClickable(By.id("conpassButtonClose")));
				botaoAtend.click();
				
				Select periodo = new Select(driver.findElement(By.id("select_periodos")));
				periodo.selectByValue("134");
				
				WebElement checkPessoas = driver.findElement(By.xpath("//*[@id=\"FilterBar\"]/div[1]/label"));
				checkPessoas.click();
				WebElement gerarEspelho = driver.findElement(By.id("bt_gerar_espelho"));
				gerarEspelho.click();
				String saldoBH = driver.findElement(By.xpath("//*[@id=\"Grid\"]/tbody/tr/td[8]")).getText();
				saldoBH = saldoBH.trim();
				PageVO.setSaldoBH(saldoBH);
				boolean consultaOK = driver.getPageSource().contains("Saldo atual");
				PageVO.setConsultaOK(consultaOK);
			}
			else{
				driver.quit();
				JOptionPane.showMessageDialog(null, "O login n�o p�de ser realizado corretamente!"
						+ "\nVerifique se o usu�rio/senha informado(a) est� correto(a).", "ATEN��O", JOptionPane.WARNING_MESSAGE);
			}
		}
		catch(NoSuchElementException e) {
			driver.quit();
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Elemento n�o encontrado na p�gina!", "ERRO", JOptionPane.ERROR_MESSAGE);
		} 
		catch(NoSuchWindowException | NullPointerException e){
			driver.quit();
			JOptionPane.showMessageDialog(null, "Browser fechado.", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		catch(TimeoutException e) {
			driver.quit();
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Timeout na espera de elemento.", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		catch(WebDriverException e) {
			driver.quit();
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro de WebDriver!", "ERRO", JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	public void apresentaSaldoBH() {
		boolean consultaOK = PageVO.getConsultaOK();
		if(consultaOK == true) {
			String saldoBH = PageVO.getSaldoBH();
			
			if(saldoBH.contains("-")) {
				JOptionPane.showMessageDialog(null, "<html>Saldo do banco de horas: " + "<span style ='color:red'>" + saldoBH + "</span></html>", "Consulta Saldo BH", JOptionPane.ERROR_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "<html>Saldo do banco de horas: " + "<span style ='color:blue'>" + saldoBH + "</span></html>", "Consulta Saldo BH", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
	public void resetaVariaveisEstaticas() {
		//Volta ao estado inicial
		PageVO.setLoginOK(false);
		PageVO.setConsultaOK(false);
	}
	
	public void fechaBrowser() {
		driver.quit();
	}
}
