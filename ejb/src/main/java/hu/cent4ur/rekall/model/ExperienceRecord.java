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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@Table(name = "experience_record",
    uniqueConstraints = {
            @UniqueConstraint(columnNames = { "person_fk", "record_date" })
    })
public class ExperienceRecord extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "person_fk")
    private Person person;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "record_date")
    private Date recordDate;

    @NotNull
    @Column(name = "experience_point")
    private Long experiencePoint;

    public ExperienceRecord(Person person, Date recordDate, Long experiencePoint) {
        this.person = person;
        this.recordDate = recordDate;
        this.experiencePoint = experiencePoint;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Long getExperiencePoint() {
        return experiencePoint;
    }

    public void setExperiencePoint(Long experiencePoint) {
        this.experiencePoint = experiencePoint;
    }
}
