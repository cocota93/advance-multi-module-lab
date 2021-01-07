package org.jedy.config;

import org.springframework.restdocs.snippet.Attributes;

import static org.springframework.restdocs.snippet.Attributes.key;

public class DocumentFormatGenerator {

    public static Attributes.Attribute getDateFormat() {
        return key("format").value("yyyy-MM-dd");
    }
}