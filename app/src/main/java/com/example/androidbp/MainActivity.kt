package com.example.androidbp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.androidbp.databinding.ContentMainBinding
import com.example.androidbp.theme.getDarkAppColors
import com.example.androidbp.theme.getLightAppColors
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ComposeView(this).apply {
            setContent { MainScreenUis() }
        })
    }

    @Composable
    private fun MainScreenUis() {

        MaterialTheme(
            colors = if (isSystemInDarkTheme()) {
                getDarkAppColors()
            } else {
                getLightAppColors()
            }
        ) {

            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                Column {
                    TopAppBar(
                        backgroundColor = MaterialTheme.colors.primary
                    ) {
                        Text(
                            text = getString(R.string.app_name),
                            fontSize = 24.sp,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    AndroidView(
                        factory = {
                            ContentMainBinding.inflate(layoutInflater).root
                        },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
