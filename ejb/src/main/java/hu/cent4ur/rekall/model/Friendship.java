// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Represents connections (friendships) between users.
 * 
 * @author cent4ur
 */
@Entity
public class Friendship extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(
        name = "MARKER_FK",
        referencedColumnName = "ID")
    private Person marker; // Source node.

    @ManyToOne
    @JoinColumn(
        name = "ACCEPTER_FK",
        referencedColumnName = "ID")
    private Person accepter; // Target node.

    @Temporal(TemporalType.DATE)
    Date created;

    public Friendship(Person marker, Person accepter, Date created) {
        this.marker = marker;
        this.accepter = accepter;
        this.created = created;
    }

    public Person getMarker() {
        return marker;
    }

    public void setMarker(Person marker) {
        this.marker = marker;
    }

    public Person getAccepter() {
        return accepter;
    }

    public void setAccepter(Person accepter) {
        this.accepter = accepter;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
