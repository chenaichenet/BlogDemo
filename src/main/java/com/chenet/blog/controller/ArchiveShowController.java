/**
 * FileName: ArchiveShowController
 * Author:   嘉平十七
 * Date:     2020/8/23 21:01
 * Description: 归档
 */
package com.chenet.blog.controller;

import com.chenet.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("archiveMap",blogService.archiveBlog());
        model.addAttribute("blogCount",blogService.coutnBlog());
        return "archives";
    }
}