//Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
//for details. All rights reserved. Use of this source code is governed by a
//BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Represents a person who can log in to the system and make modifications.
 * 
 * @author cent4ur
 */
@Entity
@NamedQueries({
        @NamedQuery(
            name = "Person.find",
            query = "SELECT p FROM Person p WHERE p.name = :name AND p.password = :password") })
public class Person extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String password;

    private Integer experiencePoint;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    private String about;

    @OneToMany
    private List<Friend> markedUsers;

    @OneToMany
    private List<Friend> acceptedUsers;

    public Person() {
    }

    public Person(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getExperiencePoint() {
        return experiencePoint;
    }

    public void setExperiencePoint(Integer experiencePoint) {
        this.experiencePoint = experiencePoint;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
