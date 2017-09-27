package com.xiongxh.bakingapp.data.remote;


import java.util.ArrayList;

import retrofit2.Call;

public interface RemoteRecipeResponse<T> {
    void onRequestStart(Call<T> call);

    void onSuccuss(ArrayList<T> result);

    void onFailure(Throwable throwable);
}
