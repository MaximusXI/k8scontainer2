package com.example.processor.controller;

import com.example.processor.model.Request;
import com.example.processor.model.Response;
import com.example.processor.utils.FileUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Harshil Makwana
 */
@RestController
@RequestMapping("/fileService")
@Slf4j
public class fileParseController {

    @Value("${fileLocation}")
    private String fileLocation;
    @PostMapping("/welcome")
    public ResponseEntity<Response> getFileData(@RequestBody Request request) throws Exception {
        //System.out.println("The controller:"+request.getFile());
        log.info("A simple log 9");
        log.info("Inside the FileParseController with request: {}",request);
        int sum = FileUtility.fetchFileData(request.getFile(), request.getProduct(),fileLocation);
        Response response = new Response();
        response.setFile(request.getFile());
        response.setSum(String.valueOf(sum));
        return ResponseEntity.ok(response);
    }
}
