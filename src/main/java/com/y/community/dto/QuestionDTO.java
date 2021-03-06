package com.y.community.dto;

import com.y.community.model.Question;
import com.y.community.model.User;
import lombok.Data;

@Data
// 整合 user 和 question 两个mapper
public class QuestionDTO {
    private Long id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;
    private Integer sticky;
}