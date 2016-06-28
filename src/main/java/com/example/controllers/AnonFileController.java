package com.example.controllers;

import com.example.entities.AnonFile;
import com.example.services.AnonFileRepository;
import com.example.utilities.PasswordStorage;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.SQLException;

/**
 * Created by hoseasandstrom on 6/27/16.
 */
@Controller
public class AnonFileController {
    @Autowired
    AnonFileRepository files;

    @PostConstruct
    public void init() throws SQLException {
    Server.createWebServer().start();
    }


    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletRequest request) throws Exception {
        File dir = new File("public/files");
        dir.mkdirs();

        File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);

        FileOutputStream fos = new FileOutputStream(uploadedFile);
        fos.write(file.getBytes());

        AnonFile anonFile = new AnonFile(file.getOriginalFilename(), uploadedFile.getName());
        if (files.count() <= 10) {
            files.save(anonFile);
        }
        return "redirect:/";
        }
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String delete(Integer id) {
        files.delete(id);

        return "redirect:/";
    }
}
