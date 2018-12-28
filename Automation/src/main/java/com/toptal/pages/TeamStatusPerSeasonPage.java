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

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.toptal.data.TestData;
import com.toptal.util.SelectOption;
import com.toptal.util.SelectionCriteriaEnum;

/**
 * Page object that represents all the elements on the current website page
 */
public class TeamStatusPerSeasonPage extends FilterPage {

	@FindBy(xpath = "//select[@name='Conference']")
	private WebElement conferenceSelect;

	@FindBy(xpath = "//div[@class='nba-stat-table__overlay']//td[@class='first']")
	private WebElement teamName;

	/**
	 * Use Advance filter to filter the results in the table
	 * 
	 * @param seasonValue
	 * @param conferenceValue
	 */
	public void filterBy(String seasonValue, String conferenceValue) {
		changeSeason(seasonValue);

		LOGGER.info("Toggling 'Advanced Filters' on...");
		advancedFilterButton.click();

		LOGGER.info("Selecting 'Conference' value as " + conferenceValue);
		conferenceSelect.click();
		SelectOption selectConference = new SelectOption(conferenceSelect);
		selectConference.selectBy(SelectionCriteriaEnum.SELECT_BY_VISIBLE_TEXT, conferenceValue);

		LOGGER.info("Clicking on the 'RUN IT' button...");
		runSearchButton.click();

		LOGGER.info("Waiting until the table results load...");
		wait.until(ExpectedConditions.visibilityOf(tableElement));
	}

	/**
	 * Reset the filters to their initial value again.
	 */
	public void resetFilters() {
		LOGGER.info("Resetting the filters to their initial value...");
		resetFiltersButton.click();

		LOGGER.info("Waiting until the table results load...");
		wait.until(ExpectedConditions.visibilityOf(tableElement));

		LOGGER.info("Toggling 'Advanced Filters' off...");
		advancedFilterButton.click();
	}

	/**
	 * Get the total number of wins in the table for all the rows
	 * 
	 * @return
	 */
	public int getNumberOfWinsInTable() {
		int total = 0;
		int teamCount = 0;

		// get the number of rows/teams in the table
		teamCount = getElementCount(getClass(), "teamName");

		// get the total number of wins for those teams
		for (int i = 1; i <= teamCount; i++) {
			total += Integer
					.valueOf(findElement(getClass(), "valueByRowNumberAndColumn", i, TestData.W_COLUMN_NAME).getText());
		}

		return total;
	}
}