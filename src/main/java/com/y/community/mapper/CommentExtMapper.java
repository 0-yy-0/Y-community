package com.y.community.mapper;

import com.y.community.model.Comment;

public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}