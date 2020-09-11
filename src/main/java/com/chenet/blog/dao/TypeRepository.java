/**
 * FileName: TypeRepository
 * Author:   嘉平十七
 * Date:     2020/8/19 14:47
 * Description:
 */
package com.chenet.blog.dao;

import com.chenet.blog.domain.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type,Long> {
    Type findByName(String name);

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
