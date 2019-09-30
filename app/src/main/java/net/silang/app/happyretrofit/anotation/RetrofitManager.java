package net.silang.app.happyretrofit.anotation;

public class RetrofitManager {

    private ServiceGenerator mServiceGenerator;
    private RetrofitManager(){
        mServiceGenerator = new ServiceGenerator.Builder()
                .addRetrofit(MallHost.class, RetrofitFactory.getInstance(MallHost.class))
                .addRetrofit(OssHost.class, RetrofitFactory.getInstance(OssHost.class))
                .addRetrofit(CommunityHost.class, RetrofitFactory.getInstance(CommunityHost.class))
                .build();
    }

    //静态内部类单例
    private static class Holder{
       static RetrofitManager  INSTANCE = new RetrofitManager();

       public static  RetrofitManager getInstance(){
           return INSTANCE;
       }
    }

    public <S> S creatService(Class<S>  clazz){
        if(mServiceGenerator!=null){
          return mServiceGenerator.creatService(clazz);
        }

        return null;
    }



}
