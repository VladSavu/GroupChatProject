package com.example.groupchatproject.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Base64;
import android.util.Patterns;
import android.widget.Toast;

import com.example.groupchatproject.R;
import com.example.groupchatproject.databinding.ActivitySignUpBinding;

import java.io.ByteArrayOutputStream;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){
        binding.textSignIn.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
        });
        binding.signUpButton.setOnClickListener(view -> {
            if (isValidSignUpData()){
                signUp();
            }
        });
    }

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void signUp(){

    }

    private String encodeImage(Bitmap bitmap){
        int previewWidth = 150;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private Boolean isValidSignUpData(){
        if(encodedImage == null){
            showToast("Select profile image");
            return false;
        }else if(binding.inputName.getText().toString().isEmpty()){
            showToast("Enter name");
            return false;
        }else if(binding.inputEmail.getText().toString().isEmpty()){
            showToast("Enter email");
            return false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(binding.inputEmail.getText().toString()).matches()){
            showToast("Email is invalid");
            return false;
        }else if(binding.inputPassword.getText().toString().isEmpty()){
            showToast("Enter password");
            return false;
        }else if(!binding.inputConfirmPassword.getText().toString().equals(binding.inputPassword.getText().toString())){
            showToast("Your passwords do not match");
            return false;
        }else{
            return true;
        }
    }
}