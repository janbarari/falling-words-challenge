package io.github.janbarari.fallingwords.challenge.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.janbarari.fallingwords.challenge.data.AssetDataSourceImpl
import io.github.janbarari.fallingwords.challenge.data.WordsRepositoryImpl
import io.github.janbarari.fallingwords.challenge.data.source.AssetDataSource
import io.github.janbarari.fallingwords.challenge.domain.repository.WordsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class ChallengeBinds {

    @Binds
    abstract fun bindWordsRepository(impl: WordsRepositoryImpl): WordsRepository

    @Binds
    abstract fun bindAssetDataSource(impl: AssetDataSourceImpl): AssetDataSource
}