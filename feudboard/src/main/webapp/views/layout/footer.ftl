<footer class="text-center">
    <span>
    	<#assign applicationProperties = statics["org.teknux.feudboard.util.ApplicationProperties"]>
		Built with <i class="fa fa-heart" aria-hidden="true"></i> in Nancy · Powered By <a class="brand-font" href="http://jetty-bootstrap.io">Feudboard</a> v${applicationProperties.getInstance().getProperty(applicationProperties.APPLICATION_VERSION_KEY)}
    </span>
</footer>
