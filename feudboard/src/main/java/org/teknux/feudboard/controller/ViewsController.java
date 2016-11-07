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
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author Francois EYL
 */
@Path("/")
@Produces({ MediaType.TEXT_HTML })
public class ViewsController extends AbstractController {

    @GET
    public Response index() throws URISyntaxException {
        return Response.seeOther(uri(Route.PROJECTS)).build();
    }

    @GET
    @Path("projects")
    public Viewable projects() {
        final Ws.Result<List<Project>> result = getServiceManager().getService(IWsService.class).projects();

        if(result.getStatusType().getStatusCode() != HttpStatus.OK_200) {
            addMessage("JIRA communication error : " + result.getStatusType().getReasonPhrase(), Message.Type.DANGER);
            return viewable(View.PROJECTS, new ProjectsModel());
        } else {
            List<Project> projects = result.getObject();

            //do filter projects if needed
            final List<String> projectKeys = getServiceManager().getService(IConfigurationService.class).getConfiguration().getJiraProjectsKeyAccept();
            if (projectKeys != null && !projectKeys.isEmpty()) {
                projects = result.getObject().stream().filter(p -> projectKeys.contains(p.getKey())).collect(Collectors.toList());
            }

            return viewable(View.PROJECTS, new ProjectsModel(projects));
        }
    }

    @GET
    @Path("projects/{key}/versions")
    public Viewable versions(@PathParam("key") String key) {
        final Ws.Result<List<Version>> result = getServiceManager().getService(IWsService.class).versions(key);

        if(result.getStatusType().getStatusCode() != HttpStatus.OK_200) {
            addMessage("JIRA communication error : " + result.getStatusType().getReasonPhrase(), Message.Type.DANGER);
            return viewable(View.VERSIONS, new VersionsModel());
        } else {
            return viewable(View.VERSIONS, new VersionsModel(result.getObject()));
        }
    }

    @GET
    @Path("projects/{key}/versions/{version}")
    public Viewable version(@PathParam("key") String key, @PathParam("version") String version) {
        final Ws.Result<IssuesSearch> result = getServiceManager().getService(IWsService.class).issuesByRelease(key, version);

        if(result.getStatusType().getStatusCode() != HttpStatus.OK_200) {
            addMessage("JIRA communication error : " + result.getStatusType().getReasonPhrase(), Message.Type.DANGER);
            return viewable(View.ROADMAP, new RoadmapModel());
        } else {
            return viewable(View.ROADMAP, new RoadmapModel(version, result.getObject()));
        }
    }
}
