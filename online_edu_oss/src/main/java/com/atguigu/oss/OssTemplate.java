package com.atguigu.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@Component
public class OssTemplate {
   /* oss.endpoint = http://oss-cn-beijing.aliyuncs.com
    oss.accessKeyId = LTAI4G2Gy8wMyYgGJAQXU7G8
    oss.accessKeySecret = RFoXrL0ECnS5QETFRC833s5fYSzHYk*/
    @Value("${oss.endpoint}")
    String endpoint;
    @Value("${oss.accessKeyId}")
    String accessKeyId;
    @Value("${oss.accessKeySecret}")
    String accessKeySecret;
    @Value("${oss.bucketName}")
    String bucketName;

    //上传文件
    public String upload(InputStream inputStream,String fileName) throws FileNotFoundException {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
       // String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        //String accessKeyId = "LTAI4G2Gy8wMyYgGJAQXU7G8";
       // String accessKeySecret = "RFoXrL0ECnS5QETFRC833s5fYSzHYk";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 上传文件流。
        //InputStream inputStream = new FileInputStream("E:\\103.jpg");
        ossClient.putObject("liuwenbo123", fileName, inputStream);



// 关闭OSSClient。
        ossClient.shutdown();

        //https://liuwenbo123.oss-cn-beijing.aliyuncs.com/104.jpg
        String retUrl = "https://"+bucketName+"."+endpoint+"/"+fileName;
        return retUrl;

    }

    /**
     * 删除文件
     */
    public void deleteFile(String fileName){
// Endpoint以杭州为例，其它Region请按实际情况填写。
        //String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
       // String accessKeyId = "LTAI4G2Gy8wMyYgGJAQXU7G8";
       // String accessKeySecret = "RFoXrL0ECnS5QETFRC833s5fYSzHYk";

       // String bucketName = "liuwenbo123";
        //String objectName = "104.jpg";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, fileName);


// 关闭OSSClient。
        ossClient.shutdown();
    }
    /**
     * 批量删除
     */

    public void deleteMultiFile(List<String> fileNameList){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        //String endpoint = "http://oss-cn-beijing.aliyuncs.com";
// 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        //String accessKeyId = "LTAI4G2Gy8wMyYgGJAQXU7G8";
        //String accessKeySecret = "RFoXrL0ECnS5QETFRC833s5fYSzHYk";

        //String bucketName = "liuwenbo123";
        //String objectName = "104.jpg";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 删除文件。key等同于ObjectName，表示删除OSS文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
       // List<String> fileNameList = new ArrayList<String>();
        //fileNameList.add("1.jpg");
       // fileNameList.add("104.jpg");


        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(fileNameList));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();

// 关闭OSSClient。
        ossClient.shutdown();

    }


}
