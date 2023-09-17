package com.xxmrk888ytxx.contesttraining.presentation.Screens.CameraScreen

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.video.FallbackStrategy
import androidx.camera.video.FileOutputOptions
import androidx.camera.video.Quality
import androidx.camera.video.QualitySelector
import androidx.camera.video.Recorder
import androidx.camera.video.VideoCapture
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
) : ViewModel() {


    val textFromPicture = MutableStateFlow("")

    val isScannerActive = MutableStateFlow(false)







    private val cameraScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    fun switchScan(context: Context, lifecycleOwner: LifecycleOwner) {
        if (isScannerActive.value) {
            stopScan()
        } else startScan(context, lifecycleOwner)
    }


    private fun stopScan() {
        isScannerActive.update { false }
        cameraScope.coroutineContext.cancelChildren()

    }



    private fun startScan(context: Context, lifecycleOwner: LifecycleOwner) {
        cameraScope.launch(Dispatchers.IO) {
            if (context.checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Permission not granted", Toast.LENGTH_LONG).show()
                }

                return@launch
            }

            isScannerActive.update { true }


            val imageCapture = ImageCapture.Builder().build()

            val file = File(context.cacheDir, "scanner-temp")

            val outputParams = ImageCapture.OutputFileOptions.Builder(file).build()

            val processCameraProvider = ProcessCameraProvider.getInstance(context).get()

            withContext(Dispatchers.Main) {
                processCameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    CameraSelector.DEFAULT_BACK_CAMERA,
                    imageCapture
                )
            }

            val recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)


            CoroutineScope(Dispatchers.Default).launch {
                isScannerActive.collect() {
                    if(!it) {

                        withContext(Dispatchers.Main) {
                            processCameraProvider.unbind(imageCapture)

                        }

                        this.cancel()
                    }
                }
            }


            fun nextImage() {
                imageCapture.takePicture(
                    outputParams,
                    Dispatchers.IO.asExecutor(),
                    object :  ImageCapture.OnImageSavedCallback {
                        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                            outputFileResults.savedUri?.let { url ->
                                recognizer.process(InputImage.fromFilePath(context,url))
                                    .addOnSuccessListener { text ->
                                        if(isScannerActive.value) {
                                            textFromPicture.update { text.text }
                                        }
                                    }
                                    .addOnCompleteListener {
                                        if(isScannerActive.value) {
                                            nextImage()
                                        }
                                    }
                            }



                        }

                        override fun onError(exception: ImageCaptureException) {

                        }

                    }
                )
            }

            nextImage()

        }
    }

    override fun onCleared() {
        cameraScope.cancel()
        super.onCleared()
    }


}