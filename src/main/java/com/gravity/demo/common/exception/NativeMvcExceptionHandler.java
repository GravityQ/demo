//package com.gravity.demo.common.exception;
//
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.huobi.hbg.error.enums.CommonError;
//import com.huobi.hbg.error.util.ParamErrorUtils;
//import com.huobi.hbg.service.util.Failure;
//import com.huobi.hbg.service.util.Result;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.stereotype.Component;
//import org.springframework.web.HttpMediaTypeNotAcceptableException;
//import org.springframework.web.HttpMediaTypeNotSupportedException;
//import org.springframework.web.HttpRequestMethodNotSupportedException;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//import org.springframework.web.multipart.MaxUploadSizeExceededException;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import javax.validation.ConstraintViolationException;
//
///**
// * @since 2019-11-22
// */
//@Component
//@ResponseBody
//@ControllerAdvice
//@ResponseStatus(HttpStatus.OK)
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class NativeMvcExceptionHandler {
//
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public Result<?> handle(MissingServletRequestParameterException e) {
//        return ParamErrorUtils.handle(e);
//    }
//
//    @ExceptionHandler(MethodArgumentConversionNotSupportedException.class)
//    public Result<?> handle(MethodArgumentConversionNotSupportedException e) {
//        return ParamErrorUtils.handle(e);
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public Result<?> handle(MethodArgumentTypeMismatchException e) {
//        return ParamErrorUtils.handle(e);
//    }
//
//    @ExceptionHandler(ConstraintViolationException.class)
//    public Result<?> handle(ConstraintViolationException e) {
//        return ParamErrorUtils.handle(e);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Result<?> handle(MethodArgumentNotValidException e) {
//        return ParamErrorUtils.handle(e);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public static Result<?> handle(HttpMessageNotReadableException e) {
//        if (e.getCause() instanceof JsonMappingException) {
//            return ParamErrorUtils.handle((JsonMappingException) e.getCause());
//        }
//        return new Failure(CommonError.ILLEGAL_REQUEST, e);
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public static Result<?> handle(HttpRequestMethodNotSupportedException e) {
//        return new Failure(CommonError.ILLEGAL_REQUEST, e);
//    }
//
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public static Result<?> handle(HttpMediaTypeNotSupportedException e) {
//        return new Failure(CommonError.ILLEGAL_REQUEST, e);
//    }
//
//    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
//    public static Result<?> handle(HttpMediaTypeNotAcceptableException e) {
//        return new Failure(CommonError.ILLEGAL_REQUEST, e);
//    }
//
//    @ExceptionHandler(MaxUploadSizeExceededException.class)
//    public static Result<?> handle(MaxUploadSizeExceededException e) {
//        return new Failure(CommonError.EXCEED_SIZE, e);
//    }
//
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public static Result<?> handle(NoHandlerFoundException e) {
//        return new Failure(CommonError.NOT_FOUND, e);
//    }
//}
