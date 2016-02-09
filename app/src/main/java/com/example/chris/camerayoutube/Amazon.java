package com.example.chris.camerayoutube;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.http.HttpClient;
import com.amazonaws.http.HttpResponse;
import com.amazonaws.mobileconnectors.cognito.CognitoSyncManager;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitosync.model.Dataset;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Chris on 29/01/2016.
 */
public class Amazon {
/*
    // Initialize the Amazon Cognito credentials provider
    CognitoCachingCredentialsProvider credentialsProvider = new CognitoCachingCredentialsProvider(
            getApplicationContext(),
            "eu-west-1:730b6872-024a-4970-b60e-1dcaa6eac691", // Identity Pool ID
            Regions.EU_WEST_1 // Region
    );

    // Initialize the Cognito Sync client
    CognitoSyncManager syncClient = new CognitoSyncManager(
            getApplicationContext(),
            Regions.EU_WEST_1, // Region
            credentialsProvider);

    // Create a record in a dataset and synchronize with the server
    Dataset dataset = syncClient.openOrCreateDataset("myDataset");
    dataset.put("myKey", "myValue");
    dataset.synchronize(new DefaultSyncCallback() {
        @Override
        public void onSuccess (Dataset dataset, List newRecords){ //Your handler code here } });
        }
    }

    AmazonS3 s3 = new AmazonS3Client(credentialsProvider);
    TransferUtility transferUtility = new TransferUtility(s3, getApplicationContext()));

    String MY_BUCKET = "cmandersenproject";

    TransferObserver observer = transferUtility.upload(
            MY_BUCKET,     /* The bucket to upload to */
          //  OBJECT_KEY,    /* The key for the uploaded object */
       //     MY_FILE        /* The file where the data to upload exists */

}