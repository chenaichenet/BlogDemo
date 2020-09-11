/**
 * FileName: BlogService
 * Author:   嘉平十七
 * Date:     2020/8/19 17:40
 * Description:
 */
package com.chenet.blog.service;

import com.chenet.blog.domain.Blog;
import com.chenet.blog.domain.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);

    //搜索标题和内容
    Page<Blog> listBlog(String query, Pageable pageable);

    //搜索type
    Page<Blog> listBlogByType(String query, Pageable pageable);

    Blog saveBlog(Blog blog);

    List<Blog> listRecommendBlogTop(Integer size);  //推荐的博客列表

    Map<String,List<Blog>> archiveBlog();

    Long coutnBlog();

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

}
