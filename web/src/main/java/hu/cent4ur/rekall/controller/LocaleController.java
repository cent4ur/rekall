// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.apache.log4j.Logger;

/**
 * Managing locale changes.
 * 
 * @author cent4ur
 */
@Named
@SessionScoped
public class LocaleController implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LocaleController.class);
    private Locale locale;
    private HashMap<String, Locale> locales;

    @PostConstruct
    public void init() {
        locales = new LinkedHashMap<String, Locale>();
        locales.put("English", new Locale("en"));
        locales.put("Magyar", new Locale("hu"));

        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        if (!locales.containsValue(locale)) {
            logger.info("init() - Unknown locale, setting English.");
            locale = locales.get("English");
            FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        }
        logger.info("init() - Current locale: " + locale.getLanguage());
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public HashMap<String, Locale> getLocales() {
        return locales;
    }

    public void changeLocale() {
        logger.info("changeLanguage() - New locale: " + locale.getLanguage());
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
