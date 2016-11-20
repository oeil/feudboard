<#import "/views/layout/layout.ftl" as layout>

<@layout.layout>

<ol class="breadcrumb">
    <li class="active"><a href="#">Projects</a></li>
</ol>

<div class="row">
    <div class="col-lg-12 text-center">
        <div class="roadmap-title">
            <#if model.projects??>
                <h4><i class="fa fa-map-o feature" aria-hidden="true"></i> Projects</h4>
            <#else>
                <h4>No Projects</h4>
            </#if>
        </div>
    </div>
</div>

<#if model.projects??>
    <div class="container">
        <#list model.projects?sort_by("name") as project>
            <div class="panel panel-default project">
                <div class="panel-body project">
                    <span class="fa-stack fa-lg">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa fa-diamond fa-stack-1x fa-inverse"></i>
                    </span>
                    <a href="${url("/projects/${project.key}/versions")}">${project.name}</a>
                </div>
            </div>
        </#list>
    </div>
</#if>

</@layout.layout>
