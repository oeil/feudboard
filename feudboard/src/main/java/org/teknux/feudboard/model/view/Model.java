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

import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.teknux.feudboard.model.Message;

public class Model implements IModel {

    private ServletContext servletContext;
    private HttpServletRequest httpServletRequest;
    private List<Message> messages;
    private Locale locale;
    private String baseJiraUrl;

    public ServletContext getServletContext() {
        return servletContext;
    }

    public void setServletContext(ServletContext context) {
        this.servletContext = context;
    }

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public void setHttpServletRequest(HttpServletRequest request) {
        this.httpServletRequest = request;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String getBaseJiraUrl() {
        return baseJiraUrl;
    }

    public void setBaseJiraUrl(String baseJiraUrl) {
        this.baseJiraUrl = baseJiraUrl;
    }
}
