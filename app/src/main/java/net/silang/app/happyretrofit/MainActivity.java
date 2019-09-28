package net.silang.app.happyretrofit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import net.silang.app.happyretrofit.api.PropertyListService;
import net.silang.app.happyretrofit.api.UserInfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private String baseUrl = "http://192.168.31.176:8080/retrofitServer2/";

    TextView testgetTv, testgetByNameTv, testgetByIdTv, testgetByIdAndNameTv, testgetByIdAndNameTvPost, testgetByIdAndNameTvPostForm, testUploadFileOnly, uploadFileAndText,uploadOneFile2;
    TextView downloadFileWithFixedUrl,downloadFileWithDynamicUrlSync,downloadFileWithDynamicUrlAsync;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testgetTv = findViewById(R.id.testgetTv);
        testgetByNameTv = findViewById(R.id.testgetByNameTv);
        testgetByIdTv = findViewById(R.id.testgetByIdTv);
        testgetByIdAndNameTv = findViewById(R.id.testgetByIdAndNameTv);
        testgetByIdAndNameTvPost = findViewById(R.id.testgetByIdAndNameTvPost);
        testgetByIdAndNameTvPostForm = findViewById(R.id.testgetByIdAndNameTvPostForm);
        testUploadFileOnly = findViewById(R.id.testUploadFileOnly);
        uploadFileAndText = findViewById(R.id.uploadFileAndText);
        uploadOneFile2 = findViewById(R.id.uploadOneFile2);
        downloadFileWithFixedUrl = findViewById(R.id.downloadFileWithFixedUrl);
        downloadFileWithDynamicUrlSync = findViewById(R.id.downloadFileWithDynamicUrlSync);

        downloadFileWithDynamicUrlAsync= findViewById(R.id.downloadFileWithDynamicUrlAsync);


    }

    public void testGet(View v) throws IOException {
        testgetTv.setText("");

        //1.创建Retrofit对象
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        //2.创建Api接口实例
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);
        //3.创建Call对象
        Call<PropertyList> call = mPropertyListService.propertyList();
        //4.调用Call的enqueue方法
        call.enqueue(new Callback<PropertyList>() {
            @Override
            public void onResponse(Call<PropertyList> call, Response<PropertyList> response) {
                testgetTv.setText(new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PropertyList> call, Throwable t) {

            }
        });


    }


    public void testGetbyName(View v) {
        testgetByNameTv.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);
        Call<PropertyList> call = mPropertyListService.propertyListByName("silang1");
        call.enqueue(new Callback<PropertyList>() {
            @Override
            public void onResponse(Call<PropertyList> call, Response<PropertyList> response) {
                testgetByNameTv.setText(new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PropertyList> call, Throwable t) {

            }
        });

    }


    public void testGetbyId(View v) {
        testgetByIdTv.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);
        Call<PropertyList> call = mPropertyListService.propertyListById("1");
        call.enqueue(new Callback<PropertyList>() {
            @Override
            public void onResponse(Call<PropertyList> call, Response<PropertyList> response) {
                testgetByIdTv.setText(new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PropertyList> call, Throwable t) {

            }
        });

    }


    public void testGetbyIdAndName(View v) {
        testgetByIdAndNameTv.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "2");
        map.put("name", "silang-2");
        Call<PropertyList> call = mPropertyListService.propertyListByIdAndName(map);
        call.enqueue(new Callback<PropertyList>() {
            @Override
            public void onResponse(Call<PropertyList> call, Response<PropertyList> response) {
                testgetByIdAndNameTv.setText(new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PropertyList> call, Throwable t) {

            }
        });

    }


    public void testGetbyIdAndNamePost(View v) {
        testgetByIdAndNameTvPost.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);

        Call<PropertyList> call = mPropertyListService.propertyListByIdAndNamePost(new UserInfo("3", "silang-3"));
        call.enqueue(new Callback<PropertyList>() {
            @Override
            public void onResponse(Call<PropertyList> call, Response<PropertyList> response) {
                testgetByIdAndNameTvPost.setText(new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PropertyList> call, Throwable t) {

            }
        });

    }

    public void testGetbyIdAndNamePostForm(View v) {
        testgetByIdAndNameTvPostForm.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);

        Call<PropertyList> call = mPropertyListService.propertyListByIdAndNamePostForm("4", "silang-4");
        call.enqueue(new Callback<PropertyList>() {
            @Override
            public void onResponse(Call<PropertyList> call, Response<PropertyList> response) {
                testgetByIdAndNameTvPostForm.setText(new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<PropertyList> call, Throwable t) {

            }
        });

    }


    public void testUploadFileOnly(View view) {
        testUploadFileOnly.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);

        RequestBody boby = RequestBody.create(MediaType.parse("image/png"), baos.toByteArray());


        Call<Message> call = mPropertyListService.uploadFile(boby);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                testUploadFileOnly.setText(new Gson().toJson(response.body().getMsg()));
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });


    }


    public void uploadFileAndText(View view) {


        uploadFileAndText.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        RequestBody boby = RequestBody.create(MediaType.parse("image/png"), baos.toByteArray());

        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.mipmap.meinv);
        ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
        bm2.compress(Bitmap.CompressFormat.JPEG, 100, baos2);
        RequestBody boby2 = RequestBody.create(MediaType.parse("image/jpeg"), baos2.toByteArray());

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("file\"; filename=\"" + "iclauncher.png", boby);
        map.put("file\"; filename=\"" + "meinv.jpeg", boby2);

        RequestBody content = RequestBody.create(MediaType.parse("text/plain;charset=UTF-8"), "TMD  橡树园A2 栋门禁开不了门！！！");
        map.put("content", content);
        Call<Message> call = mPropertyListService.uploadFileAndText(map);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {

                uploadFileAndText.setText(response.body().getMsg());
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

    public void  uploadOneFile2(View view){
        uploadOneFile2.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.mipmap.jisa);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        RequestBody boby = RequestBody.create(MediaType.parse("image/jpeg"), baos.toByteArray());

        MultipartBody.Part part = MultipartBody.Part.createFormData("jisa","jisa.jpg",boby);

        Call<Message> call = mPropertyListService.uploadOneFile2(part);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                testUploadFileOnly.setText(new Gson().toJson(response.body().getMsg()));
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });

    }


    public void downloadFileWithFixedUrl(View   view){

        downloadFileWithFixedUrl.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);
        Call<ResponseBody> call = mPropertyListService.downloadFileWithFixedUrl();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    downloadFileWithFixedUrl.setText("server contacted and has file");

                    boolean writtenToDisk = writeResponseBodyToDisk(response.body());

                    Log.d("MainActivity", "file download was a success? " + writtenToDisk);
                } else {
                    Log.d("MainActivity", "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }



    public void downloadFileWithDynamicUrlSync(View   view){
        downloadFileWithDynamicUrlSync.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);
        Call<ResponseBody> call = mPropertyListService.downloadFileWithDynamicUrlSync("https://raw.githubusercontent.com/ZhongXiaoHong/superFileView/master/doc.png");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    downloadFileWithDynamicUrlSync.setText("server contacted and has file");

                    boolean writtenToDisk = writeResponseBodyToDisk(response.body());

                    Log.d("MainActivity", "file download was a success? " + writtenToDisk);
                } else {
                    Log.d("MainActivity", "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public void downloadFileWithDynamicUrlAsync(View   view){
        downloadFileWithDynamicUrlAsync.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);
        Call<ResponseBody> call = mPropertyListService.downloadFileWithDynamicUrlAsync("https://raw.githubusercontent.com/ZhongXiaoHong/superFileView/master/doc.png");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    downloadFileWithDynamicUrlAsync.setText("server contacted and has file");

                    new AsyncTask<Void, Void, Void>() {
                        @Override
                        protected Void doInBackground(Void... voids) {
                            boolean writtenToDisk = writeResponseBodyToDisk( response.body());

                            Log.d("MainActivity", "file download was a success? " + writtenToDisk);
                            return null;
                        }
                    }.execute();
                }
                else {
                    Log.d("MainActivity", "server contact failed");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(getExternalFilesDir(null) + File.separator + "Future Studio Icon.png");

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("MainActivity", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }


}
