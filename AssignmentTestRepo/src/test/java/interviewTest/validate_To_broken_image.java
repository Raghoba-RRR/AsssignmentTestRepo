package interviewTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class validate_To_broken_image {

	WebDriver driver;
	
	@Test
	public void Find_broken_image()
	{
		WebDriverManager.chromedriver().setup();
		driver =new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("http://the-internet.herokuapp.com/broken_images");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		List<WebElement> images = driver.findElements(By.tagName("img"));
		System.out.println("The no.of image " +images.size());
		
		
		for(WebElement image :images)
			
		{
			String imageSrc =image.getAttribute("src");	
			
			try {
				URL url = new URL(imageSrc);
				URLConnection urlConnection =url.openConnection();
				HttpURLConnection httpurlconnection =(HttpURLConnection) urlConnection;
				httpurlconnection.setConnectTimeout(5000);
				
				
				if(httpurlconnection.getResponseCode()==200)
System.out.println(imageSrc+ " >> "+httpurlconnection.getResponseCode()+ " >> " +httpurlconnection.getResponseMessage());	

				else
					System.err.println(imageSrc+ " >> "+httpurlconnection.getResponseCode()+ " >> " +httpurlconnection.getResponseMessage());

				
			
			} catch (IOException e) {
				
				System.out.println(imageSrc);
			}
		
		}
		
		driver.quit();
		

		}
		
	}
	

