package com.example.administrator.retrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//      1.我们要使用Retrofit
//      依赖
//      2.我们想访问一个Web接口(RESTE)
//      根据文档,我们要创建一个接口
//      3.创建出Retrofit
//      4.create我们的Interface

public class MainActivity extends AppCompatActivity {

    private Retrofit                  retrofit;
    private GithubApi                 githubApi;
    private Call<List<Contributor>>   call;
    private ListView                  listView;
    private ArrayAdapter<Contributor> adapter;
    private Gson                      gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        视图
        listView = (ListView) findViewById(R.id.listview);
        adapter = new ArrayAdapter<Contributor>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);
//          初始化Retrofit
        initRetrofit();
//        创建出接口
        githubApi = retrofit.create(GithubApi.class);
        call = githubApi.getContributors("square", "retrofit");
        call.enqueue(callBack);//异步
    }

    private Callback<List<Contributor>> callBack = new Callback<List<Contributor>>() {
        @Override
        public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
            List<Contributor> contributorList = response.body();
            adapter.addAll(contributorList);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onFailure(Call<List<Contributor>> call, Throwable t) {
            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
//    private Callback<ResponseBody> callBack = new Callback<ResponseBody>() {
//        @Override
//        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//            gson = new Gson();
//            try {
//                String body = response.body().string();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        @Override
//        public void onFailure(Call<ResponseBody> call, Throwable t) {
//            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    };

    /**
     * 在这里添加转化器直接将ResponseBody转化为Contributors,不需要再进行转化
     */
    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.github.com/")
                //转换器Gson
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
