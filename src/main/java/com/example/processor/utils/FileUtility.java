package com.example.processor.utils;

import com.example.processor.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

/**
 * @author Harshil Makwana
 */
@Slf4j
public class FileUtility {

    public static int fetchFileData(String fileName, String product,String fileLocation) throws Exception {
        Properties properties = new Properties();
        int total=0;
        try {
            //File file = ResourceUtils.getFile("classpath:"+fileName);
            //System.out.println(fileName);
            log.info("Inside the fetchFileData method of FileUtility");
            log.info("The file name is: {}",fileName);
            InputStream in = new FileInputStream(fileLocation+fileName);
            //System.out.println(fileName);
            properties.load(in);
            Set<Object> keys = properties.keySet();
            for(Object key:keys){
                String s = key.toString();
                String[] array = s.split(",");
                if(array[1]==null){
                    log.error("ServiceException Exception occurred due to file content not proper");
                    throw new ServiceException(HttpStatus.BAD_REQUEST,fileName,"Input file not in CSV format.");
                }
                if(Objects.equals(array[0], product)){
                    total = total+Integer.parseInt(array[1].trim());
                }
            }

        }catch (FileNotFoundException e){
            log.error("FileNotFoundException Exception occurred: {}",e.getMessage());
            throw new ServiceException(HttpStatus.NOT_FOUND,fileName,"File not found.");
        }catch (ArrayIndexOutOfBoundsException e){
            log.error("ArrayIndexOutOfBoundsException Exception occurred: {}",e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST,fileName,"Input file not in CSV format.");
        }
        catch (IOException e) {
            log.error("IOException Exception occurred: {}",e.getMessage());
            throw new ServiceException(HttpStatus.BAD_REQUEST,fileName,"Input file not in CSV format.");
        }catch (Exception e){
            log.error("Exception occurred: {}",e.getMessage());
            throw new Exception(e.getMessage());
        }
        return total;
    }
}
