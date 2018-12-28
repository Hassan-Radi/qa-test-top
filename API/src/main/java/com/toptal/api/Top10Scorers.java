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

public class Top10Scorers {

	private int count;
	private Filter filters;
	private Competition competition;
	private Season season;
	private Scorer[] scorers;

	public Filter getFilters() {
		return filters;
	}

	public Season getSeason() {
		return season;
	}

	public Competition getCompetition() {
		return competition;
	}

	public int getCount() {
		return count;
	}

	public Scorer[] getScorers() {
		return scorers;
	}
}