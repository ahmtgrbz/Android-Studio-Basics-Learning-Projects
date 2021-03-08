package com.example.imagedownloader2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    EditText txtURL;
    Button btnDownload;
    ImageView imgView;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
    Manifest.permission.WRITE_EXTERNAL_STORAGE};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        txtURL = (EditText) findViewById(R.id.txtURL);
        btnDownload = (Button) findViewById(R.id.btnDownload);
        imgView = (ImageView) findViewById(R.id.imgView);


        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permission = ActivityCompat.checkSelfPermission(
                        MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                );
                if(permission != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE);
                }else{

                    Thread backgroundThread =new Thread(new DownloadRunnable(txtURL.getText().toString()));
                    backgroundThread.start();

                   /* AsyncTask backgroundTask = new DownloadTask();
                String[] urls = new String[1];
                urls[0]= txtURL.getText().toString();
                backgroundTask.execute(urls);*/
            } }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==REQUEST_EXTERNAL_STORAGE){
            if(grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED &&
            grantResults[1]== PackageManager.PERMISSION_GRANTED){
                Thread backgroundThread =new Thread(new DownloadRunnable(txtURL.getText().toString()));
                backgroundThread.start();
              /*  AsyncTask backgroundTask = new DownloadTask();
                String[] urls = new String[1];
                urls[0]= txtURL.getText().toString();
                backgroundTask.execute(urls);*/
            }
        }
    }

    private void downloadFile(String urlString, String imagePath) {
       try{
           URL url = new URL(urlString);
           URLConnection connection = url.openConnection();
           connection.connect();
           int filesize = connection.getContentLength();

           InputStream is = new BufferedInputStream(url.openStream(),8192);
           OutputStream os = new FileOutputStream(imagePath);

           byte data[] = new byte[1024];
           int total =0;
           int count;
           while((count = is.read(data)) != -1 ){
               os.write(data,0,count );
               total += count;
               int percentage = (total*100/filesize);

           }

           os.flush();
           os.close();
           is.close();

       }catch (Exception ex){
            ex.printStackTrace();
       }
    }

    class DownloadTask extends AsyncTask<String,Integer,Bitmap>{

        ProgressDialog PD;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        PD =new ProgressDialog(MainActivity.this);
        PD.setMax(100);
        PD.setIndeterminate(false);
        PD.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        PD.setTitle("Download");
        PD.setMessage("Please Wait");
       PD.show();
        }

        @Override
        protected Bitmap doInBackground(String... strs) {
            Log.d("Download Task ",strs[0]);
            String imagePath =(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString()+"/temp.jpg";
            downloadFile(txtURL.getText().toString(),imagePath);
            downloadFile2(strs[0],imagePath);
            Bitmap image = BitmapFactory.decodeFile(imagePath);
            float w = image.getWidth();
            float h = image.getHeight();
            int W = 400;
            int H = (int) ( (h*W)/w);
            Bitmap b = Bitmap.createScaledBitmap(image, W, H, false);



            return b;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.d("on Post Execute", "set image ");
            super.onPostExecute(bitmap);
            imgView.setImageBitmap(bitmap);
            PD.dismiss();
        }



        private void downloadFile2(String urlString, String imagePath) {
            try{
                URL url = new URL(urlString);
                URLConnection connection = url.openConnection();
                connection.connect();
                int filesize = connection.getContentLength();

                InputStream is = new BufferedInputStream(url.openStream(),8192);
                OutputStream os = new FileOutputStream(imagePath);

                byte data[] = new byte[1024];
                int total =0;
                int count;
                while((count = is.read(data)) != -1 ){
                    os.write(data,0,count );
                    total += count;
                    int percentage = (total*100/filesize);
                    publishProgress(percentage);

                }

                os.flush();
                os.close();
                is.close();

            }catch (Exception ex){
                ex.printStackTrace();
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            PD.setProgress(values[0]);
        }
    }

    class DownloadRunnable implements Runnable{
    String url;

        public DownloadRunnable( String url) {

            this.url = url;
        }
        @Override
        public void run() {
            Log.d("Download Task ",url);
            String imagePath =(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)).toString()+"/temp.jpg";
            downloadFile(txtURL.getText().toString(),imagePath);
            downloadFile(url,imagePath);
            Bitmap image = BitmapFactory.decodeFile(imagePath);
            float w = image.getWidth();
            float h = image.getHeight();
            int W = 400;
            int H = (int) ( (h*W)/w);
            final Bitmap b = Bitmap.createScaledBitmap(image, W, H, false);
            runOnUiThread(new UpdateBitmap(b));
        }}

    class UpdateBitmap implements  Runnable{
            Bitmap bitmap;
        @Override
        public void run() {
            imgView.setImageBitmap(bitmap);
        }

        public UpdateBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
        }
    }

}