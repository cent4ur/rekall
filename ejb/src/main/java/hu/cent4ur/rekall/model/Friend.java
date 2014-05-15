// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Represents connections (friendships) between users.
 * 
 * @author cent4ur
 */
@Entity
@IdClass(FriendAssociationId.class)
public class Friend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private int markedUserFk;

    @Id
    private int acceptedUserFk;

    @ManyToOne
    @PrimaryKeyJoinColumn(
        name = "MARKED_USER_FK",
        referencedColumnName = "ID")
    private Person markedUser;

    @ManyToOne
    @PrimaryKeyJoinColumn(
        name = "ACCEPTED_USER_FK",
        referencedColumnName = "ID")
    private Person acceptedUser;

    @Temporal(TemporalType.DATE)
    Date markedDate;

    @Temporal(TemporalType.DATE)
    Date acceptedDate;

    public Person getMarkedUser() {
        return markedUser;
    }

    public void setMarkedUser(Person markedUser) {
        this.markedUser = markedUser;
    }

    public Person getAcceptedUser() {
        return acceptedUser;
    }

    public void setAcceptedUser(Person acceptedUser) {
        this.acceptedUser = acceptedUser;
    }

    public Date getMarkedDate() {
        return markedDate;
    }

    public void setMarkedDate(Date date) {
        this.markedDate = date;
    }
}
