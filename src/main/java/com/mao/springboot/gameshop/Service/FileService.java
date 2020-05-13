package com.mao.springboot.gameshop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    @Autowired
    ServletContext servletContext;

    public String saveImage(MultipartFile file) throws Exception{
        String folder = servletContext.getRealPath("/") +"/uploads/img/";
        byte[] bytes = file.getBytes();
        Path path = Paths.get(folder +file.getOriginalFilename());
        Files.write(path,bytes);
        return path.toString();
    }

    public String saveMessageImage(MultipartFile file,String pathStr) throws Exception{
        String filePath = servletContext.getRealPath("/") + pathStr;
        byte[] bytes = file.getBytes();
        Path path = Paths.get(filePath);
        Files.write(path,bytes);
        return path.toString();
    }

}
