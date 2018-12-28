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

/**
 * Class to act as a container for all the test data in the project. When you need to change any of
 * these values, you change it in one location and it reflects everywhere else in the whole project.
 */
public class TestData {

	// Environment variables
	public static final String API_TOKEN_ENVIRONEMNT_VARIABLE = System.getenv("API_TOKEN");

	// Misc
	public static final int TEN_VALUE = 10;

	// API
	public static final String HEADER_TOKEN_NAME = "X-Auth-Token";
	public static final String BASE_API_END_POINT = "https://api.football-data.org/v2";
	public static final String ITALYS_TOP_LEAGUE_SCORERS = BASE_API_END_POINT + "/competitions/SA/scorers";
	public static final String NON_EXISTING_RESOURCE = "https://api.football-data.org/v1/competitions/SA/scorers";
}