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

package org.teknux.feudboard.test.ws;

import org.junit.Assert;
import org.junit.Test;
import org.teknux.feudboard.ws.Ws;
import org.teknux.feudboard.ws.model.IssuesSearch;
import org.teknux.feudboard.ws.model.JqlSearch;
import org.teknux.feudboard.ws.model.Project;
import org.teknux.feudboard.ws.model.Version;

import java.util.Arrays;
import java.util.List;


/**
 * @author Francois EYL
 */
public class WsServiceTests {

    private static final String PROJECT_ID = "CRA";
    private static final String VERSION_ID = "3.13";
    private static final String API_URL = "https://jira.atlassian.com/rest/api/2/";
    private static final Ws.IWsCredentials CREDENTIALS = new Ws.IWsCredentials() {

        @Override
        public String getUser() {
            return "user";
        }

        @Override
        public String getPassword() {
            return "pwd";
        }
    };

    @Test
    public void testJqlSearch() {
        JqlSearch jqlSearchQuery = new JqlSearch();
        jqlSearchQuery.jql = "(type=bug OR type=story) AND fixVersion='" + VERSION_ID + "'";
        jqlSearchQuery.fields = Arrays.asList(new String[] { "key", "summary", "description", "components"});

        Ws ws = new Ws(API_URL);
        Ws.Result<IssuesSearch> r = ws.doPost("search", jqlSearchQuery, IssuesSearch.class);

        Assert.assertNotNull(r);
        Assert.assertTrue(r.getObject() instanceof IssuesSearch);
    }

    @Test
    public void testProjects() {
        Ws ws = new Ws(API_URL);
        Ws.Result<List<Project>> r = ws.doGetList("project", Project.class, new Ws.Param[] {new Ws.Param("expand", "description")});

        Assert.assertNotNull(r);
        Assert.assertTrue(r.getObject().size() > 0);
    }

    @Test
    public void testVersions() {
        Ws ws = new Ws(API_URL);
        Ws.Result<List<Version>> r = ws.doGetList("project/"+ PROJECT_ID +"/versions", Version.class);

        Assert.assertNotNull(r);
        Assert.assertTrue(r.getObject().size() > 0);
    }
}
