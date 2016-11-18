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

import org.teknux.feudboard.service.IService;
import org.teknux.feudboard.ws.Ws;
import org.teknux.feudboard.ws.model.IssuesSearch;
import org.teknux.feudboard.ws.model.Project;
import org.teknux.feudboard.ws.model.Version;

import java.util.List;


/**
 * @author Francois EYL
 */
public interface IWsService extends IService {

    Ws.Result<IssuesSearch> issuesByRelease(String project, String release);

    Ws.Result<List<Project>> projects();

    Ws.Result<Project> project(String projectKey);

    Ws.Result<List<Version>> versions(String projectKey);

}
