package com.example.extestes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imFoto;
    Button btfoto;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "oi ", Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "alterado git", Toast.LENGTH_LONG).show();
        retiraFoto();

    }



    private void retiraFoto(){

        btfoto=(Button)findViewById(R.id.btnTirarFoto);

        imFoto=(ImageView)findViewById(R.id.imagem);

        btfoto.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,0);

            }

        });

    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data ){

        super.onActivityResult(requestCode, resultCode, data);

        Bundle bundle = data.getExtras();

        if (data !=null){

            Bitmap bitmap = (Bitmap)bundle.get("data");

            imFoto.setImageBitmap(bitmap);

            SaveImage(bitmap);

        }

    }



    private void SaveImage(Bitmap finalBitmap) {



        String root = Environment.getExternalStorageDirectory().toString();

        File myDir = new File(root + "/saved_images");

        myDir.mkdirs();

        Random generator = new Random();

        int n = 10000;

        n = generator.nextInt(n);

        String fname = "Image-"+ n +".jpg";

        File file = new File (myDir, fname);

        if (file.exists ()) file.delete ();

        try {

            FileOutputStream out = new FileOutputStream(file);

            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);

            out.flush();

            out.close();



        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
