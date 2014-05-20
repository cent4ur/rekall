// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents the connection between cards and tags.
 * 
 * @author cent4ur
 */
@Entity
@XmlRootElement
@Table(name = "card_tag")
public class CardTag extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "card_fk")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "tag_fk")
    private Tag tag;

    public CardTag(Card card, Tag tag) {
        this.card = card;
        this.tag = tag;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
