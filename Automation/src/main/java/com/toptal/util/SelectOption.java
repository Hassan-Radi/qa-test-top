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
package com.toptal.util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SelectOption {

	private WebElement element;

	public SelectOption(WebElement element) {
		this.element = element;
	}

	/**
	 * Selects an option from a Combo-Box given a specific criteria.
	 * 
	 * @param selectionCriteria
	 *            The type of select to use.
	 * @param criteriaValue
	 *            The criteria value to use
	 */
	public void selectBy(SelectionCriteriaEnum selectionCriteria, Object criteriaValue) {
		Select select = new Select(element);

		switch (selectionCriteria) {
		case SELECT_BY_INDEX:
			select.selectByIndex((Integer) criteriaValue);
			break;
		case SELECT_BY_VALUE:
			select.selectByValue((String) criteriaValue);
			break;
		case SELECT_BY_VISIBLE_TEXT:
			select.selectByVisibleText((String) criteriaValue);
			break;
		}
	}
}