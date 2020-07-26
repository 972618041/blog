package com.serend.blog.service;

import com.serend.blog.po.Blog;
import com.serend.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);
    Blog getAndConvert(Long id);
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlog(Pageable pageable,Long tagId);
    Page<Blog> listBlog(Pageable pageable,String query);
    Blog save(Blog blog);
    Blog updateBlog(Long id,Blog blog);
    List<Blog> listRecommendBlogTop(Integer size);
    Map<String, List<Blog>> archiveBlog();
    Long countBlog();
    void deleteBlog(Long id);
}
