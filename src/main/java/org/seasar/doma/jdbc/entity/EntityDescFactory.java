/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.doma.jdbc.entity;

import java.lang.reflect.Method;

import org.seasar.doma.DomaIllegalArgumentException;
import org.seasar.doma.DomaNullPointerException;
import org.seasar.doma.Entity;
import org.seasar.doma.internal.Conventions;
import org.seasar.doma.internal.WrapException;
import org.seasar.doma.internal.util.ClassUtil;
import org.seasar.doma.internal.util.MethodUtil;
import org.seasar.doma.jdbc.ClassHelper;
import org.seasar.doma.message.Message;

/**
 * A factory for entity descriptions.
 */
public final class EntityDescFactory {

    /**
     * Returns the entity description.
     * 
     * @param <E>
     *            the entity type
     * @param entityClass
     *            the entity class
     * @param classHelper
     *            the class helper
     * @return the entity description
     * @throws DomaNullPointerException
     *             if any arguments are {@code null}
     * @throws DomaIllegalArgumentException
     *             if the entity class is not annotated with the {@link Entity}
     *             annotation
     * @throws EntityDescNotFoundException
     *             if the entity description is not found
     */
    public static <E> EntityDesc<E> getEntityDesc(Class<E> entityClass, ClassHelper classHelper) {
        if (entityClass == null) {
            throw new DomaNullPointerException("entityClass");
        }
        if (classHelper == null) {
            throw new DomaNullPointerException("classHelper");
        }
        if (!entityClass.isAnnotationPresent(Entity.class)) {
            throw new DomaIllegalArgumentException("entityClass",
                    Message.DOMA2206.getMessage("entityClass"));
        }
        String entityDescClassName = Conventions.createDescClassName(entityClass.getName());
        try {
            Class<E> clazz = classHelper.forName(entityDescClassName);
            Method method = ClassUtil.getMethod(clazz, "getSingletonInternal");
            return MethodUtil.invoke(method, null);
        } catch (WrapException e) {
            throw new EntityDescNotFoundException(e.getCause(), entityClass.getName(),
                    entityDescClassName);
        } catch (Exception e) {
            throw new EntityDescNotFoundException(e.getCause(), entityClass.getName(),
                    entityDescClassName);
        }
    }
}