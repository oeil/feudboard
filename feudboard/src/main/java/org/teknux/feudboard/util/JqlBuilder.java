package org.teknux.feudboard.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


/**
 * Utility JQL builder to provide a fluent way of creating a search JQL query being type safe.
 * <p>
 *     This builder is not thread-safe
 * </p>
 */
public class JqlBuilder {

    private String project;
    private String fixVersion;
    private Set<String> issuesTypes;

    /**
     * Get a new instance of the builder
     *
     * @return
     */
    public static JqlBuilder get() {
        return new JqlBuilder();
    }

    /**
     * Sets the JIRA project key
     *
     * @param project
     * @return
     */
    public JqlBuilder project(String project) {
        this.project = project;
        return this;
    }

    /**
     * Sets the fix version
     *
     * @param version
     * @return
     */
    public JqlBuilder fixVersion(String version) {
        this.fixVersion = version;
        return this;
    }

    public void initIssueTypes() {
        if (this.issuesTypes == null) {
            this.issuesTypes = new HashSet<String>();
        }
    }

    /**
     * Adds a JIRA Issue Type
     *
     * @param type
     * @return
     */
    public JqlBuilder issueType(String type) {
        initIssueTypes();
        this.issuesTypes.add(type);
        return this;
    }

    /**
     * Adds a collection of JIRA Issue Types
     *
     * @param types
     * @return
     */
    public JqlBuilder issueTypes(Collection<String> types) {
        initIssueTypes();
        this.issuesTypes.addAll(types);
        return this;
    }

    /**
     * Build the final stringify JQL query
     *
     * @return
     */
    public String build() {
        String jql = "";

        // project
        if (project != null && !project.isEmpty()) {
            jql += "project='" + project + "'";
        }

        if (fixVersion != null && !fixVersion.isEmpty()) {
            jql += jql.isEmpty() ? "" : " AND ";
            jql += "fixVersion='" + fixVersion + "'";

        }

        // issue types
        if (issuesTypes != null && !issuesTypes.isEmpty()) {
            String jqlTypes = "";
            for (String type : issuesTypes) {
                if (!jqlTypes.isEmpty()) {
                    jqlTypes += " OR ";
                }
                jqlTypes += "type='" + type + "'";
            }

            if (issuesTypes.size() > 1) {
                jqlTypes = "(" + jqlTypes + ")";
            }

            jql += jql.isEmpty() ? jqlTypes : " AND " + jqlTypes;
        }

        return jql;
    }
}