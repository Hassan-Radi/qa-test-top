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
package com.toptal.util;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.toptal.core.DriverManager;
import com.toptal.data.TestData;

/**
 * provides some helper functions that can be used across the framework.
 */
public class Helper {

	/**
	 * Returns true if the currently used browser is Firefox
	 */
	public static boolean isFirefox() {
		if (DriverManager.getDriver() == null) {
			return false;
		} else if (DriverManager.getDriver() instanceof FirefoxDriver) {
			return true;
		}

		return false;
	}

	/**
	 * Returns true if the currently used browser is Chrome
	 */
	public static boolean isChrome() {
		if (DriverManager.getDriver() == null) {
			return false;
		} else if (DriverManager.getDriver() instanceof ChromeDriver) {
			return true;
		}

		return false;
	}

	/**
	 * Sometimes we need to click with JavaScript instead of relying on the Selenium native click
	 * method.
	 * 
	 * @param element
	 *            The element to click on
	 */
	public static void clickWithJavaScript(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
		executor.executeScript("arguments[0].click();", element);
	}

	/**
	 * Wait for page loading to complete
	 */
	public static void waitForPageLoadingToComplete() {
		new WebDriverWait(DriverManager.getDriver(), TestData.TWENTY_SECOND_WAIT_MILLI)
				.until(driver -> ((JavascriptExecutor) driver).executeScript(TestData.WAIT_FOR_PAGE_LOADING)
						.equals(TestData.COMPLETE_STATE));
	}

	/**
	 * Wait for all Ajax requests on the page to finish loading
	 */
	public static void waitForAjaxToComplete() {
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) ((JavascriptExecutor) DriverManager.getDriver())
							.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					// no jQuery present
					return true;
				}
			}
		};

		new WebDriverWait(DriverManager.getDriver(), TestData.TWENTY_SECOND_WAIT_MILLI).until(jQueryLoad);
	}

	/**
	 * Executes a JavaScript script to calculate the amount of time it took an Ajax request to
	 * complete
	 * 
	 * @param urlToCheck
	 *            The URL to check its loading time
	 * @return A long representing the amount of time in milliseconds that it took to complete the
	 *         request
	 */
	public static long getAjaxRequestLoadingTime(String urlToCheck) {
		String script = String.format("$.ajax({url: '%s',method: 'GET',start_time: new Date().getTime(),"
				+ "complete: function(data) {alert(new Date().getTime() - this.start_time);}});", urlToCheck);

		// Execute the script which would show an alert
		((JavascriptExecutor) DriverManager.getDriver()).executeAsyncScript(script);

		// Wait for the alert to appear
		Alert alertWithValue = new WebDriverWait(DriverManager.getDriver(), TestData.TWENTY_SECOND_WAIT_MILLI)
				.until(ExpectedConditions.alertIsPresent());

		// Get the alert text and accept it
		String alertText = alertWithValue.getText();
		alertWithValue.accept();

		return Long.valueOf(alertText);
	}
}