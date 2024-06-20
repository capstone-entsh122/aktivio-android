package com.bangkit.aktivio.modules.dietary

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LifecycleOwner
import com.bangkit.aktivio.R
import com.bangkit.aktivio.core.data.Resource
import com.bangkit.aktivio.core.domain.model.FoodNutritionModel
import com.bangkit.aktivio.core.domain.model.Nutrition
import com.bangkit.aktivio.core.utils.Extensions.toast
import com.bangkit.aktivio.core.utils.mapTo
import com.bangkit.aktivio.databinding.ActivityCameraBinding
import com.google.common.util.concurrent.ListenableFuture
import dagger.hilt.android.AndroidEntryPoint
import www.sanju.motiontoast.MotionToastStyle
import java.io.File

@AndroidEntryPoint
class CameraActivity : AppCompatActivity() {

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>
    private lateinit var imageCapture: ImageCapture
    private lateinit var camera: Camera
    private lateinit var cameraProvider: ProcessCameraProvider
    private lateinit var binding: ActivityCameraBinding
    private lateinit var file: File
    private val viewModel: CameraViewModel by viewModels()

    private val backCameraSelector by lazy {
        getCameraSelector(CameraSelector.LENS_FACING_BACK)
    }

    private val cameraPermissionResult =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                startCamera()
            } else {
                toast("Error", "Camera permission is required to use this feature", MotionToastStyle.ERROR)
                finish()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            cameraPermissionResult.launch(Manifest.permission.CAMERA)
        } else {
            startCamera()
        }
        showLoading(false)
    }

    private fun startCamera() {
        cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener({
            cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider, backCameraSelector)
        }, ContextCompat.getMainExecutor(this))

        binding.ivBtnCapture.setOnClickListener {
            takePicture()
        }
    }

    private fun takePicture() {
        showLoading(true)
        if (this::imageCapture.isInitialized.not()) return
        file = File(getExternalFilesDir(null), "IMG_${System.currentTimeMillis()}.jpg")
        val outputOptions = ImageCapture.OutputFileOptions.Builder(file).build()

        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    viewModel.predictFood(file).observe(this@CameraActivity) {
                        when(it) {
                            is Resource.Error -> {
                                showLoading(false)
                                toast("Error", it.message.toString(), MotionToastStyle.ERROR)
                            }
                            is Resource.Loading -> {
                                showLoading(true)
                            }
                            is Resource.Success -> {
                                showLoading(false)
                                toast("Success", "Food successfully predicted", MotionToastStyle.SUCCESS)
                                val nutritionModel: Nutrition = it.data?.nutrition!!.mapTo()
                                val foodNutritionModel = FoodNutritionModel(
                                    label = it.data.label,
                                    nutrition = nutritionModel
                                )
                                val intent = Intent(this@CameraActivity, NutritionActivity::class.java)
                                intent.putExtras(Bundle().apply {
                                    putParcelable("foodNutrition", foodNutritionModel)
                                    putString("file", file.absolutePath)
                                })
                                Log.d("CameraActivity", "file: ${file.absolutePath}")

                                startActivity(intent)
                            }
                        }
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    toast("Error", "Failed to capture image", MotionToastStyle.ERROR)
                }
            }
        )
    }

    private fun showLoading(state: Boolean) {
        with(binding) {
            if (state) {
                progressContainer.visibility = View.VISIBLE
                circularProgressBar.setProgressWithAnimation(100f, 1000)
            } else {
                progressContainer.visibility = View.GONE
                circularProgressBar.progress = 0f
            }
        }
    }

    private fun getCameraSelector(lensFacing: Int) =
        CameraSelector.Builder().requireLensFacing(lensFacing).build()

    private fun bindPreview(cameraProvider: ProcessCameraProvider, cameraSelector: CameraSelector) {
        val preview = Preview.Builder().build()
        preview.setSurfaceProvider(binding.previewView.surfaceProvider)
        imageCapture =
            ImageCapture.Builder().setTargetRotation(binding.previewView.display.rotation).build()
        camera = cameraProvider.bindToLifecycle(
            this as LifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
    }
}
