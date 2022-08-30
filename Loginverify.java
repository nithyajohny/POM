package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.projectspecmeth;
import pages.LoginPage;

public class Loginverify extends projectspecmeth {
	@BeforeTest
	public void setup()
	{
		excelFileName="Login";
		testname="Loginverify";
		testdescription="Login test for leaftaps application";
		testauthor="hari";
		testcategory="smoke";
	}


	@Test(dataProvider = "fetch")
	public void loginverify(String username,String password) {
		// TODO Auto-generated method stub
		LoginPage lp=new LoginPage();
		lp.typeusername(username)
		.typepassword(password)
		.clicklogin();
	}

}
