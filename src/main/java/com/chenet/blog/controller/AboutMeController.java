/**
 * FileName: AboutMeController
 * Author:   嘉平十七
 * Date:     2020/8/31 15:19
 * Description:
 */
package com.chenet.blog.controller;

import com.chenet.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutMeController {
    @Autowired
    private BlogService blogService;

    @GetMapping("/about")
    public String about(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));   //8个推荐
        return "aboutme";
    }
}