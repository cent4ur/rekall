// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a person's interests regarding tags.
 * 
 * @author cent4ur
 */
@Entity
@XmlRootElement
public class Interest extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "person_fk")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "tag_fk")
    private Tag tag;

    public Interest(Person person, Tag tag) {
        super();
        this.person = person;
        this.tag = tag;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
