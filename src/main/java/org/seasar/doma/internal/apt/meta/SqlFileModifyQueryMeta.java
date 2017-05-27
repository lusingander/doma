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
package org.seasar.doma.internal.apt.meta;

import java.util.List;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import org.seasar.doma.internal.apt.cttype.EntityCtType;
import org.seasar.doma.internal.apt.reflection.ModifyReflection;
import org.seasar.doma.jdbc.SqlLogType;

/**
 * @author taedium
 * 
 */
public class SqlFileModifyQueryMeta extends AbstractSqlFileQueryMeta {

    protected EntityCtType entityCtType;

    protected String entityParameterName;

    protected ModifyReflection modifyReflection;

    public SqlFileModifyQueryMeta(ExecutableElement method, TypeElement dao) {
        super(method, dao);
    }

    public EntityCtType getEntityCtType() {
        return entityCtType;
    }

    public void setEntityCtType(EntityCtType entityCtType) {
        this.entityCtType = entityCtType;
    }

    public String getEntityParameterName() {
        return entityParameterName;
    }

    public void setEntityParameterName(String entityParameterName) {
        this.entityParameterName = entityParameterName;
    }

    public ModifyReflection getModifyReflection() {
        return modifyReflection;
    }

    public void setModifyReflection(ModifyReflection modifyReflection) {
        this.modifyReflection = modifyReflection;
    }

    public int getQueryTimeout() {
        return modifyReflection.getQueryTimeoutValue();
    }

    public Boolean getIgnoreVersion() {
        return modifyReflection.getIgnoreVersionValue();
    }

    public Boolean getExcludeNull() {
        return modifyReflection.getExcludeNullValue();
    }

    public Boolean getSuppressOptimisticLockException() {
        return modifyReflection.getSuppressOptimisticLockExceptionValue();
    }

    public Boolean getIncludeUnchanged() {
        return modifyReflection.getIncludeUnchangedValue();
    }

    public List<String> getInclude() {
        return modifyReflection.getIncludeValue();
    }

    public List<String> getExclude() {
        return modifyReflection.getExcludeValue();
    }

    public SqlLogType getSqlLogType() {
        return modifyReflection.getSqlLogValue();
    }

    public boolean isPopulatable() {
        return entityCtType != null && queryKind == QueryKind.SQLFILE_UPDATE;
    }

    @Override
    public <R, P> R accept(QueryMetaVisitor<R, P> visitor, P p) {
        return visitor.visitSqlFileModifyQueryMeta(this, p);
    }
}
