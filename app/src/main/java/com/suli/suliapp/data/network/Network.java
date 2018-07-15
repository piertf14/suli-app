package com.suli.suliapp.data.network;

import com.google.gson.GsonBuilder;
import com.suli.suliapp.data.models.AccessTokenResponse;
import com.suli.suliapp.data.models.ProjectResponse;
import com.suli.suliapp.data.models.post.request.CustodyChainRequest;
import com.suli.suliapp.data.models.post.response.AgentResponse;
import com.suli.suliapp.data.models.post.response.CustodyChainResponse;
import com.suli.suliapp.data.models.post.response.InstrumentResponse;
import com.suli.suliapp.data.models.post.response.MeasurementValueResponse;


import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Streaming;


public class Network {

    public static final String API_URL = "https://suli-api.herokuapp.com";
    //public static final String API_URL = "https://bd7b3fd5.ngrok.io";
    public static final String CLIENT_ID = "sdMPqIjltBq6FjhP5dncNSmNCnH4smh4v8wjfbCx";
    public static final String CLIENT_SECRET = "kyy1xC3DKmUJgo2f7pzrxJYJWrbIjcqzJSjBpRQ545Qec0tNyW0YgG3OB5QE06Q8pxD00hAozO29smVQw27jfDtQKlH1s0uN0MXHayeb0OCTb9C5fYBGMOVBMyPBarPQ";
    public static final String GRANT_TYPE = "password";
    public static final String SCOPE = "read+write";

    public static SuliService service;

    public static SuliService getService() {
        if (service == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .client(getOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .build();
            service = retrofit.create(SuliService.class);
        }
        return service;
    }

    public static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);// set your desired log level
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();// add your other interceptors … // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        return httpClient.build();
    }

    public interface SuliService {

        @FormUrlEncoded
        @POST("o/token/")
        Call<AccessTokenResponse> postAuth(
                @Field("client_id") String clientId,
                @Field("client_secret") String clientSecret,
                @Field("grant_type") String grantType,
                @Field("username") String username,
                @Field("password") String password
        );

        @GET("project")
        Call<List<ProjectResponse>> getProjects(@Header("Authorization") String header);

        @POST("custody-chain/")
        Call<CustodyChainResponse> postCustodyChain(@Header("Authorization") String header, @Body CustodyChainRequest body);

        @GET("instrument")
        Call<List<InstrumentResponse>> getInstruments(@Header("Authorization") String header);

        @GET("agent")
        Call<List<AgentResponse>> getAgents(@Header("Authorization") String header);

        @Streaming
        @Multipart
        @POST("v2/measurement-value/")
        Call<MeasurementValueResponse> postMeasurementValue(
                @Header("Authorization") String header,
                @Part("chain_custody") RequestBody custodyChain,
                @Part("max") RequestBody max,
                @Part("min") RequestBody min,
                @Part("avg") RequestBody avg,
                @Part("point_reference") RequestBody referencePoint,
                @Part("observation_measurement") RequestBody observationMeasurement,
                @Part("type_lighting") RequestBody typeLighting,
                //@Part("images") RequestBody image
                @Part MultipartBody.Part image
        );
    }


}
