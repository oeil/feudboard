/*
 * Copyright (C) 2016 TekNux.org
 *
 * This file is part of the feudboard GPL Source Code.
 *
 * feudboard Source Code is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * feudboard Source Code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with dropbitz Community Source Code.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.teknux.feudboard.test.controllers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.junit.ClassRule;


public class BaseControllerTest {

	@ClassRule
	public static final StartApplicationRule RULE = new StartApplicationRule();

	public String getBaseUrl() {
		return String.format("http://localhost:%d", RULE.getConfiguration().getPort());
	}

	public Client createClient() {
		return ClientBuilder.newClient();
	}

	public WebTarget getWebTarget(final String relativeUrl) {
		return createClient().target(getBaseUrl() + relativeUrl);
	}
}
