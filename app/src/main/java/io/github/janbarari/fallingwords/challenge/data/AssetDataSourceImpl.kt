package io.github.janbarari.fallingwords.challenge.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.janbarari.fallingwords.challenge.data.source.AssetDataSource
import java.io.File
import javax.inject.Inject

class AssetDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AssetDataSource {
    override fun getFile(fileName: String): File {
        val assetManager = context.assets
        val file = File(context.cacheDir, fileName)
        val inputStream = assetManager.open(fileName)
        file.outputStream().use { inputStream.copyTo(it) }
        return file
    }
}
