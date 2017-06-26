package com.imageprocessing.enzoftware.imageprocessingapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
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
    public Bitmap b = null;

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
                    TaskMedian tme = new TaskMedian();
                    tme.execute();
                    startAnim();
                    output.setImageBitmap(b);
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


                if(bmp != null){
                    TaskMirror tm = new TaskMirror();
                    tm.execute();
                    avi.show();

                    while ( b == null){
                        Log.d("Loading", "second");
                    }

                    output.setImageBitmap(b);
                    //avi.hide();

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
        Log.d("FUNCIONA","FUNCIONA");
        return out;
    }


    private Bitmap cannyFilterAlg(Bitmap Imagem){
        int width = b.getWidth();
        int height = b.getHeight();

        Bitmap n = Bitmap.createBitmap(Imagem,0,0,width,height);

            int[][] allPixR = new int[width][height];
            int[][] allPixG = new int[width][height];
            int[][] allPixB = new int[width][height];

        for ( int i = 0; i < b.getWidth(); i++ )
        {
            for ( int j = 0; j < b.getHeight(); j++ )
            {
                allPixR[i][ j] = b.getPixel (i, j).R;
                allPixG[i][ j] = b.getPixel (i, j).G;
                allPixB[i][ j] = b.getPixel (i, j).B;
            }
        }
        for ( int i = 2; i < width - 2; i++ )
        {
            for ( int j = 2; j < height - 2; j++ )
            {
                int red = (
                ( ( allPixR [i - 2][ j - 2] )  + ( allPixR [i - 1][ j - 2] ) * 4 + ( allPixR [i][ j - 2] ) * 7 + ( allPixR [i + 1][ j - 2] ) * 4 + ( allPixR [i + 2][ j - 2] )
                + ( allPixR [i - 2][ j - 1] ) * 4 + ( allPixR [i - 1][ j - 1] ) * 16 + ( allPixR [i][ j - 1] ) * 26 + ( allPixR [i + 1][ j - 1] ) * 16 + ( allPixR [i + 2][ j - 1] ) * 4
                    + ( allPixR [i - 2][ j] ) * 7 + ( allPixR [i - 1][ j] ) * 26 + ( allPixR [i][ j] ) * 41 + ( allPixR [i + 1][ j] ) * 26 + ( allPixR [i + 2][ j] ) * 7
                    + ( allPixR [i - 2][ j + 1] ) * 4 + ( allPixR [i - 1][ j + 1] ) * 16 + ( allPixR [i][ j + 1] ) * 26 + ( allPixR [i + 1][ j + 1] ) * 16 + ( allPixR [i + 2][ j + 1] ) * 4
                    + ( allPixR [i - 2][ j + 2] )  + ( allPixR [i - 1][ j + 2] ) * 4 + ( allPixR [i][ j + 2] ) * 7 + ( allPixR [i + 1][ j + 2] ) * 4 + ( allPixR [i + 2][ j + 2] )  ) / 273
                              );

                int green = (
                ( ( allPixG[i - 2][ j - 2] )  + ( allPixG[i - 1][ j - 2] ) * 4 + ( allPixG[i][ j - 2] ) * 7 + ( allPixG[i + 1][ j - 2] ) * 4 + ( allPixG[i + 2][ j - 2] )
                + ( allPixG[i - 2][ j - 1] ) * 4 + ( allPixG[i - 1][ j - 1] ) * 16 + ( allPixG[i][ j - 1] ) * 26 + ( allPixG[i + 1][ j - 1] ) * 16 + ( allPixG[i + 2][ j - 1] ) * 4
                    + ( allPixG[i - 2][ j] ) * 7 + ( allPixG[i - 1][ j] ) * 26 + ( allPixG[i][ j] ) * 41 + ( allPixG[i + 1][ j] ) * 26 + ( allPixG[i + 2][ j] ) * 7
                    + ( allPixG[i - 2][ j + 1] ) * 4 + ( allPixG[i - 1][ j + 1] ) * 16 + ( allPixG[i][ j + 1] ) * 26 + ( allPixG[i + 1][ j + 1] ) * 16 + ( allPixG[i + 2][ j + 1] ) * 4
                    + ( allPixG[i - 2][ j + 2] )  + ( allPixG[i - 1][ j + 2] ) * 4 + ( allPixG[i][ j + 2] ) * 7 + ( allPixG[i + 1][ j + 2] ) * 4 + ( allPixG[i + 2][ j + 2] )  ) / 273
                              );

                int blue = (
                ( ( allPixB[i - 2][ j - 2] )  + ( allPixB[i - 1][ j - 2] ) * 4 + ( allPixB[i][ j - 2] ) * 7 + ( allPixB[i + 1][ j - 2] ) * 4 + ( allPixB[i + 2][ j - 2] )
                + ( allPixB[i - 2][ j - 1] ) * 4 + ( allPixB[i - 1][ j - 1] ) * 16 + ( allPixB[i][ j - 1] ) * 26 + ( allPixB[i + 1][ j - 1] ) * 16 + ( allPixB[i + 2][ j - 1] ) * 4
                    + ( allPixB[i - 2][ j] ) * 7 + ( allPixB[i - 1][ j] ) * 26 + ( allPixB[i][ j] ) * 41 + ( allPixB[i + 1][ j] ) * 26 + ( allPixB[i + 2][ j] ) * 7
                    + ( allPixB[i - 2][ j + 1] ) * 4 + ( allPixB[i - 1][ j + 1] ) * 16 + ( allPixB[i][ j + 1] ) * 26 + ( allPixB[i + 1][ j + 1] ) * 16 + ( allPixB[i + 2][ j + 1] ) * 4
                    + ( allPixB[i - 2][ j + 2] )  + ( allPixB[i - 1][ j + 2] ) * 4 + ( allPixB[i][ j + 2] ) * 7 + ( allPixB[i + 1][ j + 2] ) * 4 + ( allPixB[i + 2][ j + 2] ) ) / 273
                              );
                n.setPixel (i, j, Color.FromArgb (red, green, blue));
            }
        }
        //pictureBox2.Image = n;//////////////////////////////////////////////////////here onward use n///////////////////////////////////////////////
            int[][] allPixRn = new int[width][height];
            int[][] allPixGn = new int[width][height];
            int[][] allPixBn = new int[width][height];

        for ( int i = 0; i < width; i++ )
        {
            for ( int j = 0; j < height; j++ )
            {
                allPixRn[i][ j] = n.getPixel (i, j).R;
                allPixGn[i][ j] = n.getPixel (i, j).G;
                allPixBn[i][ j] = n.getPixel (i, j).B;
            }
        }


            int[][] gx = new int[][] { { -1, 0, 1 }, { -2, 0, 2 }, { -1, 0, 1 } };
            int[][] gy = new int[][] { { 1, 2, 1 }, { 0, 0, 0 }, { -1, -2, -1 } };
        int new_rx = 0, new_ry = 0;
        int new_gx = 0, new_gy = 0;
        int new_bx = 0, new_by = 0;
        int rc, gc, bc;
        int gradR, gradG, gradB;

            int[][] graidientR = new int [width][ height];
            int[][] graidientG = new int [width][ height];
            int[][] graidientB = new int [width][ height];

        int atanR,atanG,atanB;

            int[][] tanR = new int[width][ height];
            int[][] tanG = new int[width][ height];
            int[][] tanB = new int[width][ height];


        for ( int i = 1; i < b.getWidth() - 1; i++ )
        {
            for ( int j = 1; j < b.getHeight() - 1; j++ )
            {

                new_rx = 0;
                new_ry = 0;
                new_gx = 0;
                new_gy = 0;
                new_bx = 0;
                new_by = 0;
                rc = 0;
                gc = 0;
                bc = 0;

                for ( int wi = -1; wi < 2; wi++ )
                {
                    for ( int hw = -1; hw < 2; hw++ )
                    {
                        rc = allPixRn[i + hw ][j + wi];
                        new_rx += gx[wi + 1][ hw + 1] * rc;
                        new_ry += gy[wi + 1][ hw + 1] * rc;

                        gc = allPixGn[i + hw][ j + wi];
                        new_gx += gx[wi + 1][ hw + 1] * gc;
                        new_gy += gy[wi + 1][ hw + 1] * gc;

                        bc = allPixBn[i + hw][ j + wi];
                        new_bx += gx[wi + 1][ hw + 1] * bc;
                        new_by += gy[wi + 1][ hw + 1] * bc;
                    }
                }

                //find gradieant
                gradR = (int)Math.sqrt (( new_rx * new_rx ) + ( new_ry * new_ry ));
                graidientR [i][j]= gradR;

                gradG = (int)Math.sqrt (( new_gx * new_gx ) + ( new_gy * new_gy ));
                graidientG [i][j]= gradG;

                gradB = (int)Math.sqrt (( new_bx * new_gx ) + ( new_by * new_by ));
                graidientB [i][j]= gradB;
                //
                //find tans
                ////////////////tan red//////////////////////////////////
                atanR= (int)( ( Math.atan ((double)new_ry / new_rx) ) * ( 180 / Math.PI ) );
                if ( (atanR > 0 && atanR < 22.5) || (atanR > 157.5 && atanR < 180) )
                {
                    atanR = 0;
                }
                else if (atanR > 22.5 && atanR < 67.5){
                    atanR = 45;
                }
                else if (atanR > 67.5 && atanR < 112.5){
                    atanR = 90;
                }
                else if (atanR > 112.5 && atanR < 157.5) {
                    atanR = 135;
                }

                if ( atanR == 0 )
                {
                    tanR[i][ j] = 0;
                }
                else if ( atanR == 45 )
                {
                    tanR[i][ j] = 1;
                }
                else if ( atanR == 90 )
                {
                    tanR[i][ j] = 2;
                }
                else if ( atanR == 135 )
                {
                    tanR[i][ j] = 3;
                }
                ////////////////tan red end//////////////////////////////////

                ////////////////tan green//////////////////////////////////
                atanG = (int)( ( Math.atan ((double)new_gy / new_gx) ) * ( 180 / Math.PI ) );
                if ( ( atanG > 0 && atanG < 22.5 ) || ( atanG > 157.5 && atanG < 180 ) )
                {
                    atanG = 0;
                }
                else if ( atanG > 22.5 && atanG < 67.5 )
                {
                    atanG = 45;
                }
                else if ( atanG > 67.5 && atanG < 112.5 )
                {
                    atanG = 90;
                }
                else if ( atanG > 112.5 && atanG < 157.5 )
                {
                    atanG = 135;
                }


                if (atanG == 0){
                    tanG[i][ j] = 0;
                }
                else if (atanG == 45) {
                    tanG[i][ j] = 1;
                }
                else if ( atanG == 90 )
                {
                    tanG[i][ j] = 2;
                }
                else if ( atanG == 135 )
                {
                    tanG[i][ j] = 3;
                }
                ////////////////tan green end//////////////////////////////////


                ////////////////tan blue//////////////////////////////////
                atanB = (int)( ( Math.atan ((double)new_by / new_bx) ) * ( 180 / Math.PI ) );
                if ( ( atanB > 0 && atanB < 22.5 ) || ( atanB > 157.5 && atanB < 180 ) )
                {
                    atanB = 0;
                }
                else if ( atanB > 22.5 && atanB < 67.5 )
                {
                    atanB = 45;
                }
                else if ( atanB > 67.5 && atanB < 112.5 )
                {
                    atanB = 90;
                }
                else if ( atanB > 112.5 && atanB < 157.5 )
                {
                    atanB = 135;
                }

                if ( atanB == 0 )
                {
                    tanB[i][ j] = 0;
                }
                else if ( atanB == 45 )
                {
                    tanB[i][ j] = 1;
                }
                else if ( atanB == 90 )
                {
                    tanB[i][ j] = 2;
                }
                else if ( atanB == 135 )
                {
                    tanB[i][ j] = 3;
                }
                ////////////////tan blue end//////////////////////////////////
            }
        }

            int[][] allPixRs = new int[width][ height];
            int[][] allPixGs = new int[width][ height];
            int[][] allPixBs = new int[width][ height];

        for ( int i = 2; i < width-2; i++ )
        {
            for ( int j = 2; j < height-2; j++ )
            {

                ////red
                if ( tanR[i][ j] == 0 )
                {
                    if ( graidientR[i - 1][ j] < graidientR[i][ j] && graidientR[i + 1][ j] < graidientR[i][ j] )
                    {
                        allPixRs[i][ j] = graidientR[i][ j];
                    }
                        else {
                    allPixRs[i][j] = 0;
                }
                }
                if ( tanR[i][ j] == 1 )
                {
                    if ( graidientR[i - 1][ j + 1] < graidientR[i][ j] && graidientR[i + 1][ j - 1] < graidientR[i][ j] )
                    {
                        allPixRs[i][ j] = graidientR[i][ j];
                    }
                        else
                    {
                        allPixRs[i][ j] = 0;
                    }
                }
                if ( tanR[i][ j] == 2 )
                {
                    if ( graidientR[i][ j - 1] < graidientR[i][ j] && graidientR[i][ j + 1] < graidientR[i][ j] )
                    {
                        allPixRs[i][ j] = graidientR[i][ j];
                    }
                        else
                    {
                        allPixRs[i][ j] = 0;
                    }
                }
                if ( tanR[i][ j] == 3 )
                {
                    if ( graidientR[i - 1][ j - 1] < graidientR[i][ j] && graidientR[i + 1][ j + 1] < graidientR[i][ j] )
                    {
                        allPixRs[i][ j] = graidientR[i][ j];
                    }
                        else
                    {
                        allPixRs[i][ j] = 0;
                    }
                }

                //green
                if ( tanG[i][ j] == 0 )
                {
                    if ( graidientG[i - 1][ j] < graidientG[i][ j] && graidientG[i + 1][ j] < graidientG[i][ j] )
                    {
                        allPixGs[i][ j] = graidientG[i][ j];
                    }
                        else
                    {
                        allPixGs[i][ j] = 0;
                    }
                }
                if ( tanG[i][ j] == 1 )
                {
                    if ( graidientG[i - 1][ j + 1] < graidientG[i][ j] && graidientG[i + 1][ j - 1] < graidientG[i][ j] )
                    {
                        allPixGs[i][ j] = graidientG[i][ j];
                    }
                        else
                    {
                        allPixGs[i][ j] = 0;
                    }
                }
                if ( tanG[i][ j] == 2 )
                {
                    if ( graidientG[i][ j - 1] < graidientG[i][ j] && graidientG[i][ j + 1] < graidientG[i][ j] )
                    {
                        allPixGs[i][ j] = graidientG[i][ j];
                    }
                        else
                    {
                        allPixGs[i][ j] = 0;
                    }
                }
                if ( tanG[i][ j] == 3 )
                {
                    if ( graidientG[i - 1][ j - 1] < graidientG[i][ j] && graidientG[i + 1][ j + 1] < graidientG[i][ j] )
                    {
                        allPixGs[i][ j] = graidientG[i][ j];
                    }
                        else
                    {
                        allPixGs[i][ j] = 0;
                    }
                }

                //blue
                if ( tanB[i][ j] == 0 )
                {
                    if ( graidientB[i - 1][ j] < graidientB[i][ j] && graidientB[i + 1][ j] < graidientB[i][ j] )
                    {
                        allPixBs[i][ j] = graidientB[i][ j];
                    }
                        else
                    {
                        allPixBs[i][ j] = 0;
                    }
                }
                if ( tanB[i][ j] == 1 )
                {
                    if ( graidientB[i - 1][ j + 1] < graidientB[i][ j] && graidientB[i + 1][ j - 1] < graidientB[i][ j] )
                    {
                        allPixBs[i][ j] = graidientB[i][ j];
                    }
                        else
                    {
                        allPixBs[i][ j] = 0;
                    }
                }
                if ( tanB[i][ j] == 2 )
                {
                    if ( graidientB[i][ j - 1] < graidientB[i][ j] && graidientB[i][ j + 1] < graidientB[i][ j] )
                    {
                        allPixBs[i][ j] = graidientB[i][ j];
                    }
                        else
                    {
                        allPixBs[i][ j] = 0;
                    }
                }
                if ( tanB[i][ j] == 3 )
                {
                    if ( graidientB[i - 1][ j - 1] < graidientB[i][ j] && graidientB[i + 1][ j + 1] < graidientB[i][ j] )
                    {
                        allPixBs[i][ j] = graidientB[i][ j];
                    }
                        else
                    {
                        allPixBs[i][ j] = 0;
                    }
                }
            }
        }

        int threshold = 80;
            int[][] allPixRf = new int[width][ height];
            int[][] allPixGf = new int[width][ height];
            int[][] allPixBf = new int[width][ height];

        // Bitmap bb = new Bitmap (pictureBox1.Image);
        Bitmap bb = Bitmap.createBitmap(Imagem,0,0,width,height);

        for (int i = 2; i <width-2 ;i++){
            for ( int j = 2; j < height-2;j++ )
            {
                if ( allPixRs[i][ j] > threshold )
                {
                    allPixRf[i][ j] = 1;
                }
                    else {
                allPixRf[i][ j] = 0;
            }

                if ( allPixGs[i][ j] > threshold )
                {
                    allPixGf[i][ j] = 1;
                }
                    else
                {
                    allPixGf[i][ j] = 0;
                }

                if ( allPixBs[i][ j] > threshold )
                {
                    allPixBf[i][ j] = 1;
                }
                    else
                {
                    allPixBf[i][ j] = 0;
                }



                if ( allPixRf[i][ j] == 1 || allPixGf[i][ j] == 1 || allPixBf[i][ j] == 1 )
                {
                    bb.setPixel (i, j, Color.Black);
                }
                    else
                bb.setPixel (i, j, Color.White);
            }
        }
        return bb;
    }



    private class TaskMirror extends AsyncTask <Void , Void , Void>{

        @Override
        protected Void doInBackground(Void... params) {
            b = mirrorFilterAlgorithm(bmp);
            return null;
        }
    }


    private class TaskMedian extends  AsyncTask <Void,Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            b = medianFilterAlgorithm(bmp);
            return null;
        }
    }

}


