package com.joelkanyi.mynote.screen

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.joelkanyi.mynote.navigation.Screen
import kotlinx.coroutines.delay


@SuppressLint("SuspiciousIndentation")
@Composable
fun AnimatedLaunchScreen(navController:NavHostController){
    var startAnimation by remember{
        mutableStateOf(false)}
    val alphaAnim= animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 3000
        )
    )
    LaunchedEffect(key1 = true){
        startAnimation=true
        delay(3000)
        navController.popBackStack()
        navController.navigate(Screen.AddNote.route)
    }
    LaunchSplash(alpha = alphaAnim.value)

}

@Composable
fun LaunchSplash(alpha:Float){
    Box(

        modifier = Modifier
            .background(if (isSystemInDarkTheme()) Color.Black else Color.Black)
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ){
        Text(
            modifier = Modifier
                .alpha(alpha=alpha),
            text = "MyNotes",
            fontWeight = FontWeight.ExtraBold,
            color = Color.White,
            style = TextStyle(fontSize = 40.sp)


        )
    }
}

@Composable
@Preview
fun LaunchSplashPreview(){
    LaunchSplash(alpha = 1f)
}





