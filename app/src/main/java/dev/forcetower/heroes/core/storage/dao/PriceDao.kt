package dev.forcetower.heroes.core.storage.dao

import androidx.room.Dao
import dev.forcetower.heroes.core.model.persistence.MarvelComicPrice

@Dao
abstract class PriceDao : BaseDao<MarvelComicPrice>()