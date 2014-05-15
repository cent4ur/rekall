// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.controller;

import hu.cent4ur.rekall.model.Person;
import hu.cent4ur.rekall.service.LoginService;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;

/**
 * Managing login.
 * 
 * @author cent4ur
 */
@Named
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(LoginController.class);

    @Inject
    private LoginService loginService;

    private String username;
    private String password;
    private Boolean loggedIn;
    private Person person;

    public LoginController() {
        logger.info("LoginController() - Initialization");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLoggedIn() {
        return loggedIn;
    }

    public Person getPerson() {
        return person;
    }

    /**
     * SHA-256 digest computation.
     * 
     * @param password
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    private byte[] getHash(String password)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        return digest.digest(password.getBytes("UTF-8"));
    }

    /**
     * Logging in a user. Checking the username and the SHA-256 hash of the
     * given password with the database. After successful authentication the
     * user is redirected to the main page.
     * 
     * @param event
     *            the activation of a user interface component
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public void login(ActionEvent event) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        logger.info("login() - Authenticating " + username);
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        loggedIn = false;

        logger.info("login() - Query user data.");
        String hash = new BigInteger(1, getHash(password)).toString(16);

        Person person = loginService.login(username, hash);

        Boolean registered = person != null;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = FacesContext.getCurrentInstance()
                .getApplication().getResourceBundle(facesContext, "bundle");

        if (registered == true) {
            loggedIn = true;

            ((HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false))
                    .setAttribute("username", username);
            try {
                FacesContext.getCurrentInstance().getExternalContext()
                        .redirect("index.xhtml");
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                        bundle.getString("Login.Welcome"), username);
                logger.info("login() - Redirecting to the main site.");
            } catch (IOException e) {
                logger.error("login() - Redirection failed.", e);
            }
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    bundle.getString("Login.Error"),
                    bundle.getString("Login.Invalid"));
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
    }

    /**
     * Logging out the user. Removes the <code>username</code> attribute from
     * the session and redirects to the <code>login</code> page.
     * 
     * @param event
     *            the activation of a user interface component
     */
    public void logout(ActionEvent event) {
        logger.info("logout() - Logging out user: " + username);

        loggedIn = false;
        person = null;

        FacesMessage msg = null;

        ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false))
                .setAttribute("username", null);

        try {
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("login.xhtml");

            msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    getBundle().getString("Logout.GoodBye"), username);

            logger.info("logout() - Redirecting to the login page.");
        } catch (IOException e) {
            logger.error("logout() - Redirection failed.", e);
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     * Registering a new user. Persisting the new user's name and password to
     * the database.
     * 
     * @param event
     *            the activation of a user interface component
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public void register(ActionEvent event) throws NoSuchAlgorithmException,
            UnsupportedEncodingException {
        logger.info("register() - Registering a new user: " + username);

        if (isValidName(username)) {
            String hash = new BigInteger(1, getHash(password)).toString(16);
            loginService.register(new Person(username, hash));
            login(event);
        } else {
            logger.error("register() - Username is not valid.");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,
                    getBundle().getString("Login.Error"),
                    getBundle().getString("Login.UsernameInvalid"));
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    /**
     * Checking whether username contains only valid characters.
     * 
     * @param name
     *            to be validated
     * @return true if the <code>username</code> is valid, else otherwise
     */
    private boolean isValidName(String name) {
        logger.info("isValidName() - Checking validity.");
        Pattern pattern = Pattern.compile("^[a-zA-Z]{3,10}$");
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    /**
     * Find the ResourceBundle defined in the application configuration
     * resources with the name <code>bundle</code>.
     * 
     * @return the application's bundle.
     */
    private ResourceBundle getBundle() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ResourceBundle bundle = FacesContext.getCurrentInstance()
                .getApplication().getResourceBundle(facesContext, "bundle");
        return bundle;
    }
}
