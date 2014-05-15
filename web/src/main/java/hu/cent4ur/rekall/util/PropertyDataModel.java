// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.util;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

/**
 * Implementation of {@link ListDataModel} that wraps a <code>List</code> of
 * <code>Property</code> objects.
 * 
 * @author cent4ur
 */
public class PropertyDataModel extends ListDataModel<Property> implements
        SelectableDataModel<Property> {

    public PropertyDataModel() {
    }

    public PropertyDataModel(List<Property> property) {
        super(property);
    }

    @Override
    public Property getRowData(String rowKey) {
        List<Property> propertys = (List<Property>) getWrappedData();

        for (Property property : propertys) {
            if (property.getKey().equals(rowKey)) {
                return property;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Property property) {
        return property.getKey();
    }
}
