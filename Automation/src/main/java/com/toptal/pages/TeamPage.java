package com.toptal.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.toptal.data.TestData;
import com.toptal.util.Helper;

public class TeamPage extends FilterPage {

	@FindBy(xpath = "//div[@class='toggle-nav-component__button']/a[text()='Team Stats']")
	protected WebElement toggleDataTypeLink;

	@FindBy(xpath = "//a[@data-type-beta='players']")
	protected WebElement playersLink;

	@FindBy(xpath = "//nba-stat-table[@rows='datasets.PlayersSeasonTotals.rows']//tr[%s]//td[count(//nba-stat-table[@rows='datasets.PlayersSeasonTotals.rows']//th[@data-field='%s']//preceding-sibling::th)+1]")
	protected WebElement valueByRowNumberAndColumnInPlayersDataSet;

	@FindBy(xpath = "//nba-stat-table[@rows='datasets.PlayersSeasonTotals.rows']//div[@class='nba-stat-table__overlay']//td[@class='first']")
	protected WebElement playerName;

	public void switchToPlayersDataType() {
		LOGGER.info("Clicking on the toggle data type link...");
		toggleDataTypeLink.click();

		LOGGER.info("Selecting 'Players' data type...");
		playersLink.click();

		// wait for loading to finish
		Helper.waitForPageLoadingToComplete();
		Helper.waitForAjaxToComplete();

		LOGGER.info("Waiting until the table results load...");
		wait.until(ExpectedConditions.visibilityOf(tableElement));
	}

	/**
	 * Gets the sum of average points per player
	 * 
	 * @return
	 */
	public double getSumOfPoints() {
		double sum = 0;
		int countOfPlayers = getElementCount(getClass(), "playerName");

		for (int i = 0; i < countOfPlayers; i++) {
			sum += Double.valueOf(
					findElement(getClass(), "valueByRowNumberAndColumnInPlayersDataSet", (i + 1), TestData.PTS_VALUE)
							.getText());
		}

		return sum;
	}
}