<!DOCTYPE html>

<#include init />

<html class="${root_css_class}" dir="<@liferay.language key="lang.dir" />" lang="${w3c_language_id}">
	<head>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

		<title>${the_title} - ${company_name}</title>

		<meta content="initial-scale=1.0, width=device-width" name="viewport" />

		<@liferay_util["include"] page=top_head_include />
	</head>

	<body class="${css_class}">
		<@liferay_ui["quick-access"] contentId="#main-content" />

		<@liferay_util["include"] page=body_top_include />

		<@liferay.control_menu />

		<div class="mb-0 pt-0" id="wrapper">
			<#-- Insert snippet 01-portal-normal-header here -->
			<#if show_header>
				<#include "${full_templates_path}/header.ftl" />
			</#if>

			<#-- Insert snippet 02-portal-normal-main here -->
			<main id="content" role="main">
				<h1 class="hide-accessible">${the_title}</h1>
			
				<#if selectable>
					<@liferay_util["include"] page=content_include />
				<#else>
					${portletDisplay.recycle()}
			
					${portletDisplay.setTitle(the_title)}
			
					<@liferay_theme["wrap-portlet"] page="portlet.ftl">
						<@liferay_util["include"] page=content_include />
					</@>
				</#if>
			</main>
			
			<#-- Type footer.ftl include here -->
			<#if show_footer>
     			<#include "${full_templates_path}/footer.ftl" />
			</#if>
		</div>

		<@liferay_util["include"] page=body_bottom_include />

		<@liferay_util["include"] page=bottom_include />
	</body>
</html>