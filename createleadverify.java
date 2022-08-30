package testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.projectspecmeth;
import pages.LoginPage;

public class createleadverify extends projectspecmeth{
	@BeforeTest
	public void setup()
	{
		excelFileName="CreateLead";
		testname="creatleadeverify";
		testdescription="createlead for leaftaps application";
		testauthor="babu";
		testcategory="sanity";
	}
	
	@Test(dataProvider="fetch")
	public void verifycreatelead(String username,String password,String fname,String lname,String cname) throws InterruptedException {
		// TODO Auto-generated method stub
          LoginPage lp=new LoginPage();
          lp.typeusername(username)
          .typepassword(password)
          .clicklogin()
          .clickcrm_sfalink()
          .clickLeadlink()
          .clickcreateleadslink()
          .typefirstname(fname)
          .typelastname(lname)
          .typecompanyname(cname)
          .clickcreateleadbutton()
          .VerifyfirstName(fname);
       
	}

}
