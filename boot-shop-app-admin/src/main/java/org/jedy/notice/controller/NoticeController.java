package org.jedy.notice.controller;

import lombok.RequiredArgsConstructor;
import org.jedy.notice.domain.Notice;
import org.jedy.notice.dto.NoticeSearchCondition;
import org.jedy.notice.service.NoticePagingServiceImpl;
import org.jedy.notice.service.NoticeServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    private final NoticeServiceImpl noticeService;
    private final NoticePagingServiceImpl noticePagingService;

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
        Notice notice = noticeService.findById(noticeId);
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
    public String list(Model model, Pageable pageable) {
        NoticeSearchCondition condition = new NoticeSearchCondition();
        condition.setDeleted(false);

        Page<Notice> pageList = noticePagingService.searchPaging(condition, pageable);
        model.addAttribute("pageList", pageList);

        return "/notice/list";
    }

}
