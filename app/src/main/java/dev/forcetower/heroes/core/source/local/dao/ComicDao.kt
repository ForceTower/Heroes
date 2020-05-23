package dev.forcetower.heroes.core.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import dev.forcetower.heroes.core.model.persistence.MarvelComic
import dev.forcetower.heroes.core.model.ui.MarvelExpensiveComic
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ComicDao : BaseDao<MarvelComic>() {
    @Transaction
    @Query("SELECT comic.issueNumber, comic.description, comic.title, comic.thumbnail, price.price, price.type FROM MarvelComic AS comic INNER JOIN MarvelComicPrice AS price ON comic.id = price.comicId WHERE comic.characterId = :characterId AND comic.id = (SELECT MCP.comicId FROM MarvelComicPrice MCP INNER JOIN MarvelComic MC on MCP.comicId = MC.id WHERE MC.characterId = :characterId ORDER BY MCP.price DESC LIMIT 1)")
    abstract fun mostExpensive(characterId: Int): Flow<MarvelExpensiveComic?>
}