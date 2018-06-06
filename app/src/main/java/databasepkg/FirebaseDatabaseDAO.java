package databasepkg;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Ali Alzantot on 07/03/2018.
 */

public class FirebaseDatabaseDAO implements Serializable {

    public void uploadUserImage(final SaveImageCallBack callBack, Bitmap userPhoto, final String imageName) {
        Log.i("ayData",""+"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference("userImages/");
        StorageReference ref = storageReference.child(imageName);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        userPhoto.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        ref.putBytes(imageBytes)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        StorageReference storageRef = storage.getReference();
                        storageRef.child("userImages/" + imageName).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                callBack.savedImageUrl(uri.toString());
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception exception) {
                                callBack.savedImageUrl(null);
                            }
                        });

                    }
                });
    }
}





