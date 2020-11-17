package com.lucascabralytandroid.appyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity
                implements YouTubePlayer.OnInitializedListener {

    private static final String GOOGLE_API_KEY = "AIzaSyB69fW6BK83Wkak-_ndgtmJttJyWgs9WJU";
    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView = findViewById(R.id.viewYoutubePlayer);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this);

    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        Toast.makeText(this,
                "Sucesso ao iniciar o Player",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this,
                "Erro ao iniciar o Player",
                Toast.LENGTH_SHORT).show();
    }
}