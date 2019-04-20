package com.example.lucene.controller;

import com.example.lucene.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import com.example.lucene.domain.uploadfile;

/**
 * Greated by Terry on 2019/4/16
 * Time: 14:56
 */
//@RestController
//@ResponseBody
//@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传:
     * 用<input type="file" name="myfile">
     * 内容格式要定义成multipart/form-data
     * @param file
     * @return
     * @throws IOException
     */
  //  @PostMapping()
   // @ApiOperation(value = "文件上传服务")
    public String fileupload(@RequestParam("file") CommonsMultipartFile file) throws IOException {
        String path="D:/lucene"+new Date().getTime()+file.getOriginalFilename();
        File newfile=new File(path);
        file.transferTo(newfile);
        uploadfile fi=new uploadfile();
        fi.setAddress("D:/lucene"+new Date().getTime()+file.getOriginalFilename());
        fi.setFilename(file.getOriginalFilename());
        fileService.creat(fi);
        return "/success";
    }

   // @DeleteMapping("/{id}")
  //  @ApiOperation(value = "文件删除服务")
    public void deleteId(int id){
        fileService.delete(id);
    }

}
