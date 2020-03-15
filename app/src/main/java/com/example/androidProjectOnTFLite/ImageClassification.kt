package com.example.androidProjectOnTFLite


import android.content.Context
import android.graphics.Bitmap
import com.example.androidProjectOnTFLite.Utils.AssetFilePath
import org.pytorch.IValue
import org.pytorch.Module
import org.pytorch.torchvision.TensorImageUtils


class ImageClassification(bitmap: Bitmap, context: Context) {
    private val module = loadModule(context);
    private val inputTensor = TensorImageUtils.bitmapToFloat32Tensor(
        bitmap,
        TensorImageUtils.TORCHVISION_NORM_MEAN_RGB,
        TensorImageUtils.TORCHVISION_NORM_STD_RGB
    )

    public fun objectDetection(): String {
        val outputTensor = module.forward(IValue.from(inputTensor)).toTensor()
        val scores = outputTensor.dataAsFloatArray
        val maxScoreIdx = scores.indices.maxBy { scores[it] } ?: -1
        val classNames = ImageNetClasses.IMAGENET_CLASSES[maxScoreIdx]
        return classNames
    }

    private fun loadModule(context: Context) = Module.load(AssetFilePath.getPath(context, "resnet18.pt"));

}