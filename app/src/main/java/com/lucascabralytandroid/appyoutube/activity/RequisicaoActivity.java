package com.lucascabralytandroid.appyoutube.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.lucascabralytandroid.appyoutube.R;
import com.lucascabralytandroid.appyoutube.adapter.AdapterVideo;
import com.lucascabralytandroid.appyoutube.api.YoutubeService;
import com.lucascabralytandroid.appyoutube.helper.RecyclerItemClickListener;
import com.lucascabralytandroid.appyoutube.helper.RetrofitConfig;
import com.lucascabralytandroid.appyoutube.helper.YoutubeConfig;
import com.lucascabralytandroid.appyoutube.model.Video;
import com.lucascabralytandroid.appyoutube.model.Resultado;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RequisicaoActivity extends AppCompatActivity {

    //Widgets
    private RecyclerView recyclerVideos;
    private MaterialSearchView searchView;

    private List<Video> videos = new ArrayList<>();
    private Resultado resultado;
    private AdapterVideo adapterVideo;

    // Retrofit
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requisicao);

        inicializandoComponentes();

        recuperarVideos();

        // Configura métodos para searchView
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });
    }

    private void inicializandoComponentes() {

        //Iniciar componentes
        recyclerVideos = findViewById(R.id.recyclerVideos);
        searchView = findViewById(R.id.searchView);

        // Configurações iniciais
        retrofit = RetrofitConfig.getRetrofit();

        // Configurando Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Youtube");
        setSupportActionBar(toolbar);
    }

    private void recuperarVideos() {

        YoutubeService youtubeService = retrofit.create(YoutubeService.class);

        youtubeService.recuperarVideos(
                "snippet", "date", "20",
                YoutubeConfig.YOUTUBE_API_KEY, YoutubeConfig.CANAL_ID
        ).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                Log.d("Resultado", "resultado: " + response.toString());

                if (response.isSuccessful()) {

                    resultado = response.body();
                    videos = resultado.videos;
                    configurarRecyclerView();

                    Log.d("ItemTitle", "resultado: " + resultado.videos.get(0).snippet.title);
                }
            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {

            }
        });
    }

    public void configurarRecyclerView() {

        adapterVideo = new AdapterVideo(videos, this);
        recyclerVideos.setHasFixedSize(true);
        recyclerVideos.setLayoutManager(new LinearLayoutManager(this));
        recyclerVideos.setAdapter(adapterVideo);

        // Configurando evento de click
        recyclerVideos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        this,
                        recyclerVideos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Video video = videos.get(position);
                                String idVideo = video.id.videoId;

                                Intent intent = new Intent(RequisicaoActivity.this, PlayerActivity.class);
                                intent.putExtra("idVideo", idVideo);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        MenuItem item = menu.findItem(R.id.menu_search);
        searchView.setMenuItem(item);
        return true;
    }
}