package net.silang.app.happyretrofit.api;

import net.silang.app.happyretrofit.Message;
import net.silang.app.happyretrofit.PropertyList;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PropertyListService {

    @GET("PropertyListServlet")//http://localhost:8080/retrofitServer2/PropertyListServlet
    Call<PropertyList> propertyList();

    @GET("PropertyServlet/{name}/property") //http://localhost:8080/retrofitServer2/PropertyServlet/silang1/property
    Call<PropertyList> propertyListByName(@Path("name")String name);

    @GET("PropertyListServlet")//http://localhost:8080/retrofitServer2/PropertyListServlet?id=1
    Call<PropertyList> propertyListById(@Query("id")String id);

    @GET("PropertyListServlet")//http://localhost:8080/retrofitServer2/PropertyListServlet?id=2&&name=silang-2
    Call<PropertyList> propertyListByIdAndName(@QueryMap Map<String,String> params);


    @POST("PropertyListServlet")
    Call<PropertyList> propertyListByIdAndNamePost(@Body UserInfo info);


    @FormUrlEncoded
    @POST("PropertyListPostFormUrlencodedServlet")
    Call<PropertyList> propertyListByIdAndNamePostForm(@Field("id") String id,@Field("name") String name);


    @Multipart()
    @POST("UploadServlet")
    Call<Message> uploadFile( @Part("file\";filename=\"test.jpg") RequestBody photo);

    @Multipart()
    @POST("UploadServlet")
    Call<Message> uploadFileAndText(@PartMap Map<String,RequestBody> map);
}
