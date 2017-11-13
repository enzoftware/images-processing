package com.imageprocessing.enzoftware.imageprocessingapp;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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
    ImageButton picker, output;
    Button medianFilter, mirrorFilter, cannyFilter;
    ProgressBar scrollView;
    private static int RESULT_LOAD_IMG = 1;
    String impDeclarableString;
    public Bitmap bmp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        picker = (ImageButton) findViewById(R.id.imageButton);
        medianFilter = (Button) findViewById(R.id.medianFilter);
        output = (ImageButton) findViewById(R.id.outputImage);
        mirrorFilter = (Button) findViewById(R.id.mirrorFilter);
        scrollView = (ProgressBar) findViewById(R.id.scrollView);
        cannyFilter = (Button) findViewById(R.id.cannyFilter);


        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadImageryGallery();

            }
        });

        medianFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bmp != null) {
                    output.setImageBitmap(medianFilterAlgorithm(bmp));
                    saveImage(bmp, "fotazo");
                } else {
                    Toast.makeText(getApplicationContext(), "No has seleccionado ninguna foto", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


        cannyFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bmp != null) {
                    cannyFilterAlg(bmp);
                } else {
                    Toast.makeText(getApplicationContext(), "No has seleccionado ninguna foto", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });


        mirrorFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (bmp != null) {
                    output.setImageBitmap(mirrorFilterAlgorithm(bmp));
                    saveImage(bmp, "fotazo");
                } else {
                    Toast.makeText(getApplicationContext(), "No has seleccionado ninguna foto", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });

    }



    public void loadImageryGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
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
                final Bitmap resizable = Bitmap.createScaledBitmap(b, 714, 438, false);
                bmp = Bitmap.createScaledBitmap(resizable, 714, 438, false);

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
        String fname = "Image-" + image_name + ".jpg";
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

    private Bitmap medianFilterAlgorithm(Bitmap bitmap) {
        int[] pixel = new int[9];
        int[] R = new int[9];
        int[] G = new int[9];
        int[] B = new int[9];
        Bitmap out = bitmap;
        for (int i = 1; i < bitmap.getWidth() - 1; i++) {
            for (int j = 1; j < bitmap.getHeight() - 1; j++) {

                pixel[0] = bitmap.getPixel(i - 1, j - 1);
                pixel[1] = bitmap.getPixel(i - 1, j);
                pixel[2] = bitmap.getPixel(i - 1, j + 1);
                pixel[3] = bitmap.getPixel(i, j + 1);
                pixel[4] = bitmap.getPixel(i + 1, j + 1);
                pixel[5] = bitmap.getPixel(i + 1, j);
                pixel[6] = bitmap.getPixel(i + 1, j - 1);
                pixel[7] = bitmap.getPixel(i, j - 1);
                pixel[8] = bitmap.getPixel(i, j);

                for (int k = 0; k < 9; k++) {
                    R[k] = Color.red(pixel[k]);
                    G[k] = Color.green(pixel[k]);
                    B[k] = Color.blue(pixel[k]);
                }

                Arrays.sort(R);
                Arrays.sort(G);
                Arrays.sort(B);
                out.setPixel(i, j, Color.rgb(R[4], G[4], B[4]));
            }
        }
        return out;
    }

    private Bitmap mirrorFilterAlgorithm(Bitmap photo) {
        int r, g, b;
        int r1, g1, b1;
        int width = photo.getWidth() - 1;
        int color;
        int color1;
        Bitmap out = photo;
        for (int i = 0; i < (photo.getWidth()) / 2; i++) {
            for (int j = 0; j < photo.getHeight(); j++) {

                color1 = photo.getPixel(i, j);
                color = photo.getPixel(width, j);

                r = Color.red(color);
                g = Color.green(color);
                b = Color.blue(color);

                r1 = Color.red(color1);
                g1 = Color.green(color1);
                b1 = Color.blue(color1);

                out.setPixel(i, j, Color.rgb(r, g, b));
                out.setPixel(width, j, Color.rgb(r1, g1, b1));
            }
            width--;
        }
        Log.d("FUNCIONA", "FUNCIONA");
        return out;
    }


    private Bitmap cannyFilterAlg(Bitmap Image) {
        Canny myCanny = new Canny();
        output.setImageBitmap(myCanny.process(Image));
        return Image;
    }


}



