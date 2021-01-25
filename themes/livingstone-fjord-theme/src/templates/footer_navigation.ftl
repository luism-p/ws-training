<ul class="flex-row nav" role="menubar">
	<#-- Insert snippet 04-footer-nav-sign-in here -->
<#if !is_signed_in>
	<li class="nav-item" role="presentation">
		<a class="nav-link" data-redirect="${is_login_redirect_required?string}" href="${sign_in_url}" id="sign-in" rel="nofollow">
			${sign_in_text}
		</a>
	</li>
</#if>
	<#-- Insert snippet 05-footer-nav-menu here -->
	<#foreach nav_item in nav_items>
		<li class="nav-item" role="presentation">
			<a class="nav-link" aria-labelledby="layout_${nav_item.getLayoutId()}" href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem">
				${nav_item.getName()}
			</a>
		</li>
	</#foreach>
</ul>