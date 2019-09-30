package net.silang.app.happyretrofit.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Son extends  Father {

    @GET
    Call<ResponseBody>  getBookInfoList(@Url String  url);
}
