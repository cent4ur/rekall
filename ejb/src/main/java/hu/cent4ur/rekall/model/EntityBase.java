// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

/**
 * Common attributes for persistent objects in the domain. All new JPA entities
 * should extend this class.
 * 
 * @author cent4ur
 */
@MappedSuperclass
public abstract class EntityBase implements Persistable {

    @Id
    @NotNull
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "REKALL_SEQUENCE")
    @SequenceGenerator(name = "REKALL_SEQUENCE", allocationSize = 1)
    private Integer id;

    @Version
    @NotNull
    @Column(columnDefinition = "NUMBER(10) DEFAULT 1")
    private Integer version = 1;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getVersion() {
        return version;
    }

    @Override
    public void setVersion(Integer version) {
        this.version = version;
    }
}
