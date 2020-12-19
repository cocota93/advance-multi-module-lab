package org.jedy.oembed_core.service;

import lombok.RequiredArgsConstructor;
import org.jedy.oembed_core.domain.Oembed;
import org.jedy.oembed_core.repository.OembedRepository;
import org.jedy.system_core.global.error.exception.EntityNotFoundException;
import org.jedy.system_core.global.error.exception.ErrorCode;
import org.jedy.system_core.util.ConverterUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class OembedServiceImpl {

    private final OembedRepository oembedRepository;

    public Long create(String jsonString) throws IOException {
        Oembed oembed = ConverterUtil.convertJsonStringToObject(jsonString, Oembed.class);
        return oembedRepository.save(oembed).getId();
    }

    public Oembed findById(Long createId) {
        return oembedRepository.findById(createId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.ENTITY_NOT_FOUND.getMessage() + " createdId : " + createId));
    }
}
