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

package org.teknux.feudboard.freemarker.helper;

import java.util.List;

import javax.servlet.ServletContext;

import org.teknux.feudboard.model.view.IModel;
import org.teknux.feudboard.util.UrlUtil;

import freemarker.template.TemplateModelException;

public class UrlHelper extends AbstractHelper {

    @Override
    public Object exec(@SuppressWarnings("rawtypes") List arguments) throws TemplateModelException {
        if (arguments.size() > 1) {
            throw new TemplateModelException("Invalid Argument");
        }
        String path = "";
        if (arguments.size() == 1) {
            path = getString(arguments.get(0));
        }        

        IModel iModel = getIModel();
        
        if (iModel.getServletContext() == null) {
            throw new TemplateModelException("Cannot get ServletContext");
        }
        ServletContext servletContext = iModel.getServletContext();
        
        return UrlUtil.getAbsoluteUrl(servletContext, path);
    }
}
