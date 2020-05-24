package dev.forcetower.heroes.core.model

import dev.forcetower.heroes.core.model.dto.MarvelCharacterDTO
import dev.forcetower.heroes.core.model.dto.MarvelComicDTO
import dev.forcetower.heroes.core.model.dto.MarvelThumbnailDTO
import dev.forcetower.heroes.core.model.dto.response.GeneralResponse
import dev.forcetower.heroes.core.model.dto.response.MarvelComicPriceDTO
import dev.forcetower.heroes.core.model.dto.response.PagedResponse
import dev.forcetower.heroes.core.model.persistence.MarvelCharacter
import dev.forcetower.heroes.core.model.ui.MarvelExpensiveComic
import java.util.concurrent.atomic.AtomicInteger
import kotlin.random.Random

object MarvelFakeDataFactory {
    private val counter = AtomicInteger(0)

    fun makeCharactersResponse(): GeneralResponse<MarvelCharacterDTO> {
        val items = (0..19).map { makeCharacter() }
        return GeneralResponse(
            200,
            PagedResponse(
                0,
                20,
                20,
                20,
                items
            )
        )
    }

    fun makeCharacter(): MarvelCharacterDTO {
        val id = counter.addAndGet(1)
        return MarvelCharacterDTO(
            id,
            "Me, myself i'm a hero now $id",
            "This is not a description whatsoever. but it is indeed a really long description, one that you never seen before...\n\nYes... More \\n's moreeeeeeeeee\n\nThat's it",
            makeThumbnail()
        )
    }

    private fun makeThumbnail(): MarvelThumbnailDTO {
        return MarvelThumbnailDTO(
            "not_a_real_picture",
            ".abc"
        )
    }

    fun makeRealCharacter(id: Int): MarvelCharacter {
        return MarvelCharacter(
        id,
        "Wonder Woman",
        "Yep, not a marvel character. Not so real uh, what did you expect? Deadpool? " +
                "Nah, take this DC character. Also, this is a Relampago marquinhos image. " +
                "Bet you didn't expect this one did ya? Has this beat the 3 line marker yet?" +
                "\n\nOk, this is at least a 4 liner by now",
        "https://image.tmdb.org/t/p/w600_and_h900_bestv2/2nM2NRV8wt3n3ffoHQ3KdMkY3vR.jpg"
        )
    }

    fun makeComicsResponse(): GeneralResponse<MarvelComicDTO> {
        val items = (0..9).map {
            makeMarvelComic()
        }.toMutableList().apply {
            add(makeMarvelComic(true))
            shuffle()
        }
        print("items ${items.size} $items")
        return GeneralResponse(
            200,
            PagedResponse(
                0,
                20,
                10,
                10,
                items
            )
        )
    }

    fun makeMarvelExpensiveComic(): MarvelExpensiveComic {
        return MarvelExpensiveComic(
            1,
            "Apple pen",
            "Pineapple pen",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/2nM2NRV8wt3n3ffoHQ3KdMkY3vR.jpg",
            "sonic",
            89.90f
        )
    }

    private fun makeMarvelComic(mostExpensive: Boolean = false): MarvelComicDTO {
        return if (mostExpensive) {
            MarvelComicDTO(
                198,
                198,
                1.1f,
                "Brown eyes green dragon",
                "Funky description",
                listOf(
                    MarvelComicPriceDTO(
                        "nothing",
                        8900.99f
                    ),
                    MarvelComicPriceDTO(
                        "nothing_2",
                        (0..10).random().toFloat()
                    )
                ),
                makeThumbnail()
            )
        } else {
            val id = counter.addAndGet(1)
            MarvelComicDTO(
                id,
                id,
                1f,
                "Not that expensive",
                "meh",
                listOf(
                    MarvelComicPriceDTO(
                        "nothing",
                        (0..10).random().toFloat()
                    ),
                    MarvelComicPriceDTO(
                        "nothing_2",
                        (0..10).random().toFloat()
                    ),
                    MarvelComicPriceDTO(
                        "nothing_3",
                        (0..10).random().toFloat()
                    )
                ),
                makeThumbnail()
            )
        }
    }
}