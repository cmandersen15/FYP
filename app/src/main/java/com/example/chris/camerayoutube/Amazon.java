package com.example.chris.camerayoutube;

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