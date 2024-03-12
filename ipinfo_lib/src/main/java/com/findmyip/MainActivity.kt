package com.findmyip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.findmyip.presentation.viewmodel.IpViewModel
import com.findmyip.ui.component.IpInfoCard
import com.findmyip.ui.theme.FindmyipTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: IpViewModel by viewModels()
        setContent {
            FindmyipTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val ipInfo by viewModel.ipInfo.observeAsState()
                    val isLoading by viewModel.loading.observeAsState()
                    val error by viewModel.error.observeAsState()

                    isLoading?.let {
                        if (it) {
                            Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                                CircularProgressIndicator()
                            }
                        } else {
                            if (error != null) {
                                Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                                    Column {
                                        Text(text = "Error: $error", modifier = Modifier.padding(16.dp))
                                        Button(onClick = { viewModel.fetchIpInfo() },modifier = Modifier.padding(16.dp).size(100.dp, 50.dp)
                                        ) {
                                            Text(text = "Retry")
                                        }
                                    }
                                }
                            } else {
                                ipInfo?.let { data ->
                                    IpInfoCard(data)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FindmyipTheme {
        Greeting("Android")
    }
}