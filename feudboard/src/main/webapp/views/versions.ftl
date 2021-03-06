<#import "/views/layout/layout.ftl" as layout>

<@layout.layout>

<ol class="breadcrumb">
    <li><a href="${url("/projects")}">Projects</a></li>
    <li class="active"><a href="${url("/projects/${model.project.key}/versions")}">${model.project.name}</a></li>
</ol>

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
                <div class="panel-body project <#if version.released==true>version-done</#if> mark${version.mark}">
                    <span class="fa-stack fa-lg">
                        <i class="fa fa-circle fa-stack-2x"></i>
                        <i class="fa <#if version.mark==6>fa-fire<#elseif version.released==true>fa-anchor<#else>fa-ship</#if> fa-stack-1x fa-inverse"></i>
                    </span>
                    <a href="${url("/projects/${version.projectId?c}/versions/${version.name}")}" <#if version.released==true>class="done version-done" </#if> >${version.name}</a>
                    <#if version.releaseDate?? && !version.isEpochReleaseDate() ><span> (${version.releaseDate?date})</span></#if>
                </div>
            </div>
        </#list>
    </div>
</#if>

</@layout.layout>
