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

package org.teknux.feudboard.model.view;

import org.teknux.feudboard.ws.model.IssuesSearch;
import org.teknux.feudboard.ws.model.Project;


/**
 * @author Francois EYL
 */
public class VersionModel extends Model {

    private Project project;
    private String version;
    private IssuesSearch issuesSearch;
    private IssueStatistics statistics;

    public VersionModel() {
    }

    public VersionModel(Project project, String version, IssuesSearch issuesSearch, IssueStatistics statistics) {
        this();
        this.project = project;
        this.version = version;
        this.issuesSearch = issuesSearch;
        this.statistics = statistics;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public IssuesSearch getIssuesSearch() {
        return issuesSearch;
    }

    public void setIssuesSearch(IssuesSearch issuesSearch) {
        this.issuesSearch = issuesSearch;
    }

    public IssueStatistics getStatistics() {
        return statistics;
    }

    public void setStatistics(IssueStatistics statistics) {
        this.statistics = statistics;
    }
}
