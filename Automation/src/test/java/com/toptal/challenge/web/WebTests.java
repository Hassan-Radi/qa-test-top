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
package com.toptal.challenge.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.toptal.challenge.BaseTest;
import com.toptal.data.Player;
import com.toptal.data.TestData;
import com.toptal.pages.LeadersPage;
import com.toptal.pages.PlayerPage;
import com.toptal.pages.TeamPage;
import com.toptal.pages.TeamStatusPerSeasonPage;
import com.toptal.util.Helper;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class WebTests extends BaseTest {

	@Test
	public void test1GetNumberOfWinsPerConference() {
		LOGGER.info("STEP 1 - Navigating to the website.");
		TeamStatusPerSeasonPage teamStatsPage = navigateToPage(TestData.TEAM_STATS_BY_SEASON_URL,
				TeamStatusPerSeasonPage.class);

		LOGGER.info("STEP 2 - Filter by 2017 'season' and East 'Conference' and then get the total wins value.");
		teamStatsPage.filterBy(TestData.SEASON_2017_VALUE, TestData.EAST);

		LOGGER.info("STEP 3 - Get number of wins for all the teams.");
		int eastWins = teamStatsPage.getNumberOfWinsInTable();
		teamStatsPage.resetFilters();
		LOGGER.info("\nEast Conference Wins = " + eastWins);
		assertEquals("East wins value is incorrect", TestData.EAST_CONFERENCE_2017_WINS, eastWins);

		LOGGER.info("STEP 4 - Filter by 2017 'season' and West 'Conference' and then get the total wins value.");
		teamStatsPage.filterBy(TestData.SEASON_2017_VALUE, TestData.WEST);

		LOGGER.info("STEP 5 - Get number of wins for all the teams.");
		int westWins = teamStatsPage.getNumberOfWinsInTable();
		teamStatsPage.resetFilters();
		LOGGER.info("\nWest Conference Wins = " + westWins);
		assertEquals("West wins value is incorrect", TestData.WEST_CONFERENCE_2017_WINS, westWins);
	}

	@Test
	@Parameters({ TestData.GOLDEN_STATE_WARRIORS_PARAM, TestData.HOUSTON_ROCKETS_PARAM })
	public void test2SumOfTeamPlayerPointsIsCorrect(String teamName, double sumValue) {
		LOGGER.info("STEP 1 - Navigating to the website.");
		LeadersPage leaderPage = navigateToPage(TestData.LEADERS_URL, LeadersPage.class);

		LOGGER.info("STEP 2 - Searching for the team.");
		TeamPage teamPage = leaderPage.searchForTeam(teamName);

		LOGGER.info("STEP 3 - Switch to 'Players' data type.");
		teamPage.switchToPlayersDataType();

		LOGGER.info("STEP 4 - Show season 2017 results.");
		teamPage.changeSeason(TestData.SEASON_2017_VALUE);

		double output = teamPage.getSumOfPoints();
		LOGGER.info("Got the sum of average points per player for the team. Value = " + output);
		assertEquals("incorrect sum value.", sumValue, output, 0.0001);
	}

	@Test
	public void test3PlayerStatusAreCorrect() {
		LOGGER.info("STEP 1 - Navigating to the website.");
		LeadersPage leaderPage = navigateToPage(TestData.LEADERS_URL, LeadersPage.class);

		LOGGER.info("STEP 2 - Get information of the first 3 palyers on the leaders page.");
		Player[] players = leaderPage.getPlayers(3);

		LOGGER.info(
				"STEP 3 - Navigate to each player profile and make sure that the information displayed matches the values retrieved before.");
		for (Player player : players) {
			LOGGER.info(player.toString());

			// navigate to the player profile page using the link you got
			PlayerPage playerPage = navigateToPage(player.getPlayerProfileURL(), PlayerPage.class);

			assertEquals("Incorrect PTS value is displayed in the player profile.", player.getPTS(),
					playerPage.getPTSText());
			assertEquals("Incorrect REB value is displayed in the player profile.", player.getREB(),
					playerPage.getREBText());
			assertEquals("Incorrect AST value is displayed in the player profile.", player.getAST(),
					playerPage.getASTText());
		}
	}

	@Test
	public void test4PlayerPageLoadingTimeIsAcceptable() {
		LOGGER.info("STEP 1 - Navigate to the player page.");
		driver.navigate().to(TestData.JAMES_HARDEN_PLAYER_PAGE);

		LOGGER.info("STEP 2 - Take the start timestamp value.");
		long startTime = System.currentTimeMillis();

		LOGGER.info("STEP 3 - Wait for Ajax requests to finish loading.");
		/**
		 * Method 1: Wait for the Ajax request to finish loading which would get an estimate of the
		 * request loading time. Not accurate enough as it doesn't get the loading of a specific
		 * request, but all Ajax requests in general. It takes into consideration the time to
		 * populate the data in the tables (which is not covered by method 2).
		 */
		Helper.waitForAjaxToComplete();

		LOGGER.info("STEP 4 - Take the finish timestamp value.");
		long finishTime = System.currentTimeMillis();

		long difference = finishTime - startTime;
		LOGGER.info(
				"METHOD 1: (Using Wait for Ajax) --> Assert - The difference between both timestamps should be less than 4 seconds. Current difference = "
						+ difference + " ms");
		assertTrue("Loading time for the player page should be less than 4 seconds.",
				difference < TestData.FOUR_MILLISECONDS);

		/**
		 * Method 2: Use JavaScript to accurately calculate the loading time for the Ajax request.
		 * This call is more accurate for the loading time of the request itself, but it ignores the
		 * time it takes to populate the data in the tables.
		 */
		long loadTime = Helper.getAjaxRequestLoadingTime(TestData.PLAYER_STATS_SEGMENT_URL);
		LOGGER.info(
				"METHOD 2: (Using JavaScript to get accurate URL load time) --> Assert - The request time should be less than 4 seconds. Current time = "
						+ loadTime + " ms");
		assertTrue("Loading time for the player page should be less than 4 seconds.",
				loadTime < TestData.FOUR_MILLISECONDS);
	}
}