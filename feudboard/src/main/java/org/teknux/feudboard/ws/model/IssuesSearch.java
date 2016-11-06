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
 * Describes Issues Search Result resource in JIRA.
 *
 * @author Francois EYL
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IssuesSearch extends BaseSearch {

    private List<Issue> issues;

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    /**
     * Issue JSON model object
     *
     * @author Francois EYL
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Issue {

        private Integer id;
        private String key;
        private Fields fields;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Fields getFields() {
            return fields;
        }

        public void setFields(Fields fields) {
            this.fields = fields;
        }

        /**
         * Fields JSON model object
         *
         * @author Francois EYL
         */
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Fields {

            private String summary;
            private String description;
            private List<Component> components;
            private IssueType issuetype;
            private Status status;

            public Status getStatus() {
                return status;
            }

            public void setStatus(Status status) {
                this.status = status;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public List<Component> getComponents() {
                return components;
            }

            public void setComponents(List<Component> components) {
                this.components = components;
            }

            public IssueType getIssuetype() {
                return issuetype;
            }

            public void setIssuetype(IssueType issuetype) {
                this.issuetype = issuetype;
            }

            /**
             * Component JSON model object
             *
             * @author Francois EYL
             */
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Component {

                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            /**
             * IssueType JSON model object
             *
             * @author Francois EYL
             */
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class IssueType {

                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }

            /**
             * Status JSON model object
             *
             * @author Francois EYL
             */
            @JsonIgnoreProperties(ignoreUnknown = true)
            public static class Status {

                private StatusCategory statusCategory;

                public StatusCategory getStatusCategory() {
                    return statusCategory;
                }

                public void setStatusCategory(StatusCategory statusCategory) {
                    this.statusCategory = statusCategory;
                }

                /**
                 * Status JSON model object
                 *
                 * @author Francois EYL
                 */
                @JsonIgnoreProperties(ignoreUnknown = true)
                public static class StatusCategory {

                    private String key;

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }
                }
            }
        }
    }
}
