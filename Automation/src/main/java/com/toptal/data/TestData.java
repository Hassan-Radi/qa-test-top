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
package com.toptal.data;

import org.openqa.selenium.Dimension;

import com.toptal.core.ExecutionMode;

/**
 * Class to act as a container for all the test data in the project. When you need to change any of
 * these values, you change it in one location and it reflects everywhere else in the whole project.
 */
public class TestData {

	// configs
	public static final String BROWSER_PROPERTY = "browser";
	public static final String FIREFOX_BROWSER = "firefox";
	public static final String CHROME_BROWSER = "chrome";
	public static final ExecutionMode EXECUTION_MODE = ExecutionMode.valueOf(System.getProperty("executionMode"));
	public static final String BROWSER = System.getProperty(BROWSER_PROPERTY);

	// Environment variables
	public static final String BROWSERSTACK_USER_ENVIRONEMNT_VARIABLE = System.getenv("BROWSERSTACK_USER");
	public static final String BROWSERSTACK_TOKEN_ENVIRONEMNT_VARIABLE = System.getenv("BROWSERSTACK_TOKEN");

	// Timeouts
	public static final int TEN_SECONDS = 10;
	public static final int FIFTY_MILLI_SECONDS = 50;
	public static final int FOUR_MILLISECONDS = 4000;
	public static final int TWENTY_SECOND_WAIT_MILLI = 20000;

	// URLs
	public static final String STATS_BY_SEASON_URL = "http://stats.nba.com/";
	public static final String TEAM_STATS_BY_SEASON_URL = "http://stats.nba.com/teams/traditional/?sort=W_PCT&dir=-1";
	public static final String LEADERS_URL = "https://stats.nba.com/leaders/";
	public static final String JAMES_HARDEN_PLAYER_PAGE = "https://stats.nba.com/player/201935/traditional/";
	public static final String PLAYER_STATS_SEGMENT_URL = "https://stats.nba.com/stats/playerdashboardbygeneralsplits?"
			+ "DateFrom=&DateTo=&GameSegment=&LastNGames=0&LeagueID=00&Location=&MeasureType=Base&Month=0&OpponentTeamID=0"
			+ "&Outcome=&PORound=0&PaceAdjust=N&PerMode=PerGame&Period=0&PlayerID=201935&PlusMinus=N&Rank=N&Season=2018-19"
			+ "&SeasonSegment=&SeasonType=Regular+Season&ShotClockRange=&Split=general&VsConference=&VsDivision=";

	// Selenium
	public static final String BROWSERSTACK_HUB_URL = "https://%s:%s@hub-cloud.browserstack.com/wd/hub";
	public static final String BROWSER_CAPABILITY = "browser";
	public static final String BROWSER_VERSION_CAPABILITY = "browser_version";
	public static final String BROWSER_NAME_W3C_CAPABILITY = "browserName";
	public static final String BROWSER_VERSION_W3C_CAPABILITY = "browserVersion";
	public static final String OS_CAPABILITY = "os";
	public static final String OS_VERSION_CAPABILITY = "os_version";
	public static final String RESOLUTION_CAPABILITY = "resolution";
	public static final String RESOLUTION_VALUE = "1920x1080";
	public static final Dimension RESOLUTION_DIMENSION_VALUE = new Dimension(1920, 1080);
	public static final String ACCEPT_SSL_ALERTS_CAPABILITY = "acceptSSLAlerts";
	public static final String SELENIUM_VERSION_CAPABILITY = "browserstack.selenium_version";
	public static final String SELENIUM_VERSION_VALUE = "3.14.0";
	public static final String USE_W3C_MODE_CAPABILITY = "browserstack.use_w3c";
	public static final String PROJECT_CAPABILITY = "project";
	public static final String PROJECT_VALUE = "Toptal";
	public static final String BROWSER_LOG_FILE_PATH = "/dev/null";
	public static final String CHROME_BROWSER_VERSION = "70.0";
	public static final String FIREFOX_BROWSER_VERSION = "64.0";
	public static final String WINDOWS_OS_NAME = "Windows";
	public static final String WINDOWS_10_NAME = "10";
	public static final String TRUE_VALUE = "true";

	// GeckoDriver
	public static final String GECKO_DRIVER_VERSION_CAPABILITY = "browserstack.geckoversion";
	public static final String GECKO_DRIVER_VERSION_VALUE = "0.23.0";
	public static final String GECKO_DRIVER_LOCAL_PATH_PROPERTY = "webdriver.gecko.driver";
	public static final String GECKO_DRIVER_LOCAL_PATH = "src/test/resources/geckodriver";

	// ChromeDriver
	public static final String CHROME_DRIVER_VERSION_CAPABILITY = "browserstack.chrome.driver";
	public static final String CHROME_DRIVER_VERSION_VALUE = "2.43";
	public static final String CHROME_DRIVER_DRIVER_LOCAL_PATH_PROPERTY = "webdriver.chrome.driver";
	public static final String CHROME_DRIVER_DRIVER_LOCAL_PATH = "src/test/resources/chromedriver";

	// Misc
	public static final String SEASON_2017_VALUE = "2017-18";
	public static final String EAST = "East";
	public static final int EAST_CONFERENCE_2017_WINS = 603;
	public static final int WEST_CONFERENCE_2017_WINS = 627;
	public static final String WEST = "West";
	public static final String W_COLUMN_NAME = "W";
	public static final String PTS_VALUE = "PTS";
	public static final String AST_VALUE = "AST";
	public static final String REB_VALUE = "REB";
	public static final String HREF_VALUE = "href";
	public static final String GOLDEN_STATE_WARRIORS = "Golden State Warriors";
	public static final double GOLDEN_STATE_WARRIORS_SUM_AVERAGE_POINTS = 149.1;
	public static final String GOLDEN_STATE_WARRIORS_PARAM = GOLDEN_STATE_WARRIORS + ", "
			+ GOLDEN_STATE_WARRIORS_SUM_AVERAGE_POINTS;
	public static final String HOUSTON_ROCKETS = "Houston Rockets";
	public static final double HOUSTON_ROCKETS_SUM_AVERAGE_POINTS = 173.7;
	public static final String HOUSTON_ROCKETS_PARAM = HOUSTON_ROCKETS + ", " + HOUSTON_ROCKETS_SUM_AVERAGE_POINTS;
	// JavaScript
	public static final String WAIT_FOR_PAGE_LOADING = "return document.readyState";
	public static final String COMPLETE_STATE = "complete";
}