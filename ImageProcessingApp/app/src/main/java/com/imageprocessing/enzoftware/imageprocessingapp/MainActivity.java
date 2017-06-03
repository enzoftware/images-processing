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
                //ImagePicker.setMinQuality(600, 600);
                onPickImage(picker);
            }
        });

    }

    private Bitmap medianFilterAlgorithm(Bitmap bitmap){
        int [] pixel = new int [9];
        int [] R = new int[9];
        int [] G = new int[9];
        int [] B = new int[9];
        Bitmap out = bitmap;
        for(int i = 1  ; i< bitmap.getWidth() -1 ; i++){
            for (int j = 1 ; j < bitmap.getHeight() -1 ; j++){

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
                out.setPixel(i,j, Color.rgb(R[4],G[4],B[4]));
            }
        }

        return out;
    }

    private Bitmap mirrorFilterAlgorithm(Bitmap photo){
        int r,g,b;
        int r1,g1,b1;
        int width = photo.getWidth()-1;
        int color;
        int color1;
        Bitmap out = photo;
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
                out.setPixel(i,j,Color.rgb(r,g,b));
                out.setPixel(width,j,Color.rgb(r1,g1,b1));
            }
            width--;
        }

        return out;
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
                Bitmap copy = Bitmap.createBitmap(resizable,0,0,resizable.getWidth(),resizable.getHeight());
                output.setImageBitmap(medianFilterAlgorithm(copy));
            }
        });


        mirrorFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap copy = Bitmap.createBitmap(resizable,0,0,resizable.getWidth(),resizable.getHeight());
                output.setImageBitmap(mirrorFilterAlgorithm(copy));
            }
        });

    }

    public void onPickImage(View view) {

        ImagePicker.pickImage(this, "Select your image:");
    }
}
