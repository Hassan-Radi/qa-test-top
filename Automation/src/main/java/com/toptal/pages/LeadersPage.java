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

import com.toptal.data.Player;
import com.toptal.data.TestData;

public class LeadersPage extends FilterPage {

	@FindBy(xpath = "//tr[%s]//td[@class='player']/a")
	protected WebElement playerLink;

	@FindBy(xpath = "//input[contains(@class,'tats-search__input')]")
	protected WebElement searchTextField;

	@FindBy(xpath = "//span[@class='stats-search__icon-text']")
	protected WebElement searchIcon;

	@FindBy(xpath = "//a[@role='button' and text()='%s']")
	protected WebElement searchResultItem;

	/**
	 * Creates an array to store all the required player information from the table.
	 * 
	 * @param count
	 *            The number of players to retrieve from the table
	 * @return An array representing the player objects retrieved
	 */
	public Player[] getPlayers(int count) {
		Player[] players = new Player[count];

		for (int i = 0; i < count; i++) {
			String playerName = findElement(getClass(), "playerLink", i + 1).getText();
			String playerProfileURL = findElement(getClass(), "playerLink", i + 1).getAttribute(TestData.HREF_VALUE);
			String PTS = findElement(getClass(), "valueByRowNumberAndColumn", i + 1, TestData.PTS_VALUE).getText();
			String AST = findElement(getClass(), "valueByRowNumberAndColumn", i + 1, TestData.AST_VALUE).getText();
			String REB = findElement(getClass(), "valueByRowNumberAndColumn", i + 1, TestData.REB_VALUE).getText();

			players[i] = new Player(playerName, playerProfileURL, PTS, AST, REB);
		}
		return players;
	}

	/**
	 * Search the website for a team and navigate to the team page.
	 * 
	 * @param teamName
	 *            The team name to seach for.
	 * @return
	 */
	public TeamPage searchForTeam(String teamName) {
		LOGGER.info(String.format("Searching the website for team [%s]...", teamName));
		searchIcon.click();
		searchTextField.sendKeys(teamName);

		WebElement searchEntry = findElement(getClass(), "searchResultItem", teamName);
		wait.until(ExpectedConditions.visibilityOf(searchEntry));

		LOGGER.info("Navigating to the team page...");
		searchEntry.click();
		return new TeamPage();
	}
}