// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.controller;

import hu.cent4ur.rekall.util.Property;
import hu.cent4ur.rekall.util.PropertyDataModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.prefs.Preferences;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.log4j.Logger;

/**
 * Getting system information.
 * 
 * @author cent4ur
 */
@Named
@SessionScoped
public class AboutController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(AboutController.class);

    Properties systemProperties;

    Preferences rekallPreferences = Preferences.userRoot().node("hu.cent4ur.rekall");

    private String logData;

    private PropertyDataModel propertyDataModel;

    public AboutController() {
        systemProperties = System.getProperties();

        List<Property> list = new ArrayList<Property>();
        list.add(new Property("AboutTab.OsName",
                systemProperties.getProperty("os.name")));
        list.add(new Property("AboutTab.OsVersion",
                systemProperties.getProperty("os.version")));
        list.add(new Property("AboutTab.JavaVendor",
                systemProperties.getProperty("java.vendor")));
        list.add(new Property("AboutTab.JavaVersion",
                systemProperties.getProperty("java.version")));
        list.add(new Property("AboutTab.Classpath",
                systemProperties.getProperty("java.class.path")));
        list.add(new Property("AboutTab.UserName",
                systemProperties.getProperty("user.name")));
        list.add(new Property("AboutTab.InstallRoot",
                systemProperties.getProperty("com.sun.aas.installRoot")));
        list.add(new Property("AboutTab.InstanceRoot",
                systemProperties.getProperty("com.sun.aas.instanceRoot")));
        list.add(new Property("AboutTab.HostName",
                systemProperties.getProperty("com.sun.aas.hostName")));
        list.add(new Property("AboutTab.JavaRoot",
                systemProperties.getProperty("com.sun.aas.javaRoot")));
        list.add(new Property("AboutTab.ConfigName",
                systemProperties.getProperty("com.sun.aas.configName")));
        list.add(new Property("AboutTab.InstanceName",
                systemProperties.getProperty("com.sun.aas.instanceName")));
        list.add(new Property("AboutTab.DomainName",
                systemProperties.getProperty("com.sun.aas.domainName")));

        propertyDataModel = new PropertyDataModel(list);
        readLogFile();
    }

    public String getLogData() {
        return logData;
    }

    public void readLogFile() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(
                    rekallPreferences.get("logFilePath", "")));
            try {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    sb.append(line);
                    sb.append('\n');
                    line = br.readLine();
                }
                logData = sb.toString();
            } catch (IOException e) {
                logger.error("readLogFile() - Error while reading the file.", e);
            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
            logger.error("readLogFile() - Error while opening the file.", e);
        } catch (IOException e) {
            logger.error("readLogFile() - Error while closing the file.", e);
        }
    }

    public PropertyDataModel getPropertyDataModel() {
        return propertyDataModel;
    }

    public String getOsName() {
        return systemProperties.getProperty("os.name");
    }

    public String getOsVersion() {
        return systemProperties.getProperty("os.version");
    }

    public String getJavaVendor() {
        return systemProperties.getProperty("java.vendor");
    }

    public String getJavaVersion() {
        return systemProperties.getProperty("java.version");
    }

    public String getClasspath() {
        return systemProperties.getProperty("java.class.path");
    }

    public String getUsername() {
        return systemProperties.getProperty("user.name");
    }

    public String getInstallRoot() {
        return systemProperties.getProperty("com.sun.aas.installRoot");
    }

    public String getInstanceRoot() {
        return systemProperties.getProperty("com.sun.aas.instanceRoot");
    }

    public String getHostName() {
        return systemProperties.getProperty("com.sun.aas.hostName");
    }

    public String getJavaRoot() {
        return systemProperties.getProperty("com.sun.aas.javaRoot");
    }

    public String getConfigName() {
        return systemProperties.getProperty("com.sun.aas.configName");
    }

    public String getInstanceName() {
        return systemProperties.getProperty("com.sun.aas.instanceName");
    }

    public String getDomainName() {
        return systemProperties.getProperty("com.sun.aas.domainName");
    }
}
