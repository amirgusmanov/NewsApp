package kz.amir.newsapp.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kz.amir.newsapp.R
import kz.amir.newsapp.ui.theme.BackgroundWhite
import kz.amir.newsapp.ui.theme.NewsAppTheme
import kz.amir.newsapp.ui.theme.Typography

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                SplashScreen()
            }
        }
    }

    @Composable
    @Preview(showSystemUi = true)
    private fun SplashScreen() {
        val alpha = remember { Animatable(0f) }
        LaunchedEffect(key1 = true) {
            alpha.animateTo(targetValue = 1f, animationSpec = tween(1500))
            delay(2000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundWhite),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.alpha(alpha.value),
                painter = painterResource(id = R.drawable.ic_logo),
                contentDescription = "logo"
            )
            Text(
                text = "Check the latest news",
                fontStyle = Typography.bodySmall.fontStyle,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(50.dp)
                    .alpha(alpha.value)
            )
        }
    }
}
