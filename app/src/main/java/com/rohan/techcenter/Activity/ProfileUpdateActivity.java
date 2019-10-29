package com.rohan.techcenter.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rohan.techcenter.R;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import es.dmoral.toasty.Toasty;

public class ProfileUpdateActivity extends AppCompatActivity implements View.OnClickListener {
    CircleImageView pu_userImage;
    EditText pu_firstName,pu_lastName,pu_phone,pu_address,pu_password;
    Button btn_camera,btn_upload,btn_update;

    Bitmap bitmap;
    Uri imageUri;
    String userImageName;
    private static final int PICK_IMAGE=1;
    private static final int CAMERA_REQUEST=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_update);

        pu_userImage=findViewById(R.id.pu_userImage);

        pu_firstName=findViewById(R.id.pu_firstName);
        pu_lastName=findViewById(R.id.pu_lastName);
        pu_phone=findViewById(R.id.pu_phone);
        pu_address=findViewById(R.id.pu_address);
        pu_password=findViewById(R.id.pu_password);

        btn_camera=findViewById(R.id.pu_camera);
        btn_camera.setOnClickListener(this);

        btn_upload=findViewById(R.id.pu_upload);
        btn_upload.setOnClickListener(this);

        btn_update=findViewById(R.id.pu_update);
        btn_update.setOnClickListener(this);
    }

    private boolean validate() {
        if (TextUtils.isEmpty(pu_firstName.getText().toString())) {
            pu_firstName.setError("Enter First Name");
            pu_firstName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pu_lastName.getText().toString())) {
            pu_lastName.setError("Enter Last Name");
            pu_lastName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pu_phone.getText().toString())) {
            pu_phone.setError("Enter Phone Number");
            pu_phone.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pu_address.getText().toString())) {
            pu_address.setError("Enter Address");
            pu_address.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(pu_password.getText().toString())) {
            pu_password.setError("Enter Password");
            pu_password.requestFocus();
            return false;
        }
        return true;
    }

    private void openGallery() {
        Intent gallery = new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery, "Choose Image"), PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            if (data == null) {
                Toasty.normal(getApplicationContext(), "Please select an Image", Toasty.LENGTH_LONG).show();
            }
            imageUri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                pu_userImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode==CAMERA_REQUEST && resultCode==RESULT_OK){
            Bundle extras=data.getExtras();
            bitmap=(Bitmap)extras.get("data");
            pu_userImage.setImageBitmap(bitmap);
        }
    }

    private void StrictMode(){
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void addImage(){}

    private void updateUser(){}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pu_camera:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setTitle("Image Options");

                String[] options={"Open Camera","Open Gallery"};
                builder.setItems(options,new DialogInterface.OnClickListener(){

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0: //camera
                                Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(camera,CAMERA_REQUEST);
                                break;
                            case 1:  //gallery
                                openGallery();
                                break;
                        }
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
                break;

            case R.id.pu_upload:
                if (bitmap!=null) {
                    addImage();
                }
                else {
                    Toasty.warning(ProfileUpdateActivity.this, "Please select a new image", Toasty.LENGTH_SHORT).show();
                }
                break;

            case R.id.pu_update:
                if (validate()) {
                    updateUser();
                }
                finish();
                break;
        }
    }
}
