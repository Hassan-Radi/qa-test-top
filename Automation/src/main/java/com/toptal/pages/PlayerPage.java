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

public class PlayerPage extends FilterPage {

	@FindBy(xpath = "//div[contains(@class,'player-stats__pts')]//span[@class='player-stats__stat-value']")
	private WebElement ptsElement;

	@FindBy(xpath = "//div[contains(@class,'player-stats__reb')]//span[@class='player-stats__stat-value']")
	private WebElement rebElement;

	@FindBy(xpath = "//div[contains(@class,'player-stats__ast')]//span[@class='player-stats__stat-value']")
	private WebElement astElement;

	public String getPTSText() {
		return ptsElement.getText();
	}

	public String getREBText() {
		return rebElement.getText();
	}

	public String getASTText() {
		return astElement.getText();
	}
}