package com.y.community.service;

import com.y.community.dto.PaginationDTO;
import com.y.community.dto.QuestionDTO;
import com.y.community.mapper.QuestionMapper;
import com.y.community.mapper.UserMapper;
import com.y.community.model.Question;
import com.y.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    // 执行 user 和 question 在 questionDTO上的匹配
    public PaginationDTO list(Integer page, Integer size) {

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount, page);
        Integer totalPage;

        // 确保查询不超过范围
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            // 整合 user 和 question 两个mapper
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
    return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);

        // 确保查询不超过范围
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            // 整合 user 和 question 两个mapper
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            // 创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionMapper.creat(question);
//            question.setViewCount(0);
//            question.setLikeCount(0);
//            question.setCommentCount(0);
//            question.setSticky(0);
        } else {
            // 更新
            question.setGmtModified(System.currentTimeMillis());
//            question.setTitle(question.getTitle());
            questionMapper.update(question);
//            Question dbQuestion = questionMapper.selectByPrimaryKey(question.getId());
//            if (dbQuestion == null) {
//                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
//            }
//
//            if (dbQuestion.getCreator().longValue() != question.getCreator().longValue()) {
//                throw new CustomizeException(CustomizeErrorCode.INVALID_OPERATION);
//            }

//            Question updateQuestion = new Question();
//
//            updateQuestion.setDescription(question.getDescription());
//            updateQuestion.setTag(question.getTag());
////            QuestionExample example = new QuestionExample();
//            example.createCriteria()
//                    .andIdEqualTo(question.getId());
//            int updated = questionMapper.updateByExampleSelective(updateQuestion, example);
//            if (updated != 1) {
//                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
//            }
        }
    }

}
