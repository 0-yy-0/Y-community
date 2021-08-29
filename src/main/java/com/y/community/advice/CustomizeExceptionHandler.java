package com.y.community.advice;

import com.alibaba.fastjson.JSON;
import com.y.community.exception.CustomizeErrorCode;
import com.y.community.exception.CustomizeException;
//import life.majiang.community.dto.ResultDTO;
//import life.majiang.community.exception.CustomizeErrorCode;
//import life.majiang.community.exception.CustomizeException;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;

// 处理上下文所有异常
@ControllerAdvice
//@Slf4j
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e, Model model, HttpServletRequest request, HttpServletResponse response) {
//        String contentType = request.getContentType();
//        if ("application/json".equals(contentType)) {
//            ResultDTO resultDTO;
//            // 返回 JSON
//            if (e instanceof CustomizeException) {
//                resultDTO = ResultDTO.errorOf((CustomizeException) e);
//            } else {
//                log.error("handle error", e);
//                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
//            }
//            try {
//                response.setContentType("application/json");
//                response.setStatus(200);
//                response.setCharacterEncoding("utf-8");
//                PrintWriter writer = response.getWriter();
//                writer.write(JSON.toJSONString(resultDTO));
//                writer.close();
//            } catch (IOException ioe) {
//            }
//            return null;
//        } else {
        // spring 可以 handle的异常， 404不能拦截
            // 错误页面跳转
            if (e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            } else {
//                log.error("handle error", e);
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
}
