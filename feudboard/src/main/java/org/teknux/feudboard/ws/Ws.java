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

package org.teknux.feudboard.ws;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;


/**
 * @author Francois EYL
 */
public class Ws {

    private final Object lock = new Object();
    private String apiUrl;
    private IWsCredentials credentials;
    private WebTarget webTarget;

    public Ws(String apiUrl, IWsCredentials credentials) {
        this.apiUrl = apiUrl;
        this.credentials = credentials;
    }

    public Ws(String apiUrl) {
        this(apiUrl, null);
    }

    private void initialize() {
        synchronized (lock) {
            if (credentials == null) {
                final Client client = ClientBuilder.newClient(new ClientConfig());
                webTarget = client.target(apiUrl);
            } else {
                final HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(credentials.getUser(), credentials.getPassword());
                final Client client = ClientBuilder.newClient(new ClientConfig().register(feature));
                webTarget = client.target(apiUrl);
            }
        }
    }

    public <T> Result<T> doPost(String path, Object payload, Class<T> clazz) {
        synchronized (lock) {
            if (webTarget == null) {
                initialize();
            }
        }

        Response response = webTarget.path(path).request(MediaType.APPLICATION_JSON).post(Entity.json(payload));
        return parse(response, clazz);
    }

    public <T> Result<T> doGet(String path, Class<T> clazz, Param ...params) {
        synchronized (lock) {
            if (webTarget == null) {
                initialize();
            }
        }

        Response response = build(path, clazz, params).path(path).request(MediaType.APPLICATION_JSON).get();
        return parse(response, clazz);
    }

    public <T> Result<List<T>> doGetList(String path, Class<T> clazz, Param ...params) {
        Response response = build(path, clazz, params).request(MediaType.APPLICATION_JSON).get();
        return parseList(response, clazz);
    }

    private <T> WebTarget build(String path, Class<T> clazz, Param ...params) {
        synchronized (lock) {
            if (webTarget == null) {
                initialize();
            }
        }

        WebTarget t = webTarget.path(path);
        if (params != null) {
            for(Param param : params) {
                t= t.queryParam(param.getKey(), param.getValue());
            }
        }

        return t;
    }

    private <T> Result<T> parse(Response response, Class<T> clazz) {
        Response.StatusType status = response.getStatusInfo();
        T object = response.readEntity(clazz);
        response.close();

        return new Result<T>(status, object);
    }

    private <T> Result<List<T>> parseList(Response response, Class<T> clazz) {
        ParameterizedType parameterizedGenericType = new ParameterizedType() {
            public Type[] getActualTypeArguments() {
                return new Type[] { clazz };
            }

            public Type getRawType() {
                return List.class;
            }

            public Type getOwnerType() {
                return List.class;
            }
        };

        GenericType<List<T>> genericType = new GenericType<List<T>>(
                parameterizedGenericType) {
        };

        // TODO: handle web service errors here!!! Maybe throw a dedicated exception...
        Response.StatusType status = response.getStatusInfo();
        List<T> object = response.readEntity(genericType);
        response.close();

        return new Result<List<T>>(status, object);
    }

    public interface IWsCredentials {
        String getUser();
        String getPassword();
    }

    public class Result<T> {

        private Response.StatusType statusType;
        private T object;

        public Result(Response.StatusType statusType, T object) {
            this.statusType = statusType;
            this.object = object;
        }

        public Response.StatusType getStatusType() {
            return statusType;
        }

        public T getObject() {
            return object;
        }
    }

    public static class Param {

        private String key;
        private Object value;

        public Param(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
