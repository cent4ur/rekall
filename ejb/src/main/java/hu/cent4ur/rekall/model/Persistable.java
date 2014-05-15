// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.model;

/**
 * Common interface for <code>EntityBase</code>.
 * 
 * @see EntityBase
 * @author cent4ur
 */
public interface Persistable {

    public Integer getId();

    public void setId(Integer id);

    public Integer getVersion();

    public void setVersion(Integer version);

}
