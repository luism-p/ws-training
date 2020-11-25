Implement Assignment Local Service
===
> ### Exercise Goals
>
> - Implement addAssignment()
> - Implement updateAssignment()
> - Implement the finder methods
> - Silence generated methods
> - Do a final code review
> - Rebuild the service


### Implement `addAssignment()`
Before implementing the method for adding assignments, open the local service base class `AssignmentLocalServiceBaseImpl` and take a look at the generated `addAssigment()` method. This method doesn't automatically generate an ID, set the audit fields (like creation or modification date), or validate the entity:
```java
@Indexable(type = IndexableType.REINDEX)
@Override
public Assignment addAssignment(Assignment assignment) {
    assignment.setNew(true);

    return assignmentPersistence.update(assignment);
}
```

Create an overload for `addAssignment()` to take care of these tasks:

1. **Open** the `AssignmentLocalServiceImpl`. The empty class looks like this:
    ```java
     /**
      * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
      *
      * This library is free software; you can redistribute it and/or modify it under
      * the terms of the GNU Lesser General Public License as published by the Free
      * Software Foundation; either version 2.1 of the License, or (at your option)
      * any later version.
      *
      * This library is distributed in the hope that it will be useful, but WITHOUT
      * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
      * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
      * details.
      */
    
     package com.liferay.training.gradebook.service.impl;
    
     import com.liferay.portal.aop.AopService;
     import com.liferay.training.gradebook.service.base.AssignmentLocalServiceBaseImpl;
    
     import org.osgi.service.component.annotations.Component;
    
     /**
      * The implementation of the assignment local service.
      *
      * <p>
      * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.training.gradebook.service.AssignmentLocalService</code> interface.
      *
      * <p>
      * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
      * </p>
      *
      * @author Brian Wing Shun Chan
      * @see AssignmentLocalServiceBaseImpl
      */
     @Component(
         property = "model.class.name=com.liferay.training.gradebook.model.Assignment",
         service = AopService.class
     )
     public class AssignmentLocalServiceImpl extends AssignmentLocalServiceBaseImpl {
    
         /*
          * NOTE FOR DEVELOPERS:
          *
          * Never reference this class directly. Use <code>com.liferay.training.gradebook.service.AssignmentLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.training.gradebook.service.AssignmentLocalServiceUtil</code>.
          */
     }
     ```
2. **Implement** the `addAssignment()` in the class as follows:
    
    ```java
     public Assignment addAssignment(long groupId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Date dueDate, ServiceContext serviceContext)
         throws PortalException {
    
         // Get group and user.
    
         Group group = groupLocalService.getGroup(groupId);
    
         long userId = serviceContext.getUserId();
    
         User user = userLocalService.getUser(userId);
    
         // Generate primary key for the assignment.
    
         long assignmentId =
             counterLocalService.increment(Assignment.class.getName());
    
         // Create assigment. This doesn't yet persist the entity.
    
         Assignment assignment = createAssignment(assignmentId);
    
         // Populate fields.
    
         assignment.setCompanyId(group.getCompanyId());
         assignment.setCreateDate(serviceContext.getCreateDate(new Date()));
         assignment.setDueDate(dueDate);
         assignment.setDescriptionMap(descriptionMap);
         assignment.setGroupId(groupId);
         assignment.setModifiedDate(serviceContext.getModifiedDate(new Date()));
         assignment.setTitleMap(titleMap);
         assignment.setUserId(userId);
         assignment.setUserName(user.getScreenName());
    
         // Persist assignment to database.
    
         return super.addAssignment(assignment);
     }
     ```
### Implement updateAssignment
1. Create an overload for the `updateAssignment()`:
    ```java
     public Assignment updateAssignment(long assignmentId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap, Date dueDate, ServiceContext serviceContext)
         throws PortalException {
    
         // Get the Assignment by id.
    
         Assignment assignment = getAssignment(assignmentId);
    
         // Set updated fields and modification date.
    
         assignment.setModifiedDate(new Date());
         assignment.setTitleMap(titleMap);
         assignment.setDueDate(dueDate);
         assignment.setDescriptionMap(descriptionMap);
    
         assignment = super.updateAssignment(assignment);
    
         return assignment;
     }
   ```
   
### Implement the Finder Methods
Defining finders in `service.xml` automatically creates the corresponding methods in the persistence classes, but we cannot access those directly from the controller layer and have to implement façades in the service implementation class.

1. **Implement** the finder methods as follows:
    ```java
     public List<Assignment> getAssignmentsByGroupId(long groupId) {
    
         return assignmentPersistence.findByGroupId(groupId);
     }
    
     public List<Assignment> getAssignmentsByGroupId(long groupId, int start, int end) {
    
         return assignmentPersistence.findByGroupId(groupId, start, end);
     }
    
     public List<Assignment> getAssignmentsByGroupId(long groupId, int start, int end, OrderByComparator<Assignment> orderByComparator) {
    
         return assignmentPersistence.findByGroupId(groupId, start, end, orderByComparator);
     }
    
     public List<Assignment> getAssignmentsByKeywords(long groupId, String keywords, int start, int end, OrderByComparator<Assignment> orderByComparator) {
    
         return assignmentLocalService.dynamicQuery(getKeywordSearchDynamicQuery(groupId, keywords), start, end, orderByComparator);
     }
    
     public long getAssignmentsCountByKeywords(long groupId, String keywords) {
         return assignmentLocalService.dynamicQueryCount(getKeywordSearchDynamicQuery(groupId, keywords));
     }
    
     private DynamicQuery getKeywordSearchDynamicQuery(long groupId, String keywords) {
    
         DynamicQuery dynamicQuery = dynamicQuery().add(RestrictionsFactoryUtil.eq("groupId", groupId));
    
         if (Validator.isNotNull(keywords)) {
             Disjunction disjunctionQuery =RestrictionsFactoryUtil.disjunction();
    
             disjunctionQuery.add(RestrictionsFactoryUtil.like("title", "%" + keywords + "%"));
             disjunctionQuery.add(RestrictionsFactoryUtil.like("description", "%" + keywords + "%"));
             dynamicQuery.add(disjunctionQuery);
         }
    
         return dynamicQuery;
     }
   ```
>For the sake of this exercise, we introduced a custom `getAssignmentsByKeywords()` method here, which we will use on the user interface later for searching. This method is using Dynamic Queries, which allow you to query the database with custom SQL. Note that this specific query wouldn't work well with localized fields, which are stored in xml.

### "Silence" the Generated Method
Sometimes it's practical to silence generated methods to ensure correct API usage. Override and "silence" the generated `addAssignment()` and `updateAssignment()` method signatures, which we replaced with our overrides before:

```java
@Override
public Assignment addAssignment(Assignment assignment) {
    throw new UnsupportedOperationException("Not supported.");
}

@Override
public Assignment updateAssignment(Assignment assignment) {
    throw new UnsupportedOperationException("Not supported.");
}
```

### Do a Final Code Review
1. Resolve missing imports.
2. Fix indents and spacing by using automatic code formatting.
The final `AssignmentLocalServiceImpl.java` class will look like this:
```java
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.training.gradebook.service.impl;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.base.AssignmentLocalServiceBaseImpl;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * The implementation of the assignment local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * <code>com.liferay.training.gradebook.service.AssignmentLocalService</code>
 * interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssignmentLocalServiceBaseImpl
 */
@Component(
    property = "model.class.name=com.liferay.training.gradebook.model.Assignment", 
    service = AopService.class
)
public class AssignmentLocalServiceImpl extends AssignmentLocalServiceBaseImpl {

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. Use
     * <code>com.liferay.training.gradebook.service.AssignmentLocalService</code>
     * via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use
     * <code>com.liferay.training.gradebook.service.AssignmentLocalServiceUtil</code
     * >.
     */

    public Assignment addAssignment(long groupId, Map<Locale, String> titleMap, Map<Locale, String> descriptionMap,
            Date dueDate, ServiceContext serviceContext) throws PortalException {

        // Get group and user.

        Group group = groupLocalService.getGroup(groupId);

        long userId = serviceContext.getUserId();

        User user = userLocalService.getUser(userId);

        // Generate primary key for the assignment.

        long assignmentId = counterLocalService.increment(Assignment.class.getName());

        // Create assigment. This doesn't yet persist the entity.

        Assignment assignment = createAssignment(assignmentId);

        // Populate fields.

        assignment.setCompanyId(group.getCompanyId());
        assignment.setCreateDate(serviceContext.getCreateDate(new Date()));
        assignment.setDueDate(dueDate);
        assignment.setDescriptionMap(descriptionMap);
        assignment.setGroupId(groupId);
        assignment.setModifiedDate(serviceContext.getModifiedDate(new Date()));
        assignment.setTitleMap(titleMap);
        assignment.setUserId(userId);
        assignment.setUserName(user.getScreenName());

        // Persist assignment to database.

        return super.addAssignment(assignment);
    }

    public Assignment updateAssignment(long assignmentId, Map<Locale, String> titleMap,
            Map<Locale, String> descriptionMap, Date dueDate, ServiceContext serviceContext) throws PortalException {

        // Get the Assignment by id.

        Assignment assignment = getAssignment(assignmentId);

        // Set updated fields and modification date.

        assignment.setModifiedDate(new Date());
        assignment.setTitleMap(titleMap);
        assignment.setDueDate(dueDate);
        assignment.setDescriptionMap(descriptionMap);

        assignment = super.updateAssignment(assignment);

        return assignment;
    }

    public List<Assignment> getAssignmentsByGroupId(long groupId) {

        return assignmentPersistence.findByGroupId(groupId);
    }

    public List<Assignment> getAssignmentsByGroupId(long groupId, int start, int end) {

        return assignmentPersistence.findByGroupId(groupId, start, end);
    }

    public List<Assignment> getAssignmentsByGroupId(long groupId, int start, int end,
            OrderByComparator<Assignment> orderByComparator) {

        return assignmentPersistence.findByGroupId(groupId, start, end, orderByComparator);
    }

    public List<Assignment> getAssignmentsByKeywords(
        long groupId, String keywords, int start, int end,
        OrderByComparator<Assignment> orderByComparator) {

        return assignmentLocalService.dynamicQuery(
            getKeywordSearchDynamicQuery(groupId, keywords), start, end,
            orderByComparator);
    }

    public long getAssignmentsCountByKeywords(long groupId, String keywords) {
        return assignmentLocalService.dynamicQueryCount(
            getKeywordSearchDynamicQuery(groupId, keywords));
    }

    private DynamicQuery getKeywordSearchDynamicQuery(
        long groupId, String keywords) {

        DynamicQuery dynamicQuery = dynamicQuery().add(
            RestrictionsFactoryUtil.eq("groupId", groupId));

        if (Validator.isNotNull(keywords)) {
            Disjunction disjunctionQuery =
                RestrictionsFactoryUtil.disjunction();

            disjunctionQuery.add(
                RestrictionsFactoryUtil.like("title", "%" + keywords + "%"));
            disjunctionQuery.add(
                RestrictionsFactoryUtil.like(
                    "description", "%" + keywords + "%"));
            dynamicQuery.add(disjunctionQuery);
        }

        return dynamicQuery;
    }

    @Override
    public Assignment addAssignment(Assignment assignment) {

        throw new UnsupportedOperationException("Not supported.");
    }

    @Override
    public Assignment updateAssignment(Assignment assignment) {

        throw new UnsupportedOperationException("Not supported.");
    }

}
```
### Rebuild the Service
1. Run the buildService Gradle task to regenerate the service.