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

package org.teknux.feudboard.controller;

import org.eclipse.jetty.http.HttpStatus;
import org.glassfish.jersey.server.mvc.Viewable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.teknux.feudboard.controller.constant.Route;
import org.teknux.feudboard.freemarker.View;
import org.teknux.feudboard.model.Message;
import org.teknux.feudboard.model.view.ProjectsModel;
import org.teknux.feudboard.model.view.RoadmapModel;
import org.teknux.feudboard.model.view.VersionsModel;
import org.teknux.feudboard.service.configuration.IConfigurationService;
import org.teknux.feudboard.service.ws.IWsService;
import org.teknux.feudboard.ws.Ws;
import org.teknux.feudboard.ws.model.IssuesSearch;
import org.teknux.feudboard.ws.model.Project;
import org.teknux.feudboard.ws.model.Version;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Francois EYL
 */
@Path("/")
@Produces({ MediaType.TEXT_HTML })
public class ViewsController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(ViewsController.class);

    @GET
    public Response index() throws URISyntaxException {
        return Response.seeOther(uri(Route.PROJECTS)).build();
    }

    @GET
    @Path("projects")
    public Viewable projects() {
        logger.trace("GET /projects");

        final Ws.Result<List<Project>> result = getServiceManager().getService(IWsService.class).projects();
        final View view = View.PROJECTS;
        if (!isWsError(result)) {
            List<Project> projects = result.getObject();

            //do filter projects if needed
            final List<String> projectKeys = getServiceManager().getService(IConfigurationService.class).getConfiguration().getJiraProjectsKeyAccept();
            if (projectKeys != null && !projectKeys.isEmpty()) {
                projects = result.getObject().stream().filter(p -> projectKeys.contains(p.getKey())).collect(Collectors.toList());
            }

            return viewable(view, new ProjectsModel(projects));
        } else {
            return viewable(view, new ProjectsModel());
        }
    }

    @GET
    @Path("projects/{key}/versions")
    public Viewable versions(@PathParam("key") String key) {
        logger.trace("GET /projects/{}/versions", key);

        final Ws.Result<Project> projectResult = getServiceManager().getService(IWsService.class).project(key);
        final Ws.Result<List<Version>> versionsResult = getServiceManager().getService(IWsService.class).versions(key);
        final View view = View.VERSIONS;
        if (isWsError(projectResult) || isWsError(versionsResult)) {
            //on ws error
            return viewable(view, new VersionsModel());
        }

        return viewable(view, new VersionsModel(projectResult.getObject(), versionsResult.getObject()));
    }

    @GET
    @Path("projects/{key}/versions/{version}")
    public Viewable version(@PathParam("key") String key, @PathParam("version") String version) {
        logger.trace("GET /projects/{}/versions/{}", key, version);

        final Ws.Result<Project> projectResult = getServiceManager().getService(IWsService.class).project(key);
        final Ws.Result<IssuesSearch> issuesResult = getServiceManager().getService(IWsService.class).issuesByRelease(key, version);

        final View view = View.ROADMAP;
        if (isWsError(projectResult) || isWsError(issuesResult)) {
            //on ws error
            return viewable(view, new RoadmapModel());
        }

        return viewable(view, new RoadmapModel(projectResult.getObject(), version, issuesResult.getObject()));
    }

    private boolean isWsError(final Ws.Result<?> result) {
        if (result.getStatusType().getStatusCode() != HttpStatus.OK_200) {
            final String errorMsg = MessageFormat.format("JIRA Web Service Communication Error : [{0}] [{1}]", result.getStatusType().getStatusCode(), result.getStatusType().getReasonPhrase());
            logger.error(errorMsg);
            addMessage(errorMsg, Message.Type.DANGER);
            return true;
        } else {
            return false;
        }
    }
}
