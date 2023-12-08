package com.example.constraintcomposelesson

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.constraintcomposelesson.ui.theme.ConstraintComposeLessonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConstraintComposeLessonTheme {
                GreetingPreview()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (text, button, image) = createRefs()
        val bottomGuideLine = createGuidelineFromBottom(0.2f)

        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button) {
                bottom.linkTo(bottomGuideLine)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        ) {
            Text(text = "Click me")
        }
        Text(
            text = "Hello world",
            modifier = Modifier.constrainAs(text) {
                bottom.linkTo(button.top, 24.dp)
                start.linkTo(button.start)
                end.linkTo(button.end)
            })
        Image(
            painter = painterResource(
                id = R.drawable.ic_launcher_background
            ),
            contentDescription = "",
            modifier = Modifier.constrainAs(image) {
                bottom.linkTo(text.top, 4.dp)
            }
        )
    }
}