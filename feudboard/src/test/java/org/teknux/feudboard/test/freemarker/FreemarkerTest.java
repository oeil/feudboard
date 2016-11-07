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

package org.teknux.feudboard.test.freemarker;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.runners.MockitoJUnitRunner;
import org.teknux.feudboard.freemarker.FreemarkerConfig;
import org.teknux.feudboard.service.ServiceException;
import org.teknux.feudboard.model.view.IModel;
import org.teknux.feudboard.model.view.Model;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class FreemarkerTest {

    private static final String RESOURCE_BASE_NAME = "i18n.dropbitz";

    private static final String MODEL_NAME_ATTRIBUTE = "model";
    private final static String VIEWS_PATH = "/views";
    private static final String VIEW_EXTENSION = ".ftl";

    private ServletContext servletContext = mock(ServletContext.class);
    private HttpServletRequest servletRequest = mock(HttpServletRequest.class);

    private FreemarkerConfig jerseyFreemarkerConfig;

    @Before
    public void initJerseyFreemarker() throws ServiceException {
        // Init Freemarker
        try {
            jerseyFreemarkerConfig = new FreemarkerConfig();
        } catch (TemplateModelException e) {
            throw new ServiceException(e);
        }
    }

    private String resolve(String viewName, IModel model) throws IOException, TemplateException {
        if (model == null) {
            model = new Model();
        }

        Template template = jerseyFreemarkerConfig.getTemplate(Objects.requireNonNull(VIEWS_PATH, "viewsPath can not be null") + Objects.requireNonNull(viewName, "viewName can not be null") + VIEW_EXTENSION);
        Writer writer = new StringWriter();

        model.setServletContext(servletContext);
        model.setHttpServletRequest(servletRequest);

        Map<String, IModel> map = new HashMap<String, IModel>();
        map.put(MODEL_NAME_ATTRIBUTE, model);

        template.process(map, writer);

        return writer.toString();
    }

    @Test
    public void test01Simple() throws IOException, TemplateException {
        Assert.assertEquals("simple", resolve("/simple", null));
    }

    @Test
    public void test02Model() throws IOException, TemplateException {
        Assert.assertEquals("testModel", resolve("/model", new FakeModel("testModel")));
    }

    @Test
    public void test03UrlHelper() throws IOException, TemplateException {
        when(servletContext.getContextPath()).thenReturn("");
        Assert.assertEquals("/", resolve("/urlHelperRoute", null));
        Assert.assertEquals("/url", resolve("/urlHelper", new FakeModel("url")));

        when(servletContext.getContextPath()).thenReturn("/root");
        Assert.assertEquals("/root", resolve("/urlHelperRoute", null));
        Assert.assertEquals("/root/url", resolve("/urlHelper", new FakeModel("url")));
    }
}
