package com.aws_s3.example.controller;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
public class AmazonS3Controller {
    @Autowired
    private AmazonS3 s3Client;

    @PostMapping("/create-Bucket")
    public String createBucket(@RequestParam String bucketName) {
        if (!s3Client.doesBucketExistV2(bucketName)) {
            s3Client.createBucket(bucketName);
            return "Bucket created:- " + bucketName;
        } else {
            return "Bucket not-created";
        }

    }

    private File convertMultipartFileToFile(MultipartFile file)
    {
        File convertFile=new File(file.getOriginalFilename());
        try (FileOutputStream fos=new FileOutputStream(convertFile)){
            fos.write(file.getBytes());
        }catch (IOException e)
        {
            System.out.println("Error converting multipartFile to file :"+e);
        }
        return convertFile;
    }

    @PostMapping("/upload-file")
    public String uploadFile(@RequestParam MultipartFile file)
    {
        File fileObj=convertMultipartFileToFile(file);
        String fileName=System.currentTimeMillis()+"_"+file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest("aws-s3-demo-by-mahesh",fileName,fileObj));
        fileObj.delete();
        return "File Uploaded";
    }

    @GetMapping("/download-file")
    public byte[] dowanloadFile(String filename)
    {
        S3Object s3Object=s3Client.getObject("aws-s3-demo-by-mahesh",filename);
        S3ObjectInputStream inputStream=s3Object.getObjectContent();
        try{
            byte[]content= IOUtils.toByteArray(inputStream);
            return content;
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    @DeleteMapping("/delete-file")
    public String deleteFile(String fileName)
    {
        s3Client.deleteObject("ws-s3-demo-by-mahesh",fileName);
        return  fileName+" removed ............";
    }

}
