package dev.forcetower.heroes.core.source.local.dao

import androidx.room.Dao
import dev.forcetower.heroes.core.model.persistence.MarvelComicPrice

@Dao
abstract class PriceDao : BaseDao<MarvelComicPrice>()