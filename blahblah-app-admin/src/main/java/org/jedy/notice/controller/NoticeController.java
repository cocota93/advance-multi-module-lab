package org.jedy.notice.controller;

import lombok.RequiredArgsConstructor;
import org.jedy.member.dto.MemberResponse;
import org.jedy.member.dto.MemberSearchCondition;
import org.jedy.notice.dto.NoticeSearchCondition;
import org.jedy.notice.repository.query.NoticeQueryRepository;
import org.jedy.notice_core.domain.Notice;
import org.jedy.notice_core.repository.NoticeRepository;
import org.jedy.notice_core.service.NoticeServiceImpl;
import org.jedy.system_core.global.error.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeRepository noticeRepository;
    private final NoticeServiceImpl noticeService;
    private final NoticeQueryRepository noticeQueryRepository;

    @GetMapping("/create")
    public String create(){
        return "/notice/create";
    }

    @PostMapping("/create")
    public String create(String content){
        noticeService.create(content);
        return "redirect:/notice/list";
    }


    @GetMapping("/modify")
    public String modify(Model model, Long noticeId){
        Notice notice = noticeRepository.findById(noticeId).orElseThrow(() -> new EntityNotFoundException("이미 삭제된 글을 읽으려 시도합니다. noticeId : " + noticeId));
        model.addAttribute("notice",notice);
        return "/notice/modify";
    }

    @PostMapping("/modify")
    public String modify(Long noticeId, String content){
        noticeService.modify(noticeId,content);
        return "redirect:/notice/list";
    }

    @PostMapping("/delete")
    public String delete(Long noticeId){
        noticeService.delete(noticeId);
        return "redirect:/notice/list";
    }

    @GetMapping("/list")
    public String list(Model model, @RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "20") Integer size) {
        NoticeSearchCondition condition = new NoticeSearchCondition();
        condition.setDeleted(false);

        Page<Notice> pageList = noticeQueryRepository.searchPage(condition, page, size);
        model.addAttribute("pageList", pageList);

        return "/notice/list";
    }

}
