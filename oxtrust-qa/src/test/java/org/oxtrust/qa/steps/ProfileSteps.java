package org.oxtrust.qa.steps;

import org.oxtrust.qa.pages.login.HomePage;
import org.oxtrust.qa.pages.profile.ProfilePage;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;

public class ProfileSteps extends BaseSteps {

	private HomePage homePage = new HomePage();

	private ProfilePage profilePage = new ProfilePage();

	@When("^I go to my profile page$")
	public void goToProfilePage() {
		homePage.goToProfileViewMenu();
	}

	@And("^I change my password from '(.+)' to '(.+)'$")
	public void changePassword(String oldPwd, String newPwd) {
		profilePage.changePassword(oldPwd, newPwd);
	}

	@After
	public void clear() {
		new HomePage().clear();
	}

}
