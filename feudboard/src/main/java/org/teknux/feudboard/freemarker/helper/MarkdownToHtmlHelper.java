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

import freemarker.template.TemplateModelException;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import java.util.List;


public class MarkdownToHtmlHelper extends AbstractHelper {

    @Override
    public Object exec(@SuppressWarnings("rawtypes") List arguments) throws TemplateModelException {
        if (arguments.size() > 1) {
            throw new TemplateModelException("Invalid Argument");
        }

        String markdownString = "";
        if (arguments.size() == 1) {
            markdownString = getString(arguments.get(0));
        }

        final PegDownProcessor processor = new PegDownProcessor(Extensions.HARDWRAPS | Extensions.AUTOLINKS);
        final String html = processor.markdownToHtml(markdownString);

        return html;
    }
}
