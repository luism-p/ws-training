Create the Gradebook Web Module
===

> ### Exercise Goals
> - Create a Liferay MVC portlet module
> - Declare dependencies
> - Set portlet properties
> - Set the portlet name
> - Do a final code review
> - Deploy the module
> - Test the module

Over the next few exercises, we will create the user interface for the Gradebook application. We will be using coding conventions and patterns recommended for Liferay development, leveraging libraries, components, and high-level superclasses to remove the need for boilerplate coding.

We will use the Liferay MVC portlet as a portlet component. The portlet lifecycle and communication between the portlet back-end and user interface will be handled by MVC command components.

The user interface will be implemented with JSP technology. We will be using Liferay tag libraries, which both minimize the need for HTML coding and guarantee a [Twitter Bootstrap-based](https://getbootstrap.com/) responsive layout.

### Create a Liferay MVC Portlet Module
###### Option 1: Use the Command Line Blade Tools

1. **Open** command line shell in your Liferay Workspace modules/gradebook folder.
2. **Run** command:

    ```
    blade create -t mvc-portlet -p com.liferay.training.gradebook.web -c GradebookPortlet gradebook-web
    ```
   
3. **Run** Gradle refresh on the IDE.

###### Option 2: Use Developer Studio Wizard

1. **Launch** the *Liferay Module Project wizard* in Developesr Studio.
2. **Use** the following information for the first step:
    - **Project Name**: "gradebook-web"
    - **Use Default Location**: uncheck and browse to modules/gradebook subfolder
    - **Build Type**: Gradle
    - **Liferay Version**: 7.2
    - **Project Template**: mvc-portlet
3. **Click** Next, and use the following information for the second step:
    - **Component Class Name**: "Gradebook"
    - **Package Name**: "com.liferay.training.gradebook.web"
4. **Click** Finish to close the wizard.

### Declare Dependencies
We need to declare dependencies for the Gradebook service (API), Liferay Clay tag library, and Petra function utility:

Open the build.gradle of gradebook-web project.
Implement the new dependencies as follows:

 compileOnly group: 'com.liferay', name: 'com.liferay.petra.function'
 compileOnly group: 'com.liferay', name: 'com.liferay.frontend.taglib.clay'
 compileOnly project(":modules:gradebook:gradebook-api")
Notice here how we reference the API (gradebook-api) and not the implementation (gradebook-service).

What is Petra? If you developed for pre-7 Liferay, you probably remember the com.liferay.util.java utilities. The Petra library family contains the modularized and OSGi-ready versions of those utilities.

Set Portlet Properties
We'll have the following requirements for our portlet:

We don't want the Gradebook portlet to be instanceable, as its data needs to be scoped under a site.
We'd like the Gradebook portlet to appear in the Liferay Training Widgets category instead of the Sample category.
Let's change the portlet component properties to match these requirements:

Open the GradebookPortlet class.
Implement the changes to component properties as follows:
 "com.liferay.portlet.display-category=category.training",
 "com.liferay.portlet.instanceable=false",
Set the Portlet Name
It's a good practice to use a fully qualified name of the portlet class as the portlet identifier. We also have to update the name in our resource bundle (we'll discuss localization at later steps):

Open the class com.liferay.training.gradebook.web.constants.GradebookPortletKeys.
Update the portlet name constant as follows:
 public static final String Gradebook = "com_liferay_training_gradebook_web_portlet_GradebookPortlet";
Open the file src/main/resources/content/Language.properties.
Implement the contents as follows:
 javax.portlet.description.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook 
 javax.portlet.display-name.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
 javax.portlet.keywords.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
 javax.portlet.short-title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
 javax.portlet.title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
Do a Final Code Review
build.gradle

dependencies {
    // Clay taglib.

    compileOnly group: 'com.liferay', name: 'com.liferay.frontend.taglib.clay'

    // Needed for the Assignments Management Toolbar.

    compileOnly group: 'com.liferay', name: 'com.liferay.petra.function'

    compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
    compileOnly group: "com.liferay.portal", name: "com.liferay.util.taglib"
    compileOnly group: "javax.portlet", name: "portlet-api"
    compileOnly group: "javax.servlet", name: "javax.servlet-api"
    compileOnly group: "jstl", name: "jstl"
    compileOnly group: "org.osgi", name: "org.osgi.service.component.annotations"

    // Gradebook service.

    compileOnly project(":modules:gradebook:gradebook-api")
}
GradebookPortlet.java

package com.liferay.training.gradebook.web.portlet;

import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author liferay
 */
@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=category.training",
        "com.liferay.portlet.instanceable=false",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/view.jsp",
        "javax.portlet.name=" + GradebookPortletKeys.Gradebook,
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)
public class GradebookPortlet extends MVCPortlet {
}
GradebookPortletKeys.java

package com.liferay.training.gradebook.web.constants;

/**
 * @author liferay
 */
public class GradebookPortletKeys {

    public static final String Gradebook = "com_liferay_training_gradebook_web_portlet_GradebookPortlet";
}
Language.properties

javax.portlet.description.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
javax.portlet.display-name.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
javax.portlet.keywords.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
javax.portlet.short-title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
javax.portlet.title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
gradebook.caption=Hello from Gradebook!
Deploy the Module
Drag the gradebook-web onto the Liferay server to deploy the module.
You should see the following message in the log:
STARTED com.liferay.training.gradebook.web_1.0.0
Test the Module
Dev Studio's hot deploy feature allows us to see the changes on the user interface in almost real-time as we work with the code. Let's do a quick test to see how this feature works:

Open your browser to http://localhost:8080 and sign in.
Click the Add icon on the top right corner of the page.
Expand the category.training category in the Widgets menu.
Add the gradebook-web portlet on the page.
