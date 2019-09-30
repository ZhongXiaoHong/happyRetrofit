package net.silang.app.happyretrofit.anotation;

import java.lang.annotation.Annotation;
import java.util.HashMap;

import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static HashMap<Class, Retrofit> retrofitMap = new HashMap<>();
    private static HashMap<Class, Retrofit> cacheRetrofitMap = new HashMap<>();//缓存

    private static Retrofit defaultRetrofit;


    public HashMap<Class, Retrofit> getRetrofitMap() {
        return retrofitMap;
    }

    public void setRetrofitMap(HashMap<Class, Retrofit> retrofitMap) {
        this.retrofitMap = retrofitMap;
    }

    public Retrofit getDefaultRetrofit() {
        return defaultRetrofit;
    }

    public void setDefaultRetrofit(Retrofit defaultRetrofit) {
        this.defaultRetrofit = defaultRetrofit;
    }

    public static class Builder {

        ServiceGenerator mServiceGenerator;

        public Builder() {
            mServiceGenerator = new ServiceGenerator();
        }

        public Builder addRetrofit(Class clazz, Retrofit retrofit, boolean asDefaultRetrofit) {
            mServiceGenerator.getRetrofitMap().put(clazz, retrofit);

            if (asDefaultRetrofit) {
                mServiceGenerator.setDefaultRetrofit(retrofit);
            }

            return this;
        }

        public Builder addRetrofit(Class clazz, Retrofit retrofit) {
            addRetrofit(clazz, retrofit, false);
            return this;
        }


        public ServiceGenerator build() {
            return mServiceGenerator;
        }


    }


    public <S> S creatService(Class<S> clazz) {

        if (clazz == null) {
            throw new RuntimeException("retofit api  interface class object disallow null");
        }

        Retrofit retrofit = null;

        synchronized (cacheRetrofitMap){
            if (cacheRetrofitMap.containsKey(clazz)) {
                retrofit = cacheRetrofitMap.get(clazz);
            } else {
                Annotation[] annotations = clazz.getAnnotations();
                //api interface 没有任何注解,使用默认Retrofit
                if (annotations == null || annotations.length == 0) {
                    retrofit = defaultRetrofit;
                } else {
                    for (Annotation annotation : annotations) {
                        if (annotation != null) {
                            if (retrofitMap.containsKey(annotation.getClass())) {
                                retrofit = retrofitMap.get(annotation.getClass());
                                //将clazz对应的retrofit缓存起来避免每一次都反射查找，提升性能
                                cacheRetrofitMap.put(clazz, retrofit);
                                break;
                            }
                        }

                    }
                }
            }
        }



        if (retrofit == null) {
            throw new RuntimeException(" retofit is null  can not be used");
        }


        return retrofit.create(clazz);
    }
}
