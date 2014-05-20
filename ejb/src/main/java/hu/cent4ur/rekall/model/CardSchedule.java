// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "card_schedule", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "card_fk", "person_fk" })
})
public class CardSchedule extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "card_fk")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "person_fk")
    private Person person;

    @NotNull
    @Column(name = "review_date")
    private Date reviewDate;

    public CardSchedule(Card card, Person person, Date reviewDate) {
        super();
        this.card = card;
        this.person = person;
        this.reviewDate = reviewDate;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }
}
