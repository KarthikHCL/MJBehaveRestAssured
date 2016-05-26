package com.MavenJBehave.steps;

import static com.jayway.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Pending;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;

public class MySteps {
    
	
    @Given("^I have cukes in my belly$")
    public void I_have_cukes_in_my_belly() throws Throwable {
    	//expect().statusCode(200).when().get("http://www.thomas-bayer.com/sqlrest/INVOICE/2/");
    	//Validating multiple values from a xml responses
    	get("http://www.thomas-bayer.com/sqlrest/INVOICE/1/").then().
    	assertThat().body("INVOICE.ID.text()", equalTo("1")).
    	body("INVOICE.CUSTOMERID.text()", equalTo("33")).
    	body("INVOICE.TOTAL.text()", equalTo("1610.70"));
    	
    }
    
    @Given("^I try access without credentials$")
    public void I_try_access_without_credentials() throws Throwable {
    	//expect().statusCode(200).when().get("http://www.thomas-bayer.com/sqlrest/INVOICE/2/");
    	//Validating a single value from a Thingspace Json
    	String thisMessage = get("https://thingspace.verizon.com/api/m2m/v1/callbacks/Authorization:89ba225e1438e95bd05c3cc288d3591").
    	then().contentType("application/json; charset=UTF-8").extract().path("fault.message");
    	assertTrue(thisMessage.equals("No matching resource found in the API for the given request"));
    } 
    
    
    @Given("^I want to check the Weather$")
    public void I_want_to_check_the_Weather() throws Throwable {
    	//expect().statusCode(200).when().get("http://www.thomas-bayer.com/sqlrest/INVOICE/2/");
    	//Validating a single value from a Thingspace Json
    	
    	System.out.println(get("http://www.webservicex.com/globalweather.asmx?WSDL").getStatusCode());
    	Response thisMessage = get("http://www.webservicex.com/globalweather.asmx/GetCitiesByCountry?CountryName=india");
    	//get("http://www.webservicex.com/globalweather.asmx/GetCitiesByCountry?CountryName=india").then().assertThat().body("NewDataSet.Table.City.gettext()", equalTo("Tiruchchirapalliiii"));
    	List<String> cityList = new ArrayList<String>();
    	String citylist1 = get("http://www.webservicex.com/globalweather.asmx/GetCitiesByCountry?CountryName=india").then().extract().path("NewDataSet.Table.City");
    	XmlPath xmPath= new XmlPath(thisMessage.toString()).setRoot("NewDataSet"); 
    	List<String> mlist=xmPath.getList("City");
    	System.out.println(mlist.size());
    	//System.out.println("---------------"+citylist1+"************");
    			//.
    	//then().contentType("application/json; charset=UTF-8").extract().path("fault.message");
    	//assertTrue(thisMessage.equals("No matching resource found in the API for the given request"));
    } 
    
    

	
	
}
