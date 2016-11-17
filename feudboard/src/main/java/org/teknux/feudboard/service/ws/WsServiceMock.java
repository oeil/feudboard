package org.teknux.feudboard.service.ws;

import org.teknux.feudboard.service.IServiceManager;
import org.teknux.feudboard.service.ServiceException;
import org.teknux.feudboard.ws.Ws;
import org.teknux.feudboard.ws.model.IssuesSearch;
import org.teknux.feudboard.ws.model.Project;
import org.teknux.feudboard.ws.model.Version;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Francois EYL
 */
public class WsServiceMock implements IWsService {

    @Override
    public void start(IServiceManager serviceManager) throws ServiceException {

    }

    @Override
    public void stop() throws ServiceException {

    }

    @Override
    public Ws.Result<IssuesSearch> issuesByRelease(String project, String release) {
        return new Ws.Result<IssuesSearch>(new Response.StatusType() {

            @Override
            public int getStatusCode() {
                return 200;
            }

            @Override
            public Response.Status.Family getFamily() {
                return null;
            }

            @Override
            public String getReasonPhrase() {
                return null;
            }
        }, null);
    }

    @Override
    public Ws.Result<List<Project>> projects() {
        return new Ws.Result<List<Project>>(new Response.StatusType() {

            @Override
            public int getStatusCode() {
                return 200;
            }

            @Override
            public Response.Status.Family getFamily() {
                return null;
            }

            @Override
            public String getReasonPhrase() {
                return null;
            }
        }, null);
    }

    @Override
    public Ws.Result<List<Version>> versions(String projectKey) {
        return new Ws.Result<List<Version>>(new Response.StatusType() {

            @Override
            public int getStatusCode() {
                return 200;
            }

            @Override
            public Response.Status.Family getFamily() {
                return null;
            }

            @Override
            public String getReasonPhrase() {
                return null;
            }
        }, null);
    }
}
