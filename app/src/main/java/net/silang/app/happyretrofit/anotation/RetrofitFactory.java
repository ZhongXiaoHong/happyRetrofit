package net.silang.app.happyretrofit.anotation;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    public static Retrofit getInstance(Class  clazz){

        Retrofit retrofit;
        if(clazz == MallHost.class){
            retrofit = new Retrofit.Builder().baseUrl("")
                .addConverterFactory(GsonConverterFactory.create()).build();
        }else if(clazz == CommunityHost.class){
            retrofit = new Retrofit.Builder().baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }else if(clazz == OssHost.class){
            retrofit = new Retrofit.Builder().baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }else{
            retrofit = new Retrofit.Builder().baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
