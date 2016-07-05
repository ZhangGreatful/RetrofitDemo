package com.example.administrator.retrofitdemo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2016/7/5 0005.
 * //        http://api.github.com/repos/square/retrofit/contributors
 * //        http://api.github.com/repos/square/okhttp/contributors
 * //        构建好请求
 * String owner = "square";//公司
 * String repo = "retrofit";//产品
 * Request request = new Request.Builder()
 * .url("http://api.github.com/repos/" + owner + "/" + repo + "/contributors")
 * .build();
 */
public interface GithubApi {
//    http://api.github.com/

    @GET("repos/square/retrofit/contributors")
    Call<ResponseBody> getRetrofitContributors1();

    @GET("repos/square/okhttp/contributors")
    Call<ResponseBody> getOkHttpContributors1();

    @GET("repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> getContributors(@Path("owner") String owner, @Path("repo") String repo);
//    若没有用转化器的话,这里返回的是返回体ResponseBody
//    @GET("repos/{owner}/{repo}/contributors")
//    Call<ResponseBody> getContributors(@Path("owner") String owner, @Path("repo") String repo);
}
