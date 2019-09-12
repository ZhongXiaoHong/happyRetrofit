package net.silang.app.happyretrofit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import net.silang.app.happyretrofit.api.PropertyListService;
import net.silang.app.happyretrofit.api.UserInfo;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private String baseUrl = "http://192.168.31.176:8080/retrofitServer2/";

    TextView testgetTv, testgetByNameTv, testgetByIdTv, testgetByIdAndNameTv, testgetByIdAndNameTvPost, testgetByIdAndNameTvPostForm, testUploadFileOnly, uploadFileAndText;

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


    }

    public void testGet(View v) {
        testgetTv.setText("");

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
        PropertyListService mPropertyListService = mRetrofit.create(PropertyListService.class);
        Call<PropertyList> call = mPropertyListService.propertyList();
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


}
