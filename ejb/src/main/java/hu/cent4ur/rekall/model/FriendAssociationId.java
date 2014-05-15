// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.model;

import java.io.Serializable;

/**
 * Composite primary key for the <code>Friend</code> entity. It is attached to
 * the entity class using the <code>@IdClass</code> annotation.
 * 
 * @see Friend
 * @author cent4ur
 */
public class FriendAssociationId implements Serializable {

    private static final long serialVersionUID = 1L;

    private int markedUserFk;
    private int acceptedUserFk;

    @Override
    public int hashCode() {
        return (int)(markedUserFk + acceptedUserFk);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof FriendAssociationId) {
            FriendAssociationId otherId = (FriendAssociationId) object;
            return (otherId.markedUserFk == this.markedUserFk) &&
                    (otherId.acceptedUserFk == this.acceptedUserFk);
        }
        return false;
    }
}
