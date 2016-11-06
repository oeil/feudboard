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


/**
 * @author Francois EYL
 */
public class RoadmapModel extends Model {

    private String version;
    private IssuesSearch issuesSearch;

    public RoadmapModel() {
    }

    public RoadmapModel(String version, IssuesSearch issuesSearch) {
        this();
        this.version = version;
        this.issuesSearch = issuesSearch;
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
}
