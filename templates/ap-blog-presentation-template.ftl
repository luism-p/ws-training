<hr />
<#if !entries?has_content>
	<#-- Insert 01-no-results-message here -->
	<#if !themeDisplay.isSignedIn()>
		${renderRequest.setAttribute("PORTLET_CONFIGURATOR_VISIBILITY", true)}
	</#if>
	<div class="alert alert-info">
		<@liferay_ui["message"] key="These are not the blogs you are looking for." />
	</div>
	
<#else>
	<div class="row">
		<#list entries as curEntry>
		<#-- Insert 02-asset-renderer here -->
		<#assign
			curEntry = curEntry
			assetRenderer = curEntry.getAssetRenderer()
			blogEntry = assetRenderer.getAssetObject()
			entryTitle = htmlUtil.escape(assetRenderer.getTitle(locale))
			viewURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry)
		/>
		<div class="col-md-4 entry-card lfr-asset-item tmpCustom">
			<@clay["image-card"]
    			imageSrc="${blogEntry.getCoverImageURL(themeDisplay)}"
    			href="${viewURL}"
    			title="${entryTitle}"
    			subtitle="${blogEntry.getDisplayDate()?date}"/>
		</div>
	    </#list>
	</div>
</#if>

<#--no funciona con la paginación -->
<style>
.maxheingt{
	max-height:100%;
}
</style>
<script>
const deleteFluid = function(){
	const imgs = $(".row .entry-card.tmpCustom img");

	imgs.each((index, node) => {
		if($(node).hasClass("aspect-ratio-item-fluid")){
			$(node).removeClass("aspect-ratio-item-fluid");
			$(node).addClass("maxheingt");
		}
	})
}
AUI().ready(deleteFluid);
</script>