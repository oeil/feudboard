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

package org.teknux.feudboard.service.ws;

import org.teknux.feudboard.config.app.Configuration;
import org.teknux.feudboard.exception.ServiceException;
import org.teknux.feudboard.service.IServiceManager;
import org.teknux.feudboard.service.configuration.IConfigurationService;
import org.teknux.feudboard.ws.Ws;
import org.teknux.feudboard.ws.model.IssuesSearch;
import org.teknux.feudboard.ws.model.JqlSearch;
import org.teknux.feudboard.ws.model.Project;
import org.teknux.feudboard.ws.model.Version;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;


/**
 * @author Francois EYL
 */
public class WsServiceImpl implements IWsService {

    private Ws ws;

    @Override
    public void start(IServiceManager serviceManager) throws ServiceException {
        final Configuration configuration = serviceManager.getService(IConfigurationService.class).getConfiguration();
        final String jiraUrl = configuration.getJiraDomainUrl();

        final String apiUrl = MessageFormat.format("{0}/rest/api/2/", jiraUrl);
        final Ws.IWsCredentials credentials = new Ws.IWsCredentials() {

            @Override
            public String getUser() {
                return configuration.getJiraUser();
            }

            @Override
            public String getPassword() {
                return configuration.getJiraPassword();
            }
        };

        this.ws = new Ws(apiUrl, credentials);
    }

    @Override
    public void stop() throws ServiceException {
        this.ws = null;
    }

    @Override
    public Ws.Result<IssuesSearch> issuesByRelease(final String project, final String release) {
        JqlSearch jqlSearchQuery = new JqlSearch();
        jqlSearchQuery.jql = MessageFormat.format("project={0} AND (type=bug OR type=story) AND fixVersion=''{1}''", project, release);
        jqlSearchQuery.fields = Arrays.asList(new String[] { "key", "summary", "description", "components", "issuetype", "status" });
        jqlSearchQuery.maxResults = 9999;

        return ws.doPost("search", jqlSearchQuery, IssuesSearch.class);
    }

    @Override
    public Ws.Result<List<Project>> projects() {
        return ws.doGetList("project", Project.class, new Ws.Param[] {new Ws.Param("expand", "description")});
    }

    @Override
    public Ws.Result<List<Version>> versions(final String projectKey) {
        return ws.doGetList("project/" + projectKey + "/versions", Version.class);
    }
}