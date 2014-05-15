// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.service;

import hu.cent4ur.rekall.model.Person;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

/**
 * Authenticating users.
 *
 * @author cent4ur
 */
@Stateless
public class LoginService {

    private static final Logger logger = Logger.getLogger(LoginService.class);

    @Inject
    private EntityManager entityManager;

    /**
     * Registering a new user. Persisting the new user's name and password to
     * the database.
     * 
     * @param person
     *            the user to register
     */
    public void register(Person person) {
        entityManager.persist(person);
    }

    /**
     * Logging in a user. Checking the username and the SHA-256 hash of the
     * given password with the database.
     * 
     * @param username
     *            the name of the user
     * @param hash
     *            the SHA-256 hash of the user's password
     */
    public Person login(String username, String hash) {
        TypedQuery<Person> query = entityManager.createNamedQuery(
                "Person.find", Person.class)
                .setParameter("name", username)
                .setParameter("password", hash);

        Person person = null;

        try {
            person = query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("login() - User is not authenticated");
        } catch (NonUniqueResultException e) {
            logger.error("login() - More than one result.", e);
        } catch (Exception e) {
            logger.error("login() - Exception.", e);
        }

        return person;
    }
}
