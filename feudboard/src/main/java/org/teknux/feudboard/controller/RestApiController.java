package org.teknux.feudboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.teknux.feudboard.service.ws.IWsService;
import org.teknux.feudboard.util.StopWatch;
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
import java.util.List;


/**
 * @author Francois EYL
 */
@Path("/api/")
@Produces({ MediaType.APPLICATION_JSON })
public class RestApiController extends AbstractController {

    private static Logger logger = LoggerFactory.getLogger(RestApiController.class);

    private static Response buildResponse(Ws.Result<?> result) {
        return Response.status(result.getStatusType()).entity(result.getObject()).build();
    }

    @GET
    @Path("projects")
    public Response projects() {
        StopWatch stopWatch = StopWatch.get();
        final Ws.Result<List<Project>> result = getServiceManager().getService(IWsService.class).projects();
        logger.debug("GET /api/projects [{} sec]", stopWatch.stop().getSeconds());
        return buildResponse(result);
    }

    @GET
    @Path("projects/{key}/versions")
    public Response versions(@PathParam("key") String key) {
        StopWatch stopWatch = StopWatch.get();
        final Ws.Result<List<Version>> result = getServiceManager().getService(IWsService.class).versions(key);
        logger.debug("GET /api/projects/{}/versions [{} sec]", key, stopWatch.stop().getSeconds());
        return buildResponse(result);
    }

    @GET
    @Path("projects/{key}/versions/{version}")
    public Response version(@PathParam("key") String key, @PathParam("version") String version) {
        StopWatch stopWatch = StopWatch.get();
        final Ws.Result<IssuesSearch> result = getServiceManager().getService(IWsService.class).issuesByRelease(key, version);
        logger.debug("GET /api/projects/{}/versions/{} [{} sec]", key, version, stopWatch.stop().getSeconds());
        return buildResponse(result);
    }
}
