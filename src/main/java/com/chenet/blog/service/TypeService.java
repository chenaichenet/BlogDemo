/**
 * FileName: TypeService
 * Author:   嘉平十七
 * Date:     2020/8/19 14:44
 * Description: 分类
 */
package com.chenet.blog.service;

import com.chenet.blog.domain.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    Type updateType(Long id,Type type);

    void deleteType(Long id);
}
