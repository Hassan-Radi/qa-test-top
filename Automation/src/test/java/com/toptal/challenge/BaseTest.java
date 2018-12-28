/**
 * Copyright 2018 Hassan Radi 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * 
 * See the License for the specific language governing permissions and limitations under the License.
 */
package com.toptal.challenge;

import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.toptal.core.DriverManager;
import com.toptal.core.PageObject;

/**
 * Base test class for the common setup and driver creation for all the test cases.
 */
public class BaseTest {

	protected final static Logger LOGGER = Logger.getLogger(BaseTest.class.getName());
	protected static WebDriver driver = null;

	/**
	 * Navigate to a website page given the URL and create an instance of a page object given its
	 * class name
	 * 
	 * @param pageURL
	 * @param clazz
	 * @return
	 */
	public <T extends PageObject> T navigateToPage(String pageURL, Class<T> clazz) {
		LOGGER.info("Navigating to page with URL = " + pageURL);
		DriverManager.getDriver().get(pageURL);
		return PageFactory.initElements(driver, clazz);
	}

	@Before
	public void beforeMethod() {
		LOGGER.info("\n*************************************************************************************\n"
				+ "*************************************NEW TEST****************************************\n"
				+ "*************************************************************************************");
	}

	@After
	public void afterMethod() {
		/**
		 * delete all cookies in the session to be able to start the new test with no old data
		 */
		DriverManager.getDriver().manage().deleteAllCookies();
		LOGGER.info("Cleaned all the session cookies to start running a new test.\n\n");
	}

	@BeforeClass
	public static void setUp() {
		driver = DriverManager.getDriver();
	}

	@AfterClass
	public static void tearDown() {
		/**
		 * clear after the test is done executing by terminating the running browser instance to
		 * prevent memory leaks.
		 */
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
			LOGGER.info("Terminating the driver session and killing the browser...");
		}
	}
}