package com.example.lesson1_mvp

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class ConverJpgToPng {

    var context: Context? = null

    fun initConvert(pathJpg: String, _context: Context): String {

        var pngPath =
            pathJpg + ".png"                                                              // делаем новое имя файла

        context = _context

        val bitmap =
            assetsToBitmap(pathJpg)                                                        // считали с asset'ов

        bitmapToFile(
            pngPath,
            bitmap
        )                                                               // записали в галерею

        Thread.sleep(10000)
        return pngPath
    }

    // Считываем с ассетов
    private fun assetsToBitmap(fileName: String): Bitmap? {
        return try {
            val stream = context?.assets?.open(fileName)
            BitmapFactory.decodeStream(stream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    // записываем в галерею
    private fun bitmapToFile(fileName: String, bitMap: Bitmap?) {
        try {
            var fos: OutputStream? = null

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                context?.contentResolver?.also { resolver ->

                    val contentValues = ContentValues().apply {
                        put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                        put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                        put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                    }

                    val imageUri: Uri? =
                        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                    fos = imageUri?.let { resolver.openOutputStream(it) }

                }
            } else {

                val imagesDir =
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

                val image = File(imagesDir, fileName)

                fos = FileOutputStream(image)
            }

            fos?.use {
                bitMap?.compress(Bitmap.CompressFormat.PNG, 100, it)
            }

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}