package cool.location.petproject.http;

import cool.location.petproject.bean.TranslateResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpointService {

    @GET("translate")
    Call<TranslateResponse> translate(@Query("q") String q, @Query("from") String from, @Query("to") String to, @Query("appid") String appid, @Query("salt") String salt, @Query("sign") String sign);
}
