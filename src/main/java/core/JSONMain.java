package core;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JSONMain {

	public static String place (String ipaddr) throws IOException{
		String pls = "";
		String pls1 = "";

			URL url = new URL("http://www.geoplugin.net/json.gp?ip=" + ipaddr + "");
			final String element_01 = "geoplugin_currencyCode";
			final String element_02 = "geoplugin_countryName";

			InputStream is = url.openStream();
			JsonParser parser = Json.createParser(is);

			while (parser.hasNext())	{
				Event e = parser.next();
				if (e == Event.KEY_NAME)	{
					switch (parser.getString())	{
					case element_01:
						parser.next();
						pls1 = parser.getString();
						System.out.print("(" + pls1 + ")");
						break;
					case element_02:
						parser.next();
						pls = parser.getString();
						break;
												}	//switch (parser.getString())
											}	//if (e == Event.KEY_NAME
										}	//while (parser.hasNext()
		return pls;
	}	//end of ipaddr


	public static String strtostr(String q){		//	the allocation of the number from string
	       String result = "";
	       String[] numbers = q.split("[^0-9.]");

   for (int i = 0; i < numbers.length; i++)
         { result += numbers[i];}
    return result;
    }												//	the allocation of the number from string


	public static String rate (String urrl) throws IOException {	//	whath the exchange currency?	begin
		String cost = "";
		URL url = new URL(urrl);
		final String element_01 = "Name";
		final String element_02 = "Rate";
		InputStream is = url.openStream();
		JsonParser parser = Json.createParser(is);
		while (parser.hasNext())	{
			Event e = parser.next();
			if (e == Event.KEY_NAME)	{
				switch (parser.getString())	{
				case element_01:
					parser.next();
					break;
				case element_02:
					parser.next();
					String country_02_code = parser.getString();  // USDEUR
					cost = country_02_code;
					break;
											}	//switch (parser.getString())
										}	//if (e == Event.KEY_NAME
									}	//while (parser.hasNext()

		return cost;
	}																//	whath the exchange currency?	end

//--------------------------------------------------------------------------------------------------------------
	public static void main(String[] args) throws Throwable {
	WebDriver driver = new FirefoxDriver();

	System.out.println("------------------------------------------------------------");
	String b = "88.191.179.56";
	String b2 = "61.135.248.220";
	String b3 = "92.40.254.196";	//ip addresses of different countries
	String b4 = "93.183.203.67";
	String b5 = "213.87.141.36";
	String url1 = "http://www.amazon.com/adidas-Brazuca-World-Official-Soccer/dp/B00GSUGEBK/ref=sr_1_2?s=sporting-goods&ie=UTF8&qid=1444588067&sr=1-2&keywords=Brazuca+Top+Glider+World+Cup+Replica+Soccer+Ball+%284%29";
	String url2 = "http://www.amazon.com/Withings-70031601-Pulse-Heart-Monitor/dp/B00KBN1F5A/ref=pd_sim_sbs_sg_6?ie=UTF8&refRID=0VQXF03E0KCG4S8JZKF4";
	String url3 = "http://www.amazon.com/Samsung-Galaxy-Unlocked-Android-Smartphone/dp/B00IUMNBGU/ref=sr_1_fkmr3_1?s=wireless&ie=UTF8&qid=1445317645&sr=1-1-fkmr3&keywords=Samsung+Galaxy+S+DUOS+GT-S7562+Unlocked+GSM+Phone+Dual+SIM%2C+4%26%2334%3B+Touchscreen%2C+5MP+Camera%2C+Video%2C+GPS%2C+Wi-Fi%2C+Bluetooth+No+Warranty+-+Black";
	String url4 = "http://www.amazon.com/Withings-Activite-Activity-Sleep-Tracker/dp/B00W1F6MF4/ref=pd_rhf_dp_s_cp_2?ie=UTF8&refRID=00P9AJV13TD473DADGYZ";
	String url5 = "http://www.amazon.com/Apple-iPhone-5s-Space-Unlocked/dp/B00F3J4B5S/ref=sr_1_2?s=electronics&ie=UTF8&qid=1404438992&sr=1-2&keywords=iphone+5s";

	//-----------------------------adidas-Brazuca-World-Official-Soccer
	driver.get(url1);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	String quote1 = driver.findElement(By.id("productTitle")).getText();
	try
	{

	String quote = driver.findElement(By.id("priceblock_ourprice")).getText();
	System.out.println("We interested in " + quote1);
	//-----------------------------USD/EUR
	String q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDEUR%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	double value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b));
	//-----------------------------	USD/CNY
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDCNY%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b2));
	//-----------------------------USD/GBP
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDGBP%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b3));
	//-----------------------------USD/UAH
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDUAH%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b4));
	//-----------------------------USDRUB
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDRUB%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b5));
	System.out.println("------------------------------------------------------------");
	} catch (Exception e) //try
	{
		System.out.println("The product "+ quote1 +" is not available now");
		System.out.println("------------------------------------------------------------");
					}	//catch

	//-----------------------------Withings Pulse O2 Activity, Sleep, and Heart Rate + SPO2 Tracker for iOS and Android
	driver.get(url2);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	quote1 = driver.findElement(By.id("productTitle")).getText();
	try
	{

	String quote = driver.findElement(By.id("priceblock_ourprice")).getText();
	System.out.println("We interested in " + quote1);
	//-----------------------------USD/EUR
	String q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDEUR%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	double value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b));
	//-----------------------------	USD/CNY
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDCNY%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b2));
	//-----------------------------USD/GBP
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDGBP%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b3));
	//-----------------------------USD/UAH
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDUAH%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b4));
	//-----------------------------USDRUB
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDRUB%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b5));
	System.out.println("------------------------------------------------------------");
	} catch (Exception e) //try
	{
		System.out.println("The product "+ quote1 +" is not available now");
		System.out.println("------------------------------------------------------------");
					}	//catch


	//-----------------------------Samsung Galaxy Star Pro DUOS S7262 Unlocked GSM Android 4.1 Smartphone - Black
	driver.get(url3);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	quote1 = driver.findElement(By.id("productTitle")).getText();
	try
	{

	String quote = driver.findElement(By.id("priceblock_ourprice")).getText();
	System.out.println("We interested in " + quote1);
	//-----------------------------USD/EUR
	String q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDEUR%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	double value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b));
	//-----------------------------	USD/CNY
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDCNY%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b2));
	//-----------------------------USD/GBP
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDGBP%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b3));
	//-----------------------------USD/UAH
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDUAH%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b4));
	//-----------------------------USDRUB
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDRUB%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b5));
	System.out.println("------------------------------------------------------------");
	} catch (Exception e) //try
	{
		System.out.println("The product "+ quote1 +" is not available now");
		System.out.println("------------------------------------------------------------");
					}	//catch


	//-----------------------------Withings-Activite-Activity-Sleep-Tracker
	driver.get(url4);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	quote1 = driver.findElement(By.id("productTitle")).getText();
	try
	{

	String quote = driver.findElement(By.id("priceblock_ourprice")).getText();
	System.out.println("We interested in " + quote1);
	//-----------------------------USD/EUR
	String q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDEUR%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	double value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b));
	//-----------------------------	USD/CNY
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDCNY%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b2));
	//-----------------------------USD/GBP
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDGBP%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b3));
	//-----------------------------USD/UAH
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDUAH%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b4));
	//-----------------------------USDRUB
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDRUB%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b5));
	System.out.println("------------------------------------------------------------");
	} catch (Exception e) //try
	{
		System.out.println("The product "+ quote1 +" is not available now");
		System.out.println("------------------------------------------------------------");
					}	//catch


	//-----------------------------Apple iPhone 5s Unlocked Cellphone, 16GB, Space Gray
	driver.get(url5);
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	quote1 = driver.findElement(By.id("productTitle")).getText();
	try
	{

	String quote = driver.findElement(By.id("priceblock_ourprice")).getText();
	System.out.println("We interested in " + quote1);
	//-----------------------------USD/EUR
	String q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDEUR%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	double value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b));
	//-----------------------------	USD/CNY
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDCNY%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b2));
	//-----------------------------USD/GBP
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDGBP%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b3));
	//-----------------------------USD/UAH
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDUAH%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b4));
	//-----------------------------USDRUB
	q ="http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.xchange%20where%20pair%20in%20%28%22USDRUB%22%29&format=json&env=store://datatables.org/alltableswithkeys";
	value = Double.parseDouble(rate(q))*Double.parseDouble(strtostr(quote));
	System.out.print("Price is ");
	System.out.format("%1.2f", value);
	System.out.println(" in national currency of " + place(b5));
	System.out.println("------------------------------------------------------------");
	} catch (Exception e) //try
	{
		System.out.println("The product "+ quote1 +" is not available now");
		System.out.println("------------------------------------------------------------");
					}	//catch

	driver.quit();
	}	//public static void main(String[] args) {
}