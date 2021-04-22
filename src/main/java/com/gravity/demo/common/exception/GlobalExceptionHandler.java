package com.gravity.demo.common.exception;

import com.gravity.demo.common.ResultResponse;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * 全局异常通知
 *
 * @author qijunlin
 * @date 2019-08-16 18:05
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 业务异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResultResponse businessExceptionHandler(HttpServletRequest request, BusinessException e) {
        return ResultResponse.error(e.getCode(), e.getMsg());
    }

    /**
     * 系统异常
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ResultResponse exceptHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        return ResultResponse.error(1, e.getMessage());
    }

    /**
     * 统一处理请求参数校验(实体对象传参)
     *
     * @param e BindException
     * @return ResultResponse
     */
    @ExceptionHandler(BindException.class)
    public ResultResponse validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return ResultResponse.error(message.toString());
    }

    /**
     * 统一处理请求参数校验(普通传参)
     *
     * @param e ConstraintViolationException
     * @return ResultResponse
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResultResponse handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return ResultResponse.error(message.toString());
    }

    /**
     * 参数校验异常拦截
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResponse handleMgtMethodArgumentNotValidException(MethodArgumentNotValidException e) throws ClassNotFoundException, NoSuchFieldException {
        FieldError fieldError = e.getBindingResult().getFieldError();
        String fieldName = fieldError.getField();
        Field field = e.getBindingResult().getTarget().getClass().getDeclaredField(fieldName);
        ApiModelProperty annotation = field.getAnnotation(ApiModelProperty.class);
        String message = "";
        if (annotation != null) {
            message = annotation.value();
        }
        message = message + "(" + fieldName + ")" + fieldError.getDefaultMessage();
        return ResultResponse.error(message);
    }
}
