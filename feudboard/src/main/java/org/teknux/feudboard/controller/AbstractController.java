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

import org.glassfish.jersey.server.mvc.Viewable;
import org.teknux.feudboard.freemarker.View;
import org.teknux.feudboard.model.Message;
import org.teknux.feudboard.model.view.IModel;
import org.teknux.feudboard.model.view.Model;
import org.teknux.feudboard.service.IServiceManager;
import org.teknux.feudboard.service.ServiceManager;
import org.teknux.feudboard.service.configuration.IConfigurationService;
import org.teknux.feudboard.util.UrlUtil;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


abstract public class AbstractController {

    private List<Message> messages = null;

    @Inject
    private ServletContext servletContext;

    @Context
    private HttpServletRequest httpServletRequest;

    protected ServletContext getServletContext() {
        return Objects.requireNonNull(servletContext);
    }

    protected HttpServletRequest getHttpServletRequest() {
        return Objects.requireNonNull(httpServletRequest);
    }

    protected HttpSession getSession() {
        return Objects.requireNonNull(httpServletRequest).getSession();
    }

    protected IServiceManager getServiceManager() {
        return Objects.requireNonNull(ServiceManager.get(getServletContext()));
    }

    protected Viewable viewable(View view) {
        return viewable(view, new Model());
    }

    protected Viewable viewable(View view, IModel model) {
        return new Viewable(Objects.requireNonNull(view).getTemplateName(), initModel(Objects.requireNonNull(model, "model can not be null")));
    }

    private IModel initModel(IModel model) {
        model.setServletContext(servletContext);
        model.setHttpServletRequest(httpServletRequest);
        model.setMessages(messages);
        model.setBaseJiraUrl(getServiceManager().getService(IConfigurationService.class).getConfiguration().getJiraDomainUrl());

        return model;
    }

    protected void addMessage(String message, Message.Type type) {
        addMessage(null, message, type, null);
    }

    protected void addMessage(String id, String message, Message.Type type) {
        addMessage(id, message, type, null);

    }

    protected void addMessage(String message, Message.Type type, boolean closable) {
        addMessage(null, message, type, closable);

    }

    protected void addMessage(String id, String message, Message.Type type, Boolean closable) {
        if (messages == null) {
            messages = new ArrayList<Message>();
        }

        Message m = new Message();
        if (id != null) {
            m.setId(id);
        } else {
            m.setId(UUID.randomUUID().toString());
        }
        m.setMessage(Objects.requireNonNull(message, "message can not be null"));
        m.setType(Objects.requireNonNull(type, "type can not be null"));
        if (closable != null) {
            m.setClosable(closable);
        }
        messages.add(m);
    }

    protected String url(String route) {
        return UrlUtil.getAbsoluteUrl(getServletContext(), route);
    }

    protected URI uri(String route) throws URISyntaxException {
        return new URI(url(route));
    }
}
