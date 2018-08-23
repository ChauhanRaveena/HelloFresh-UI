package com.webelements;

import org.openqa.selenium.By;

public class WebElements {
	
	//Home Page Elements
	public By logoPresent = By.xpath("//*[@id=\"header_logo\"]/a/img");
	public By signIn = By.className("login");
	
	//Authentication Page Elements
	public By email = By.id("email_create");
	public By exisitingLoginEmail = By.id("email");
	public By existingLoginPassword = By.id("passwd");
	public By submitLogin = By.id("SubmitLogin");
	
	//Registration Page Elements
	public By radioButton = By.id("id_gender2");
	public By firstname = By.id("customer_firstname");
	public By lastname = By.id("customer_lastname");
	public By password = By.id("passwd");
	public By date = By.id("days");
	public By month = By.id("months");
	public By years = By.id("years");
	public By company = By.id("company");
	public By address1 = By.id("address1");
	public By address2 = By.id("address2");
	public By city = By.id("city");
	public By state = By.id("id_state");
	public By zip = By.id("postcode");
	public By additionalInfo = By.id("other");
	public By homePhone = By.id("phone");
	public By mobilePhone = By.id("phone_mobile");
	public By alias = By.id("alias");
	public By submit = By.id("submitAccount");
	
	//Post login Elements
	public By loginHeading = By.xpath("//*[text()='My account']");
	public By loginAccount = By.className("account");
	public By loginMessage = By.className("info-account");
	public By logout = By.className("logout");
	public By women = By.linkText("Women");
	public By tShirt = By.xpath("//a[@title='Faded Short Sleeve T-shirts']/ancestor::li");
	
	//Faded short sleeve t-shirt check out page
	public By AddToCart = By.name("Submit");
	
	//Summary page
	public By proceedToCheckout = By.xpath("//*[@id='layer_cart']//a[@class and @title='Proceed to checkout']");
	
	//Shopping Cart Summary Checkout Page
	public By shoppingCartSummaryCheckout = By.xpath("//*[contains(@class,'cart_navigation')]/a[@title='Proceed to checkout']");
	
	//Address Page Checkout Page
	public By addressCheckout = By.name("processAddress");
	
	//Shipping Page
	public By agreeTermsCheckbox = By.id("uniform-cgv");
	public By shippingCheckout = By.name("processCarrier");
	
	//Payments Page
	public By paymentTypeBankWire = By.className("bankwire");
	public By orderConfirmationButton = By.xpath("//*[@id='cart_navigation']/button");
	
	//Order Confirmation Page
	public By paymentVerification1 = By.xpath("//li[@class='step_done step_done_last four']");
	public By paymentVerification2 = By.xpath("//li[@id='step_end' and @class='step_current last']");
	public By orderConfirmationMessage = By.xpath("//*[@class='cheque-indent']/strong");
	public By orderPageHeader = By.cssSelector("h1");
}