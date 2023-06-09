package com.rapid.swifta.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorObject> handleExpenseNotFoundException(ResourceNotFoundException ex, WebRequest request){
//        ErrorObject eObject = new ErrorObject();
//        eObject.setStatusCode(HttpStatus.NOT_FOUND.value());
//        eObject.setMessage(ex.getMessage());
//        eObject.setTimestamp(System.currentTimeMillis());
//        return new ResponseEntity<ErrorObject>(eObject, HttpStatus.NOT_FOUND);
//    }
//
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<ErrorObject> handleException(MethodArgumentTypeMismatchException ex, WebRequest request){
//        ErrorObject eObject = new ErrorObject();
//        eObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
//        eObject.setMessage(ex.getMessage());
//        eObject.setTimestamp(System.currentTimeMillis());
//        return new ResponseEntity<>(eObject, HttpStatus.BAD_REQUEST);
//    }
//
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorObject> handleGeneralException(Exception ex, WebRequest request){
//        ErrorObject eObject = new ErrorObject();
//        eObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        eObject.setMessage(ex.getMessage());
//        eObject.setTimestamp(System.currentTimeMillis());
//        return new ResponseEntity<ErrorObject>(eObject, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//
//    @ExceptionHandler(ItemAlreadyExistsException.class)
//    public ResponseEntity<ErrorObject> handleItemExistsException(ItemAlreadyExistsException ex, WebRequest request){
//        ErrorObject eObject = new ErrorObject();
//        eObject.setStatusCode(HttpStatus.CONFLICT.value());
//        eObject.setMessage(ex.getMessage());
//        eObject.setTimestamp(System.currentTimeMillis());
//        return new ResponseEntity<ErrorObject>(eObject, HttpStatus.CONFLICT);
//    }



//    @Override
//    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
//                                                                  HttpStatusCode status, WebRequest request) {
//
//        Map<String, Object> body = new HashMap<String, Object>();
//
//        body.put("timestamp", new Date());
//        body.put("statusCode", HttpStatus.BAD_REQUEST.value());
//
//        List<String> errors = ex.getBindingResult()
//                .getFieldErrors()
//                        .stream()
//                                .map(x-> x.getDefaultMessage())
//                                        .collect(Collectors.toList());
//        body.put("messages", errors);
//
//        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
//    }
}
