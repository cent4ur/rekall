//Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
//for details. All rights reserved. Use of this source code is governed by a
//BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents a person who can log in to the system and make modifications.
 * 
 * @author cent4ur
 */
@Entity
@NamedQueries({
        @NamedQuery(
            name = "Person.find",
            query = "SELECT p FROM Person p WHERE p.name = :name AND p.password = :password"),
})
public class Person extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 25)
    @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
    private String name;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "registration_date")
    private Date registrationDate;

    private String about;

    @OneToMany(mappedBy = "person")
    private List<Interest> interests;

    @OneToMany(mappedBy = "marker")
    private List<Friendship> markedUsers;

    @OneToMany(mappedBy = "accepter")
    private List<Friendship> acceptedUsers;

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

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public List<Friendship> getMarkedUsers() {
        return markedUsers;
    }

    public void setMarkedUsers(List<Friendship> markedUsers) {
        this.markedUsers = markedUsers;
    }

    public List<Friendship> getAcceptedUsers() {
        return acceptedUsers;
    }

    public void setAcceptedUsers(List<Friendship> acceptedUsers) {
        this.acceptedUsers = acceptedUsers;
    }
}
