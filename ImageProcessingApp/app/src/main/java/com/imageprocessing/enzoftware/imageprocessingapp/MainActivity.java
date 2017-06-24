package com.imageprocessing.enzoftware.imageprocessingapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.wang.avi.AVLoadingIndicatorView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    AVLoadingIndicatorView avi;
    ImageButton picker,output;
    Button medianFilter,mirrorFilter,btnsave,cannyFilter;
    ProgressBar scrollView;
    private static int RESULT_LOAD_IMG = 1;
    String impDeclarableString;
    public Bitmap bmp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picker = (ImageButton)findViewById(R.id.imageButton);
        medianFilter = (Button) findViewById(R.id.medianFilter);
        output = (ImageButton) findViewById(R.id.outputImage);
        mirrorFilter = (Button) findViewById(R.id.mirrorFilter);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        btnsave = (Button) findViewById(R.id.ButtonSave);
        scrollView = (ProgressBar) findViewById(R.id.scrollView);
        cannyFilter = (Button) findViewById(R.id.cannyFilter);



        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadImagefromGallery();

            }
        });

        medianFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bmp != null){
                    startAnim();
                    output.setVisibility(View.GONE);
                    output.setImageBitmap(medianFilterAlgorithm(bmp));
                }else{
                    Toast.makeText(getApplicationContext(), "No has seleccionado ninguna foto", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


        cannyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bmp != null){
                    startAnim();
                    output.setVisibility(View.GONE);
                    output.setImageBitmap(cannyFilterAlg(bmp));
                }else{
                    Toast.makeText(getApplicationContext(), "No has seleccionado ninguna foto", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


        mirrorFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap b = null;

                if(bmp != null){
                    startAnim();
                    long futureTime = System.currentTimeMillis() + 10000;
                    while( System.currentTimeMillis() < futureTime ){
                        synchronized (this){
                            try{
                                //wait(futureTime - System.currentTimeMillis());
                                 b = mirrorFilterAlgorithm(bmp);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    avi.hide();
                    //output.setVisibility(View.GONE);
                    output.setImageBitmap(b);
                }else{
                    Toast.makeText(getApplicationContext(), "No has seleccionado ninguna foto", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Foto guardada con exito !!", Toast.LENGTH_LONG)
                        .show();
                saveImage(bmp,"fotazo");
            }
        });


    }


    void startAnim(){
        avi.show();
    }


    public void loadImagefromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };
                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                impDeclarableString = cursor.getString(columnIndex);
                cursor.close();
                Bitmap copy = Bitmap.createScaledBitmap(BitmapFactory.decodeFile(impDeclarableString),
                        714,
                        438,
                        false);
                picker.setImageBitmap(copy);
                Bitmap b = BitmapFactory.decodeFile(impDeclarableString);
                final Bitmap resizable = Bitmap.createScaledBitmap(b,714,438,false);
                bmp = Bitmap.createScaledBitmap(resizable,714,438,false);

            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }


    private void saveImage(Bitmap finalBitmap, String image_name) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Image-" + image_name+ ".jpg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        Log.i("LOAD", root + fname);
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

                color1 = photo.getPixel(i,j);
                color = photo.getPixel(width,j);

                r = Color.red(color);
                g = Color.green(color);
                b = Color.blue(color);

                r1 = Color.red(color1);
                g1 = Color.green(color1);
                b1 = Color.blue(color1);

                out.setPixel(i,j,Color.rgb(r,g,b));
                out.setPixel(width,j,Color.rgb(r1,g1,b1));
            }
            width--;
        }
        return out;
    }


    private Bitmap cannyFilterAlg(Bitmap Imagem){
        int height = Imagem.getHeight();
        int width = Imagem.getWidth();
        int[][] Cannys = new int[][]{{0, 1, 0}, {1, -4, 1}, {0, 1, 0}};
        int todosPixelsR = 0;
        int todosPixelsG = 0;
        int todosPixelsB = 0;
        int i , j;
        int color;
        int aux , aux2;
        for (i = 0; i < height - 2; i++)
        {
            for (j = 0; j < width - 2; j++)
            {
                for (aux = 0; aux < 3; aux++)
                {
                    for (aux2 = 0; aux2 < 3; aux2++)
                    {
                        color = Imagem.getPixel(j+aux,i+aux2);
                        todosPixelsR += Color.red(color)* Cannys[aux2][aux];
                        todosPixelsG += Color.green(color) * Cannys[aux2][aux];
                        todosPixelsB += Color.blue(color) * Cannys[aux2][aux];
                    }

                }


                if (todosPixelsR < 0)
                {
                    todosPixelsR = 0;
                }
                else
                {
                    if (todosPixelsR > 255)
                    {
                        todosPixelsR = 255;
                    }
                }
                if (todosPixelsG < 0)
                {
                    todosPixelsG = 0;
                }
                else
                {
                    if (todosPixelsG > 255)
                    {
                        todosPixelsG = 255;
                    }
                }
                if (todosPixelsB < 0)
                {
                    todosPixelsB = 0;
                }
                else
                {
                    if (todosPixelsB > 255)
                    {
                        todosPixelsB = 255;
                    }

                }
                int cinza = (todosPixelsR + todosPixelsG + todosPixelsB) / 3;


                Imagem.setPixel(j + 1, i + 1, Color.argb(255, cinza, cinza, cinza));
                todosPixelsR = 0;
                todosPixelsG = 0;
                todosPixelsB = 0;
                cinza = 0;

            }
        }
        return Imagem;
    }
}


