<h1 class="text-center"><#-- Insert title data here --></h1>

<div class="container">
    <ul class="list-unstyled row">
        <li class="card col-md-6">
            <#-- Insert 01-div-header-and-body snippet here -->
            <@createCard text=Text1 image=Text1.Image1 link=Text1.Link1 />
        </li>
        <#-- Insert 04-top-row-second-column snippet here -->
        <li class="card col-md-6">
            <<@createCard text=Text2 image=Text2.Image2 link=Text2.Link2 />
        </li>
    </ul>
    <#-- Insert 05-second-row snippet here -->
    <ul class="list-unstyled row">
        <li class="card col-md-6">
            <@createCard text=Text3 image=Text3.Image3 link=Text3.Link3 />
        </li>
        <li class="card col-md-6">
            <@createCard text=Text4 image=Text4.Image4 link=Text4.Link4 />
        </li>
    </ul>
</div>

<#macro createCard text image link>
 <div class="card-type-asset">
                <div class="card-item-first aspect-ratio">
                    <#if image.getData()?? && image.getData() != "">
                        <a href="${image.getData()}">
                            <img class="aspect-ratio-item-fluid" ${languageUtil.format(locale, "download-x", "${text.getData()}", false)} src="${image.getData()}">
                        </a>
                    </#if>
                </div>
                <div class="card-body">
                    <h2 class="text-center">
                        <a class="item-one" href="${link.getFriendlyUrl()}">${text.getData()}</a>
                    </h2>
                </div>
            </div>
</#macro>