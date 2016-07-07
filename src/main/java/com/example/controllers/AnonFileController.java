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
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Boolean forever = false;
        if (forever != null) {
            forever = true;
        }

        int count = (int) files.countByForever(false);
        if (count < 10) {

            File dir = new File("public/files");
            dir.mkdirs();

            File uploadedFile = File.createTempFile("file", file.getOriginalFilename(), dir);

            FileOutputStream fos = new FileOutputStream(uploadedFile);
            fos.write(file.getBytes());

            String password = request.getParameter("password"); //adopted from CalendarSpring Test

            String comment = request.getParameter("comment");//adopted from CalendarSpring Test

            AnonFile anonFile = new AnonFile();
            if (password != null) {
                anonFile = new AnonFile(file.getOriginalFilename(), uploadedFile.getName(), PasswordStorage.createHash(password), forever, comment);
            } else {
                anonFile = new AnonFile(file.getOriginalFilename(), uploadedFile.getName(), forever, comment);
                files.save(anonFile);
            }


        } else {
            Iterable<AnonFile> fileList = files.findByForever(false);
            for (AnonFile f : fileList) {
                int least = 0;
                AnonFile fileOnDatabase = files.findOne(least);
                File fileOnDisk = new File("public/files/" + fileOnDatabase.getRealFilename());
                fileOnDisk.delete();
                files.delete(least);
            }
        }
        return "redirect:/";
    }
    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public String delete(int fileId, String checkPassword) throws PasswordStorage.InvalidHashException, PasswordStorage.CannotPerformOperationException {
        AnonFile f = files.findOne(fileId);
        if(f.getForever() == true) {
            return "redirect:/";
        }
        if ((f.getPassword() != null) && (!PasswordStorage.verifyPassword(checkPassword, f.getPassword()))) {
            return "redirect:/";
        }
        else {
        files.delete(fileId);
            return "redirect:/";
        }
    }
}
