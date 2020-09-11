/**
 * FileName: IndexController
 * Author:   嘉平十七
 * Date:     2020/8/19 10:02
 * Description:
 */
package com.chenet.blog.controller;


import com.chenet.blog.service.BlogService;
import com.chenet.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @GetMapping({"/","/index"})
    public String index(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6)); //前6个分类
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));   //8个推荐
        return "index";
    }

    @PostMapping("/search")
    public String search(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String query, Model model){
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));   //8个推荐
        return "search";
    }

    @GetMapping("/search/{typename}")
    public String searchbyname(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, @PathVariable String typename, Model model){
        model.addAttribute("page",blogService.listBlogByType("%"+typename+"%",pageable));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));   //8个推荐
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));   //8个推荐
        return "blog";
    }

    @GetMapping("/types")
    public String type(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        model.addAttribute("blogs",blogService.listBlog(pageable));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));   //8个推荐
        return "types";
    }
}