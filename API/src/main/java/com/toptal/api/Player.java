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
package com.toptal.api;

public class Player {

	private int id;
	private String name;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String countryOfBirth;
	private String nationality;
	private String position;
	private int shirtNumber;
	private String lastUpdated;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getCountryOfBirth() {
		return countryOfBirth;
	}

	public String getNationality() {
		return nationality;
	}

	public String getPosition() {
		return position;
	}

	public int getShirtNumber() {
		return shirtNumber;
	}

	public String getLastUpdated() {
		return lastUpdated;
	}
}