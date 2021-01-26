<#if !entries?has_content>
    <#-- Insert 06-if-no-content-message here -->
    <#if themeDisplay.isSignedIn()>
        <div class="alert alert-info">
            <@liferay.language key="there-are-no-menu-items-to-display" />
        </div>
    </#if>
<#else>
    <#-- Insert 12-nav-menu-using-macro here -->
    <nav class="menubar menubar-transparent menubar-vertical-expand-md">
        <a aria-controls="navigationWDTExample" aria-expanded="false" class="menubar-toggler" data-toggle="collapse" href="#navigationWDTExample" role="button">
            ${themeDisplay.getLayout().getNameCurrentValue()}
    
            <@clay.icon symbol="caret-bottom" />
        </a>
    
        <div class="collapse menubar-collapse" id="navigationWDTExample">
            <ul class="nav nav-nested">
                <#list entries as navItem>
                    <@buildNavigation navItem=navItem />
                </#list>
            </ul>
        </div>
    </nav>

    <#-- insert 07-build-navigation-macro here -->
   <#macro buildNavigation navItem>
       <#-- Add 08-nav-item-assignments snippet here -->
       <#assign
           nav_item_css_class = "nav-link"
       />
       
       <#if navItem.isSelected()>
           <#assign
               nav_item_css_class = "${nav_item_css_class} active"
           />
       </#if>
   
       <li class="nav-item">
           <div class="d-inline-flex">
               <a class="${nav_item_css_class}" href="${navItem.getRegularURL()}">
                    ${navItem.getName()}
               </a>
   
           <#-- Add 09-children-menu-if-statement snippet here -->
           <#if navItem.hasBrowsableChildren()>
               <#assign randomNamespace = portalUtil.generateRandomKey(request, "expandCollapse") />
                   <a aria-controls="${randomNamespace}" aria-expanded="true" class="collapse-icon nav-link" data-toggle="collapse" href="#${randomNamespace}" role="button">
                       <#-- Add 10-opt-clay-symbol-macros snippet or type here -->
                       <span class="collapse-icon-closed">
                           <@clay.icon symbol="caret-right" />
                       </span>
                       <span class="collapse-icon-open">
                           <@clay.icon symbol="caret-bottom" />
                       </span>
                   </a>
               </div>
           
               <div class="collapse show" id="${randomNamespace}">
                   <ul class="nav nav-stacked">
                       <#-- Add 11-opt-nav-children-macro snippet or type here -->
                       <#list navItem.getBrowsableChildren() as navItemChild>
                           <@buildNavigation navItem=navItemChild />
                       </#list>
                   </ul>
               </div>
           <#else>
               </div>
           </#if>
       </li>
   </#macro>
</#if>