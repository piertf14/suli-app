package com.suli.suliapp.ui.utils;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.suli.suliapp.data.models.post.request.CustodyChainRequest;
import com.suli.suliapp.data.models.post.request.MeasurementValueRequest;
import com.suli.suliapp.data.models.post.response.AgentResponse;
import com.suli.suliapp.data.models.post.response.CustodyChainResponse;
import com.suli.suliapp.data.models.post.response.InstrumentResponse;
import com.suli.suliapp.data.models.post.response.MeasurementValueResponse;
import com.suli.suliapp.data.preferences.PreferencesManager;
import com.suli.suliapp.ui.callbacks.GetAgentsCallback;
import com.suli.suliapp.ui.callbacks.GetInstrumentsCallback;
import com.suli.suliapp.ui.callbacks.GetProjectsCallback;
import com.suli.suliapp.ui.callbacks.LoginCallback;
import com.suli.suliapp.data.models.AccessTokenResponse;
import com.suli.suliapp.data.models.ProjectResponse;
import com.suli.suliapp.data.network.Network;
import com.suli.suliapp.ui.callbacks.PostCustodyChainCallback;
import com.suli.suliapp.ui.callbacks.PostMeasurementValueCallback;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Utils {

    private Context context;
    private PreferencesManager preferencesManager;

    public Utils(Context context) {
        this.context = context;
        preferencesManager = new PreferencesManager(context);
    }

    public void postAuth(String username, String password, final LoginCallback callback) {
        Network.getService().postAuth(Network.CLIENT_ID, Network.CLIENT_SECRET,
                Network.GRANT_TYPE, username, password).enqueue(new Callback<AccessTokenResponse>() {
            @Override
            public void onResponse(Call<AccessTokenResponse> call, Response<AccessTokenResponse> response) {
                if (response.isSuccessful()) {
                    AccessTokenResponse accessToken = response.body();
                    if (accessToken != null) {
                        saveAccessToken(accessToken);
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFailure("Ocurrió un error, vuelva a intentarlo.");
                    }
                } else {
                    callback.onFailure("Ocurrió un error, vuelva a intentarlo.");
                }
            }

            @Override
            public void onFailure(Call<AccessTokenResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getProjects(final GetProjectsCallback callback) {
        String authorization = "Bearer " + getAccessToken();
        Network.getService().getProjects(authorization).enqueue(new Callback<List<ProjectResponse>>() {
            @Override
            public void onResponse(Call<List<ProjectResponse>> call, Response<List<ProjectResponse>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    switch (response.code()) {
                        case 401:
                            callback.onFailure("La sesión expira, por favor vuelve a iniciar sesión");
                        default:
                            callback.onFailure("Ocurrió un error, vuelva a intentarlo.");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ProjectResponse>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    private void saveAccessToken(AccessTokenResponse response) {
        preferencesManager.saveAccessToken(response.getAccessToken());
    }

    public Boolean isLogged() {
        return preferencesManager.isLogged();
    }

    public String getAccessToken() {
        return preferencesManager.getAccessToken();
    }

    public void logout() {
        preferencesManager.logout();
    }

    public void getAgents(final GetAgentsCallback callback) {
        String authorization = "Bearer " + getAccessToken();
        Network.getService().getAgents(authorization).enqueue(new Callback<List<AgentResponse>>() {
            @Override
            public void onResponse(Call<List<AgentResponse>> call, Response<List<AgentResponse>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Ocurrió un error, vuelva a intentarlo.");
                }
            }

            @Override
            public void onFailure(Call<List<AgentResponse>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void getInstruments(final GetInstrumentsCallback callback) {
        String authorization = "Bearer " + getAccessToken();
        Network.getService().getInstruments(authorization).enqueue(new Callback<List<InstrumentResponse>>() {
            @Override
            public void onResponse(Call<List<InstrumentResponse>> call, Response<List<InstrumentResponse>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Ocurrió un error, vuelva a intentarlo.");
                }
            }

            @Override
            public void onFailure(Call<List<InstrumentResponse>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void postCustodyChain(final PostCustodyChainCallback callback, CustodyChainRequest request) {
        String authorization = "Bearer " + getAccessToken();
        Network.getService().postCustodyChain(authorization, request).enqueue(new Callback<CustodyChainResponse>() {
            @Override
            public void onResponse(Call<CustodyChainResponse> call, Response<CustodyChainResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Ocurrió un error, vuelva a intentarlo.");
                }
            }

            @Override
            public void onFailure(Call<CustodyChainResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public void postMeasurementValue(final PostMeasurementValueCallback callback, MeasurementValueRequest req, String path) {
        File file = new File(path);
        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);



        String authorization = "Bearer " + getAccessToken();
        Network.getService().postMeasurementValue(authorization, create(""+req.custodyChain),
                create(req.max),
                create(req.min),
                create(req.avg),
                create(req.referencePoint),
                create(req.observation),
                create(req.typeLighting),
                //fbody
                prepareFilePart(path)
        ).enqueue(new Callback<MeasurementValueResponse>() {
            @Override
            public void onResponse(Call<MeasurementValueResponse> call, Response<MeasurementValueResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Ocurrió un error, vuelva a intentarlo.");
                }
            }

            @Override
            public void onFailure(Call<MeasurementValueResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }


    private RequestBody create(String value){
        return RequestBody.create(MediaType.parse("text/plain"), value);
    }


    @NonNull
    private MultipartBody.Part prepareFilePart(String path) {
        File file = new File(path);
        RequestBody fbody = RequestBody.create(MediaType.parse("image/*"), file);
        // MultipartBody.Part is used to send also the actual file name
        return MultipartBody.Part.createFormData("images", file.getName(), fbody);
    }



}


