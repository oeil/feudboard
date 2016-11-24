package org.teknux.feudboard.test.ws;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Realm;
import org.asynchttpclient.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.teknux.feudboard.ws.Ws;
import org.teknux.feudboard.ws.model.Project;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * @author Francois EYL
 */
public class WsProto {

    private static final String PROJECT_ID = "CRA";
    private static final String VERSION_ID = "3.13";
    private static final String API_URL = "https://jira.atlassian.com/rest/api/2/";

    private static Ws ws;

    private static Realm realm;
    private static AsyncHttpClient asyncHttpClient;

    @BeforeClass
    public static void setup() {
        ws = new Ws(API_URL);

        realm = new Realm.Builder("", "")
                .setUsePreemptiveAuth(true)
                .setScheme(Realm.AuthScheme.BASIC)
                .build();
        asyncHttpClient = new DefaultAsyncHttpClient();
    }

    @Test
    public void testProjects() {
        for (int i = 0; i<30; i++) {
            Ws.Result<List<Project>> r = ws.doGetList("project", Project.class, new Ws.Param[] {new Ws.Param("expand", "description")});

            Assert.assertNotNull(r);
            Assert.assertTrue(r.getObject().size() > 0);
        }
    }

    @Test
    public void testProjectsAsync() throws ExecutionException, InterruptedException, IOException {
        for (int i = 0; i<30; i++) {
            Future<Response> f = asyncHttpClient
                    .prepareGet("https://jira.atlassian.com/rest/api/2/project?expand=description")
                    .execute();

            Response r = f.get();

            /*f.get(new AsyncCompletionHandler<Ws.Result<List<Project>>>(){

                @Override
                public Ws.Result<List<Project>> onCompleted(Response response) throws Exception{
                    return null; // Parse the Response and return the appropriate obj
                }

                @Override
                public void onThrowable(Throwable t){
                    // Something wrong happened.
                }
            })*/

            List<Project> p = parseResponseList(r.getResponseBody(), Project.class);
            Assert.assertNotNull(p);
        }

    }

    private <T> T parseResponse(String rawJsonData, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(new JsonFactory().createParser(rawJsonData), clazz);
    }

    private <T> List<T> parseResponseList(String rawJsonData, Class<T> clazz) throws IOException {
        return new ObjectMapper().readValue(new JsonFactory().createParser(rawJsonData), new TypeReference<List<T>>() {});
    }
}
