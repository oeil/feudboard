<#import "/views/layout/layout.ftl" as layout>

<@layout.layout>

    <ol class="breadcrumb">
        <li><a href="${url("/projects")}">Projects</a></li>
        <li ><a href="${url("/projects/${model.project.key}/versions")}">${model.project.name}</a></li>
        <li class="active"><a href="${url("/projects/${model.project.key}/versions/${model.version}")}">${model.version}</a></li>
    </ol>

    <div class="col-lg-12 text-center">
        <div class="roadmap-title">
            <#if model.version??>
                <h4><i class="fa fa-map-o feature" aria-hidden="true"></i> ${model.version}</h4>
            <#else>
                <h4>No Version</h4>
            </#if>
        </div>
    </div>

    <#if model.version??>
        <div class="container">

            <div class="row">
                <div class="col-md-6">
                    <#assign completion = (model.statistics.doneCount / model.statistics.totalCount) * 100>
                    <p class="text-left version-statistics">
                        Completion <span class="badge completion">${completion?string["0"]}%</span>
                    </p>
                </div>
                <div class="col-md-6">
                    <p class="text-right version-statistics">
                        Improvements <span class="badge improvements">${model.statistics.otherTypeCount}</span> Bugs <span class="badge bugs">${model.statistics.bugTypeCount}</span>
                    </p>
                </div>
            </div>

                <div class="panel-group roadmap" id="accordion-story" role="tablist" aria-multiselectable="true">
                    <#list model.issuesSearch.issues as issue>
                            <div class="panel panel-default roadmap">
                                <div class="panel-heading" role="tab" id="heading${issue.key}">
                                    <h4 class="panel-title roadmap">
                                        <#if issue.fields.issuetype.name=="Story">
                                            <i class="fa fa-star feature" aria-hidden="true"></i>
                                        <#elseif issue.fields.issuetype.name?contains("Bug")>
                                            <i class="fa fa-bug bug" aria-hidden="true"></i>
                                        <#else>
                                            <i class="fa fa-star-o feature" aria-hidden="true"></i>
                                        </#if>

                                        <a role="button" data-toggle="collapse" data-parent="#accordion-story" href="#collapse${issue.key}" aria-expanded="true" aria-controls="collapse${issue.key}" <#if issue.fields.status?? && issue.fields.status.statusCategory.key == 'done'>class="done"</#if>>
                                            ${issue.fields.summary}
                                        </a>
                                    </h4>

                                    <#if issue.fields.components??>
                                        <#list issue.fields.components as component>
                                            <span class="label label-default roadmap">${component.name}</span>
                                        </#list>
                                    </#if>
                                </div>
                                <div id="collapse${issue.key}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${issue.key}">
                                    <div class="panel-body roadmap">
                                        <a target="_blank" href="${model.baseJiraUrl+'/browse/'+issue.key}">${issue.key}</a>
                                        <br><br>
                                        <#if issue.fields.description??>
                                            ${markdown(issue.fields.description)}
                                        </#if>
                                    </div>
                                </div>
                            </div>
                    </#list>
                </div>

            </div>
        </div>
    </#if>

</@layout.layout>
