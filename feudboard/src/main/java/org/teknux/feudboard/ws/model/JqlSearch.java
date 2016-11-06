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

package org.teknux.feudboard.ws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


/**
 * Describe a JPQL resource in JIRA.
 *
 * @author Francois EYL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JqlSearch {

    public String jql;
    public Integer maxResults;
    public List<String> fields;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        JqlSearch that = (JqlSearch) o;

        if (jql != null ? !jql.equals(that.jql) : that.jql != null)
            return false;
        return fields != null ? fields.equals(that.fields) : that.fields == null;

    }

    @Override
    public int hashCode() {
        int result = jql != null ? jql.hashCode() : 0;
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }
}
