package com.xxmrk888ytxx.contesttraining.presentation.Screens.CameraScreen

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun CameraScreen(
    cameraViewModel: CameraViewModel = hiltViewModel(),
) {
    val text by cameraViewModel.textFromPicture.collectAsState()

    val isScanning by cameraViewModel.isScannerActive.collectAsState()

    val context = LocalContext.current

    val lifecycleOwner = LocalLifecycleOwner.current

    val requestPermissionContract = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {

        }
    )

    DisposableEffect(key1 = Unit, effect = {
        onDispose {
            ProcessCameraProvider.getInstance(context).get().unbindAll()
        }
    })

    LaunchedEffect(key1 = Unit, block = {
        if(context.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionContract.launch(Manifest.permission.CAMERA)
        } else {
            cameraViewModel.switchScan(context.applicationContext, lifecycleOwner)
        }
    })


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        Preview()

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "Result:$text")

        Button(onClick = { cameraViewModel.switchScan(context.applicationContext, lifecycleOwner) }) {
            Text(text = if(isScanning) "Disable scan" else "Enable scan")
        }


    }



}

@Composable
fun Preview() {
    val localLifecycleOwner = LocalLifecycleOwner.current

    val context = LocalContext.current

    val cameraProvider = remember {
        ProcessCameraProvider.getInstance(context).get()
    }

    val preview = remember {
        Preview.Builder().build()
    }

    LaunchedEffect(key1 = Unit, block = {
        if(!cameraProvider.isBound(preview)) {
            cameraProvider.bindToLifecycle(localLifecycleOwner, CameraSelector.DEFAULT_BACK_CAMERA,preview)
        }
    })

    DisposableEffect(key1 = Unit, effect = {
        onDispose { cameraProvider.unbind(preview) }
    })


    Box(modifier = Modifier.padding(16.dp)) {
        AndroidView(
            factory = {
                PreviewView(it).apply {
                    preview.setSurfaceProvider(this.surfaceProvider)
                }
            },
        )
    }
}
