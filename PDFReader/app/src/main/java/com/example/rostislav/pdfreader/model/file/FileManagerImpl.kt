package com.example.rostislav.pdfreader.model.file

import android.content.Context
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import com.example.rostislav.pdfreader.utils.changeName
import java.io.File
import java.io.FileOutputStream

class FileManagerImpl(private val context: Context) : FileManager {

    override fun writeFile(byteArray: ByteArray, filename: String): File {
        context.openFileOutput(filename, Context.MODE_PRIVATE)
            .use { it.write(byteArray) }
        return File(context.filesDir, filename)
    }

    override fun readFile(localPath: String) = File(localPath)

    override fun isFileExist(localPath: String) = File(localPath).exists()

    override fun generateImageFromPdf(file: File): File {
        val fileThumbnail = File(context.filesDir, changeName(file.name))
        val pdfRenderer = PdfRenderer(ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY))
        val pdfPage = pdfRenderer.openPage(DEFAULT_PAGE)
        val bitmap = Bitmap.createBitmap(pdfPage.width, pdfPage.height, Bitmap.Config.ARGB_8888)
        pdfPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_PRINT)
        bitmap.compress(Bitmap.CompressFormat.PNG, QUALITY_IN_PERCENTS, FileOutputStream(fileThumbnail))
        return fileThumbnail
    }

    companion object {
        const val DEFAULT_PAGE = 0
        const val QUALITY_IN_PERCENTS = 100
    }
}