package org.alluxio;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class S3Demo {
    public static void main(String[] args) throws IOException {
        Regions clientRegion = Regions.US_WEST_2;
        String bucketName = "zhaozihao-test-01";
        String key = "a1.txt";

        try {
            long startTime = System.currentTimeMillis();

            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(new ProfileCredentialsProvider())
                    .withRegion(clientRegion)
                    .build();

            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
            S3Object object = s3Client.getObject(getObjectRequest);

            InputStream in = object.getObjectContent();
            File file = new File("a1.txt");
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[64 * 1024 * 1024];
            int len = 0;
            while ((len = in.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }

            fos.close();
            in.close();

            long endTime = System.currentTimeMillis();

            System.out.println("Download time: " + (endTime - startTime) + "ms");

        } catch (AmazonServiceException e) {
            // The call was transmitted successfully, but Amazon S3 couldn't process
            // it and returned an error response.
            e.printStackTrace();
        } catch (SdkClientException e) {
            // Amazon S3 couldn't be contacted for a response, or the client
            // couldn't parse the response from Amazon S3.
            e.printStackTrace();
        }
    }
}
