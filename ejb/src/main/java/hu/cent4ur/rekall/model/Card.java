// Copyright (c) 2014, the Rekall project authors. Please see the AUTHORS file
// for details. All rights reserved. Use of this source code is governed by a
// BSD-style license that can be found in the LICENSE file.
package hu.cent4ur.rekall.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents question-answer pairs.
 * 
 * @author cent4ur
 */
@Entity
@XmlRootElement
public class Card extends EntityBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private String question;

    @NotNull
    private String answer;

    @NotNull
    private Integer difficulty;

    public Card(String question, String answer, Integer difficulty) {
        this.question = question;
        this.answer = answer;
        this.difficulty = difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Q: " + question + ", A: " + answer + ", D: " + difficulty;
    }
}
