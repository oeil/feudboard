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

package org.teknux.feudboard.config.app;

import org.skife.config.Config;
import org.skife.config.Default;

import java.util.List;


public interface Configuration {

    @Config("debug")
    @Default("false")
    boolean isDebug();

    @Config("basePath")
    @Default("/")
    String getBasePath();

    @Config("ssl")
    @Default("false")
    boolean isSsl();

    @Config("port")
    @Default("8080")
    int getPort();

    @Config("jira.user")
    String getJiraUser();

    @Config("jira.password")
    String getJiraPassword();

    @Config("jira.domain.url")
    String getJiraDomainUrl();

    @Config("jira.projects.key.accept")
    @Default("")
    List<String> getJiraProjectsKeyAccept();
}
