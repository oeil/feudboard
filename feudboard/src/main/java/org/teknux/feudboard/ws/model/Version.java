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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


/**
 * @author Francois EYL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Version {

    private String name;
    private String description;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private Date releaseDate = Date.from(Instant.EPOCH);
    private Boolean released;
    private Integer projectId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public boolean isEpochReleaseDate() {
        return Instant.EPOCH.equals(releaseDate.toInstant());
    }

    public int getMark() {
        if (isEpochReleaseDate() || released == null) {
            //none
            return 0;
        }

        final LocalDate today = LocalDate.now();
        final LocalDate nextMonth = LocalDate.now().plusMonths(1);
        final LocalDate rDate = releaseDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (Boolean.TRUE.equals(released)) {
            //released
            return 1;
        } else {
            if (rDate.getMonthValue() == nextMonth.getMonthValue() && rDate.getYear() == nextMonth.getYear()) {
                //release next month
                return 2;
            }

            if (rDate.getMonthValue() == today.getMonthValue() && rDate.getYear() == today.getYear()) {
                //release this month
                return 3;
            }

            if (rDate.isEqual(today)) {
                //release today
                return 4;
            }

            if (rDate.isAfter(today)) {
                //release in future
                return 5;
            }

            //release already late
            return 6;
        }
    }

    public Boolean getReleased() {
        return released;
    }

    public void setReleased(Boolean released) {
        this.released = released;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Version version = (Version) o;

        if (name != null ? !name.equals(version.name) : version.name != null)
            return false;
        if (description != null ? !description.equals(version.description) : version.description != null)
            return false;
        if (releaseDate != null ? !releaseDate.equals(version.releaseDate) : version.releaseDate != null)
            return false;
        if (released != null ? !released.equals(version.released) : version.released != null)
            return false;
        return projectId != null ? projectId.equals(version.projectId) : version.projectId == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (released != null ? released.hashCode() : 0);
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        return result;
    }
}
