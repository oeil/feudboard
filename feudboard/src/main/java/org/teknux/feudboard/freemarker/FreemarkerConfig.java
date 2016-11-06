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

package org.teknux.feudboard.freemarker;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.cache.WebappTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.TemplateModelException;
import jersey.repackaged.com.google.common.collect.Lists;
import org.teknux.feudboard.controller.constant.Route;
import org.teknux.feudboard.freemarker.helper.MarkdownToHtmlHelper;
import org.teknux.feudboard.freemarker.helper.UrlHelper;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;


public class FreemarkerConfig extends Configuration {

    public FreemarkerConfig() throws TemplateModelException {
        this(null);
    }

    @Inject
    public FreemarkerConfig(final ServletContext servletContext) throws TemplateModelException {
        super();

        //Copy of original configuration (org.glassfish.jersey.server.mvc.freemarker.FreemarkerViewProcessor)
        //=========================
        // Create different loaders
        final List<TemplateLoader> loaders = Lists.newArrayList();
        if (servletContext != null) {
            loaders.add(new WebappTemplateLoader(servletContext));
        }
        loaders.add(new ClassTemplateLoader(FreemarkerConfig.class, "/"));
        try {
            loaders.add(new FileTemplateLoader(new File("/")));
        } catch (IOException e) {
            // NOOP
        }

        // Create Factory
        setTemplateLoader(new MultiTemplateLoader(loaders.toArray(new TemplateLoader[loaders.size()])));
        //=========================

        //Add shared variables
        setSharedVariable("statics", BeansWrapper.getDefaultInstance().getStaticModels());
        setSharedVariable("url", new UrlHelper());
        setSharedVariable("route", BeansWrapper.getDefaultInstance().getStaticModels().get(Route.class.getName()));
        setSharedVariable("markdown", new MarkdownToHtmlHelper());
    }
}
