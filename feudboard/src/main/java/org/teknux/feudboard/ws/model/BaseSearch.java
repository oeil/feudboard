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


/**
 * Describes shared Search Response properties resource in JIRA.
 *
 * @author Francois EYL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BaseSearch {

    private String expand;
    private Integer startAt;
    private Integer maxResults;
    private Integer total;

    public String getExpand() {
        return expand;
    }

    public void setExpand(String expand) {
        this.expand = expand;
    }

    public Integer getStartAt() {
        return startAt;
    }

    public void setStartAt(Integer startAt) {
        this.startAt = startAt;
    }

    public Integer getMaxResults() {
        return maxResults;
    }

    public void setMaxResults(Integer maxResults) {
        this.maxResults = maxResults;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BaseSearch that = (BaseSearch) o;

        if (expand != null ? !expand.equals(that.expand) : that.expand != null)
            return false;
        if (startAt != null ? !startAt.equals(that.startAt) : that.startAt != null)
            return false;
        if (maxResults != null ? !maxResults.equals(that.maxResults) : that.maxResults != null)
            return false;
        return total != null ? total.equals(that.total) : that.total == null;

    }

    @Override
    public int hashCode() {
        int result = expand != null ? expand.hashCode() : 0;
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (maxResults != null ? maxResults.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }
}
