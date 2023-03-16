package io.github.janbarari.fallingwords.challenge.data.source

import java.io.File

interface AssetDataSource {
    fun getFile(fileName: String): File
}
