<#macro layout>
    <#escape x as x?html>
	<!DOCTYPE html>
	<html lang="en">
		<head>
			<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
			<meta charset="utf-8" />
			<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
			<title>Feudboard</title>
            <link rel="icon" type="image/png" href="${url("static/favicon.ico")}" />

			<!--[if lt IE 9]>
				<script src="${url("/static/lib/html5shiv/html5shiv.min.js")}" type="text/javascript"></script>
			<![endif]-->

			<script src="${url("/static/lib/jquery/jquery-2.1.1.min.js")}" type="text/javascript"></script>
			<link href="${url("/static/lib/bootstrap/css/bootstrap.min.css")}" rel="stylesheet" type="text/css"/>
		  	<script src="${url("/static/lib/bootstrap/js/bootstrap.min.js")}" type="text/javascript"></script>
			<link href="${url("/static/css/styles.css")}" rel="stylesheet" type="text/css">
			<script src="${url("/static/js/scripts.js")}" type="text/javascript"></script>

            <link href="${url("/static/lib/font-awesome/css/font-awesome.min.css")}" rel="stylesheet" type="text/css"/>
		</head>
		<body>
		    <div class="wrap">

                <div>
                    <#include "/views/layout/header.ftl"/>
                </div>
                <div class="container">
                    <#include "/views/layout/messages.ftl"/>
                </div>
                <div class="spacer"></div>
                <div id="content">
                    <#nested/>
                </div>

                <div id="push"></div>
		    </div>
		    <div id="footer">
		    	<#include "/views/layout/footer.ftl"/>
		    </div>
		</body>
	</html>
    </#escape>
</#macro>
