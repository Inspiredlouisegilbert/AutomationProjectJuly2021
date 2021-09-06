package tests;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import pageObjects.HomePage;
import pageObjects.SignIn;

public class MovieTestAPIandUI {
	
	HomePage hp = new HomePage();
	SignIn sn = new SignIn();
	
	@Test
	public void GIVEN_NavigationToSignIn_AND_OnlypasswordEntered_AND_clickSignInButtonClicked_THEN_Error() {
		
		//GIVEN
		sn.goHome();
		hp.clickSignIn();
		sn.enterPassword("sdfsdfdf");
		
		//WHEN
		sn.clickSignInButton();
		
		String actual = sn.checkError();
		String expected = "Email or Mobile number is required";
		
		//THEN
		Assert.assertEquals(actual, expected);
		
		
	}
	
	@Test
	public void GIVEN_NavigationToSignIn_AND_InvalidUserNameEntered_AND_clickSignInButtonClicked_THEN_Error() {
		
		//GIVEN
		sn.goHome();
		hp.clickSignIn();
		sn.enterUserName("5645656");
		
		//WHEN
		sn.clickSignInButton();
		
		String actual = sn.checkError();
		String expected = "Please enter a valid Email or Mobile Number";
		
		//THEN
		Assert.assertEquals(actual, expected);
	
	}
	
	@Test
	public void GIVEN_NavigationToSignIn_AND_ValidBUTIcorrectUserNameEntered_AND_clickSignInButtonClicked_THEN_Error() {
		
		//GIVEN
		sn.goHome();
		hp.clickSignIn();
		sn.enterPassword("sdfsdfdf");
		sn.enterUserName("ddddd@ddd.com");
		
		//WHEN
		sn.clickSignInButton();
		
		String actual = sn.checkSignInError();
		String expected = "Your details are incorrect, please try again";
		
		//THEN
		Assert.assertEquals(actual, expected);
	}
	
	@Test(dataProvider="getData")
	public void TestsUsingDataProvider(String userName, String Password) {
		
		//GIVEN
		sn.goHome();
		hp.clickSignIn();
		sn.enterPassword(userName);
		sn.enterUserName(Password);
		
		//WHEN
		sn.clickSignInButton();
		
		String actual = sn.checkError();
		System.out.println(actual);
		String expected = "Please enter a valid Email or Mobile Number";
	
		//THEN
		Assert.assertEquals(actual, expected);
		
	}
	
    //@Test
    public void getRequest() {
    	//The HTTP GET request is used to fetch a resource from a server.
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/posts")
                .then()
                .extract().response();

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.jsonPath().getString("title[1]"),"qui est esse");
        //given().when().get(systemUnderTestAcme).then().assertThat().statusCode(200);
    }
    
    @Test
    public void getRequestReal() {
    	//The HTTP GET request is used to fetch a resource from a server.
        Response response = 
        given()
        .queryParam("redirect_uri","//connect.dstv.com/4.1/")//.log().all()
        .when().get("/registration/signin").then()
        .log().all().extract().response();   

        Assert.assertEquals(response.statusCode(),200);
    }
    
	
	@DataProvider
	public Object[][] getData()
	{
		//define an multi dimensional object array with 3 rows and 2 columns
		//3 rows for the number of test cases
		//2 columns for the number of values passed in
		Object[][] data = new Object[3][2];
		
		//1st set
		data[0][0]="firstsetusername";
		data[0][1]="firstsetusername";
		
		//2nd set
		data[1][0]="secondsetusername";
		data[1][1]="secondsetusername";
		
		//3rd set
		data[2][0]="thirdsetusername";
		data[2][1]="thirdsetusername";
		
		return data;

	}
	
    @BeforeClass
    public void setup() {
    	RestAssured.baseURI = "https://connect.dstv.com";
    }
    
	@AfterSuite
	public void cleanup() {
		sn.cleanUp();
	}
}
