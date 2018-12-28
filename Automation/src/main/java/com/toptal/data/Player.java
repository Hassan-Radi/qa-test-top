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

public class Player {

	private String playerName;
	private String playerProfileURL;
	private String PTS; // Points Per game
	private String AST; // Assists Per Game
	private String REB; // Rebounds Per Game

	public Player(String playerName, String playerProfileURL, String pTS, String aST, String rEB) {
		super();
		this.playerName = playerName;
		this.playerProfileURL = playerProfileURL;
		PTS = pTS;
		AST = aST;
		REB = rEB;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getPlayerProfileURL() {
		return playerProfileURL;
	}

	public String getPTS() {
		return PTS;
	}

	public String getAST() {
		return AST;
	}

	public String getREB() {
		return REB;
	}

	@Override
	public String toString() {
		return "Player [playerName = \"" + playerName + "\", playerProfileURL = \"" + playerProfileURL + "\", PTS = "
				+ PTS + ", AST = " + AST + ", REB = " + REB + "]";
	}
}