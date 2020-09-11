/**
 * FileName: BlogRepository
 * Author:   嘉平十七
 * Date:     2020/8/19 17:42
 * Description:
 */
package com.chenet.blog.dao;

import com.chenet.blog.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    @Query("select b from Blog b where b.recommend=true")
    List<Blog> findTop(Pageable pageable);

    // select * from t_blog where title like '%query%' or...
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Query("select b from Blog b where b.type.name like ?1")
    Page<Blog> findByTypeName(String query,Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    //归档，对应sql语句：SELECT date_format(b.update_time,'%Y') as year from t_blog b GROUP BY year ORDER BY year DESC
    @Query("select function('date_format',b.updateTime,'%Y') as year from Blog b group by function('date_format',b.updateTime,'%Y') order by function('date_format',b.updateTime,'%Y') desc ")
    List<String> findGroupYears();

    //归档查询，对应sql：SELECT * FROM t_blog b where date_format(b.update_time,'%Y')='2020'
    @Query("select b from Blog b where function('date_format',b.updateTime,'%Y') = ?1")
    List<Blog> findByYear(String year);
}
