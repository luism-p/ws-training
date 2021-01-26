<hr />
<#if !entries?has_content>
	<#if !themeDisplay.isSignedIn()>
		${renderRequest.setAttribute("PORTLET_CONFIGURATOR_VISIBILITY", true)}
	</#if>
	<div class="alert alert-info">
		<@liferay_ui["message"] key="These are not the blogs you are looking for." />
	</div>
	
<#else>
	<div class="row">
		<#list entries as curEntry>
		<#assign
			curEntry = curEntry
			assetRenderer = curEntry.getAssetRenderer()
			blogEntry = assetRenderer.getAssetObject()
			entryTitle = htmlUtil.escape(assetRenderer.getTitle(locale))
			viewURL = assetPublisherHelper.getAssetViewURL(renderRequest, renderResponse, curEntry)
		/>
		<div class="col-md-4 entry-card lfr-asset-item">
			<div class="card-type-asset form-check form-check-card form-check-top-left image-card">
				<div class="container-fluid container-fluid-max-sm">
					<div class="card card-type-asset">
						<div class="aspect-ratio card-item-first">
							<a href="${viewURL}">
								<img alt="Cover Image" class="aspect-ratio-item aspect-ratio-item-center-middle" src="${blogEntry.getCoverImageURL(themeDisplay)}" style="max-height:100%;">
							</a>
						</div>
					
						<div class="card-body">
							<div class="card-row">
								<div class="autofit-col autofit-col-expand">
									<h3 class="card-title">
										<a href="${viewURL}">${entryTitle}</a>
									</h3>
					
									<div class="card-subtitle" title="Author Action">${blogEntry.getDisplayDate()?date}</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	    </#list>
	</div>	
</#if>