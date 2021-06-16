package org.jedy.phone;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Phone {

    String head;
    String body;
    String tail;

    public Phone(String head, String body, String tail) {
        this.head = head;
        this.body = body;
        this.tail = tail;
    }
}
