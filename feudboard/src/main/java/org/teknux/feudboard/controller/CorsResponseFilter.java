package org.teknux.feudboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;


/**
 * This reponse filter allow CORS support from any origin.
 *
 * @author Francois EYL
 */
public class CorsResponseFilter implements ContainerResponseFilter {

    private static Logger logger = LoggerFactory.getLogger(CorsResponseFilter.class);

    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {

        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
        //headers.add("Access-Control-Allow-Origin", "http://mydomain.org"); //allows CORS requests only coming from mydomain.org
        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        headers.add("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");

        logger.trace("CORS Headers added to Response");
    }

}
