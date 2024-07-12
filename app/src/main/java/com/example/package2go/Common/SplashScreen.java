package com.example.package2go.Common;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.package2go.Common.LoginSignup.BusinessStartUpScreen;
import com.example.package2go.Common.LoginSignup.Login;
import com.example.package2go.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;
    // variables
    ImageView backgroundImage;
    TextView slogan;

    // Animations
    Animation sideAnim, bottomAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN );
//        EdgeToEdge.enable(this);
        setContentView(R.layout.splash_screen);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        // hooks
        backgroundImage = findViewById(R.id.background_image);
        slogan = findViewById(R.id.slogan);

        // Animations
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        // set Animations on elements
        backgroundImage.setAnimation(sideAnim);
        slogan.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(getApplicationContext(), BusinessStartUpScreen.class);
                startActivity(intent);
                finish();


            }
        },SPLASH_TIMER);




    }
}