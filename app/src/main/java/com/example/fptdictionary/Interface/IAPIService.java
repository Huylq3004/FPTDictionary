package com.example.fptdictionary.Interface;

import java.util.ArrayList;

import com.example.fptdictionary.model.API.TranslateTuVung;
import com.example.fptdictionary.model.API.TuVung;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IAPIService {
    String Trans_Service = "https://api.tracau.vn/WBBcwnwQpV89/s/";
    String BASE_Service = "https://api.dictionaryapi.dev/api/v2/entries/en/";

    @GET("{id}/en")
    Observable<TranslateTuVung> GetTrans(@Path("id") String id);

    @GET("{id}")
    Observable<ArrayList<TuVung>> GetTuDien(@Path("id") String id);

}
