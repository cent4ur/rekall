// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * Users participating in chat.
 *
 * @author cent4ur
 */
@Named
@SessionScoped
public class ChatUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> users;

    @PostConstruct
    public void init() {
        this.users = new ArrayList<String>();
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    public void addUser(String user) {
        this.users.add(user);
    }

    public void removeUser(String user) {
        this.users.remove(user);
    }

    public boolean contains(String user) {
        return this.users.contains(user);
    }
}
