<#import "/views/layout/layout.ftl" as layout>

<@layout.layout>
<link href="${url("/static/css/roadmap.css")}" rel="stylesheet" type="text/css"/>

<div class="row">
    <div class="col-lg-12 text-center">
        <div class="roadmap-title">
            <#if model.versions??>
                <h4><i class="fa fa-map-o feature" aria-hidden="true"></i> Versions</h4>
            <#else>
                <h4>No Versions</h4>
            </#if>
        </div>
    </div>
</div>

<#if model.versions??>
    <div class="container">
        <#list model.versions?sort_by("releaseDate", "name")?reverse as version>
            <div class="panel panel-default project">
                <div class="panel-body project">
                    <span class="fa-stack fa-lg">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa fa-ship fa-stack-1x fa-inverse"></i>
                    </span>
                    <a href="${url("/projects/${version.projectId?c}/versions/${version.name}")}" <#if version.released==true>class="done version-done" </#if> >${version.name}</a>
                    <#if version.releaseDate?? && !version.isEpochReleaseDate() ><span> (${version.releaseDate?date})</span></#if>
                </div>
            </div>
        </#list>
    </div>
</#if>

</@layout.layout>
