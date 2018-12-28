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
package com.toptal.challenge.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.toptal.api.Top10Scorers;
import com.toptal.data.TestData;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APITests {

	@Test
	public void getItalysTop10ScorersTest() {
		RestAssured.baseURI = TestData.ITALYS_TOP_LEAGUE_SCORERS;
		RequestSpecification httpRequest = RestAssured.given().header(TestData.HEADER_TOKEN_NAME,
				TestData.API_TOKEN_ENVIRONEMNT_VARIABLE);

		Response response = httpRequest.get();

		// parse the response to a data object
		Top10Scorers top10Scoreres = response.getBody().as(Top10Scorers.class);

		// verify that the response returns only 10 results as it should
		assertEquals("Count of elements returned should be 10.", TestData.TEN_VALUE, top10Scoreres.getCount());
		assertEquals("Count of scorers returned should be 10.", TestData.TEN_VALUE, top10Scoreres.getScorers().length);

		// print player names
		for (int i = 0; i < top10Scoreres.getScorers().length; i++) {
			System.out.println(String.format("Scorer [%d] name = [%s].", (i + 1),
					top10Scoreres.getScorers()[i].getPlayer().getName()));
		}

		// verify the status code and content type
		response.then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
	}

	@Test
	public void getNonExistingResourceTest() {
		RestAssured.baseURI = TestData.NON_EXISTING_RESOURCE;
		RequestSpecification httpRequest = RestAssured.given().header(TestData.HEADER_TOKEN_NAME,
				TestData.API_TOKEN_ENVIRONEMNT_VARIABLE);

		Response response = httpRequest.get();

		// verify the status code and content type
		response.then().assertThat().statusCode(404).and().contentType(ContentType.JSON);
	}

	@Test
	public void tooManyRequestsTest() {
		RestAssured.baseURI = TestData.ITALYS_TOP_LEAGUE_SCORERS;
		RequestSpecification httpRequest = RestAssured.given().header(TestData.HEADER_TOKEN_NAME,
				TestData.API_TOKEN_ENVIRONEMNT_VARIABLE);

		int count = 0;
		Response response = null;
		// send too many requests
		while (true) {
			response = httpRequest.get();
			count++;

			// break when exceeding the limit
			if (response.getStatusCode() == 429) {
				System.out.println(String.format("\n\nExceeded the limit after [%d] requests.", count));
				break;
			}
		}

		// verify the status code and content type
		response.then().assertThat().statusCode(429).and().contentType(ContentType.JSON);
	}
}