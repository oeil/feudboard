package org.teknux.feudboard.model.view;

import org.teknux.feudboard.ws.model.IssuesSearch;

import java.util.List;


/**
 * @author Francois EYL
 */
public class IssueStatistics {

    private int totalCount = 0;

    private int bugTypeCount = 0;
    private int otherTypeCount = 0;

    private int newCount = 0;
    private int indeterminateCount = 0;
    private int doneCount = 0;

    public void parse(List<IssuesSearch.Issue> issues) {
        if (issues == null) {
            return;
        }

        totalCount = issues.size();

        issues.stream().forEach(issue -> {
            final IssuesSearch.Issue.Fields fields = issue.getFields();
            if (fields == null) {
                return;
            }

            if (fields.getIssuetype() != null) {
                final String type = fields.getIssuetype().getName();
                switch (type.toLowerCase()) {
                case IssuesSearch.Issue.Fields.IssueType.NAME_BUG:
                    bugTypeCount++;
                    break;
                default:
                    otherTypeCount++;
                }
            }

            if (fields.getStatus() != null && fields.getStatus().getStatusCategory() != null) {
                final String statusCategory = fields.getStatus().getStatusCategory().getKey();
                switch (statusCategory.toLowerCase()) {
                case IssuesSearch.Issue.Fields.Status.StatusCategory.KEY_DONE :
                    doneCount++;
                    break;
                case IssuesSearch.Issue.Fields.Status.StatusCategory.KEY_INDETERMINATE :
                    indeterminateCount++;
                    break;
                case IssuesSearch.Issue.Fields.Status.StatusCategory.KEY_NEW :
                    newCount++;
                    break;
                }
            }
        });
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getBugTypeCount() {
        return bugTypeCount;
    }

    public int getOtherTypeCount() {
        return otherTypeCount;
    }

    public int getNewCount() {
        return newCount;
    }

    public int getIndeterminateCount() {
        return indeterminateCount;
    }

    public int getDoneCount() {
        return doneCount;
    }
}
