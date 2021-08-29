package com.y.community.mapper;

import com.y.community.model.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);

//    int incCommentCount(Question record);
//
//    List<Question> selectRelated(Question question);
//
//    Integer countBySearch(QuestionQueryDTO questionQueryDTO);
//
//    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
//
//    List<Question> selectSticky();
}