package com.heima.minio;

import io.minio.*;
import io.minio.errors.MinioException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MinIOTest {
    
    public static void main(String[] args)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            // access key and secret key 需要在控制台中创建
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://192.168.88.11:9000")
                            .credentials("iRS32pFqZ5vHnXJkJQMI", "4i2C5D3EYqicEJ18lErPyxLITROwWdHbHhQEYDsA")
                            .build();

            // Make 'leadnews' bucket if not exist.
            boolean found =
                    minioClient.bucketExists(BucketExistsArgs.builder().bucket("leadnews").build());
            if (!found) {
                // Make a new bucket called 'leadnews'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("leadnews").build());
            } else {
                System.out.println("Bucket 'leadnews' already exists.");
            }

            // Upload 'E:\list.html' as object name 'list.html' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("leadnews")
                            .object("list.html")
                            .filename("E:\\list.html")
                            .build());
            System.out.println(
                    "'E:\\list.html' is successfully uploaded as "
                            + "object 'list.html' to bucket 'leadnews'.");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}
