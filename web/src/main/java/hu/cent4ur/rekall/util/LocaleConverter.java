// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.util;

import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Custom converter for Locale. It is necessary when using Locale in
 * <code>SelectOneMenu</code>.
 * 
 * @author cent4ur
 */
@FacesConverter(value = "localeConverter")
public class LocaleConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        for (Locale locale : Locale.getAvailableLocales()) {
            if (locale.toString().equals(value)) {
                return locale;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        return value.toString();
    }
}
