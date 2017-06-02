package com.imageprocessing.enzoftware.imageprocessingapp;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import com.mvc.imagepicker.ImagePicker;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ImageButton picker,output;
    Button medianFilter,mirrorFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picker = (ImageButton)findViewById(R.id.imageButton);
        medianFilter = (Button) findViewById(R.id.medianFilter);
        output = (ImageButton) findViewById(R.id.outputImage);
        mirrorFilter = (Button) findViewById(R.id.mirrorFilter);
        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.setMinQuality(600, 600);
                onPickImage(picker);
            }
        });

    }

    private void medianFilterAlgorithm(Bitmap bitmap){
        int [] pixel = new int [9];
        int [] R = new int[9];
        int [] G = new int[9];
        int [] B = new int[9];

        for(int i = 1  ; i< bitmap.getWidth() -1 ; i++){
            for (int j = 1 ; j < bitmap.getHeight() -1 ; j++){
                /*int colour = bitmap.getPixel(i, j);
                int red = Color.red(colour);
                int blue = Color.blue(colour);
                int green = Color.green(colour);*/

                pixel[0] = bitmap.getPixel(i-1,j-1);
                pixel[1] = bitmap.getPixel(i-1,j);
                pixel[2] = bitmap.getPixel(i-1,j+1);
                pixel[3] = bitmap.getPixel(i,j+1);
                pixel[4] = bitmap.getPixel(i+1,j+1);
                pixel[5] = bitmap.getPixel(i+1,j);
                pixel[6] = bitmap.getPixel(i+1,j-1);
                pixel[7] = bitmap.getPixel(i,j-1);
                pixel[8] = bitmap.getPixel(i,j);

                for(int k = 0 ; k<9 ; k++){
                    R[k] = Color.red(pixel[k]);
                    G[k] = Color.green(pixel[k]);
                    B[k] = Color.blue(pixel[k]);
                }

                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);
                bitmap.setPixel(i,j, Color.rgb(R[4],G[4],B[4]));
            }
        }


    }

    private void mirrorFilterAlgorithm(Bitmap photo){
        int r,g,b;
        int r1,g1,b1;
        int width = photo.getWidth()-1;
        int color;
        int color1;
        for(int i=0;i< ( photo.getWidth() ) / 2;i++){
            for(int j=0;j<photo.getHeight();j++){
                //se obtiene el color del pixel
                color1 = photo.getPixel(i,j);
                color = photo.getPixel(width,j);
                //se extraen los valores RGB
                r = Color.red(color);
                g = Color.green(color); // ultima pos
                b = Color.blue(color);

                r1 = Color.red(color1);
                g1 = Color.green(color1); // primera pos
                b1 = Color.blue(color1);

                //se coloca en la nueva imagen con los valores invertidos
                photo.setPixel(i,j,Color.rgb(r,g,b));
                photo.setPixel(width,j,Color.rgb(r1,g1,b1));
            }
            width--;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = ImagePicker.getImageFromResult(this, requestCode, resultCode, data);
        final Bitmap resizable = Bitmap.createScaledBitmap(bitmap,714,438,false);
        // TODO do something with the bitmap
        picker.setImageBitmap(resizable);
        medianFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                medianFilterAlgorithm(resizable);
                output.setImageBitmap(resizable);
            }
        });


        mirrorFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mirrorFilterAlgorithm(resizable);
                output.setImageBitmap(resizable);
            }
        });

    }

    public void onPickImage(View view) {
        // Click on image button
        ImagePicker.pickImage(this, "Select your image:");
    }
}
