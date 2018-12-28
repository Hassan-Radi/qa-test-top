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
package com.toptal.pages;

import java.util.logging.Logger;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.toptal.core.PageObject;
import com.toptal.util.Helper;
import com.toptal.util.SelectOption;
import com.toptal.util.SelectionCriteriaEnum;

public class FilterPage extends PageObject {

	protected static final Logger LOGGER = Logger.getLogger(FilterPage.class.getName());

	public FilterPage() {
		/**
		 * Make sure that the page loaded successfully before interacting with it
		 */
		wait.until(ExpectedConditions.visibilityOf(advancedFilterButton));
	}

	@FindBy(xpath = "//a[@class='stats-filters-advanced__toggle']")
	protected WebElement advancedFilterButton;

	@FindBy(xpath = "//a[@class='run-it']")
	protected WebElement runSearchButton;

	@FindBy(xpath = "//div[@class='nba-stat-table']")
	protected WebElement tableElement;

	@FindBy(xpath = "//a[@ng-click='reset();']")
	protected WebElement resetFiltersButton;

	@FindBy(xpath = "//tr[%s]//td[count(//table//th[@data-field='%s']//preceding-sibling::th)+1]")
	protected WebElement valueByRowNumberAndColumn;

	@FindBy(xpath = "//select[@name='Season']")
	private WebElement seasonSelect;

	/**
	 * Changing the season value
	 * 
	 * @param seasonValue
	 */
	public void changeSeason(String seasonValue) {
		LOGGER.info("Selecting 'Season' value as " + seasonValue);
		seasonSelect.click();
		SelectOption seasonConference = new SelectOption(seasonSelect);
		seasonConference.selectBy(SelectionCriteriaEnum.SELECT_BY_VISIBLE_TEXT, seasonValue);

		// wait for loading to finish
		Helper.waitForPageLoadingToComplete();
		Helper.waitForAjaxToComplete();

		LOGGER.info("Waiting until the table results load...");
		wait.until(ExpectedConditions.visibilityOf(tableElement));
	}
}