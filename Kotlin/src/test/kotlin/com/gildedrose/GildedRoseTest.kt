package com.gildedrose

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class GildedRoseTest {

    @ParameterizedTest
    @CsvSource(
            "10, 6, 5",
            "3, 2, 1"
    )
    fun normal_item_when_ages_quality_drops(sellIn: Int, quality: Int, expectedQuality: Int) = item_test("foo", sellIn, quality, expectedQuality)

    @ParameterizedTest
    @CsvSource(
            "0, 10, 8",
            "-1, 20, 18",
            "-10, 10, 8"
    )
    fun normal_item_when_expired_quality_drops_faster(sellIn: Int, quality: Int, expectedQuality: Int) = item_test("foo", sellIn, quality, expectedQuality)

    @ParameterizedTest
    @CsvSource(
            "Conjured Mana Cake, 1, 2, 0",
            "Conjured Mana Cake, 1, 1, 0",
            "Backstage passes to a TAFKAL80ETC concert, 0, 3, 0",
            "Backstage passes to a TAFKAL80ETC concert, -10, 3, 0",
            "item, 1, 1, 0",
            "sample, 0, 0, 0",
            "foo, -1, 2, 0",
    )
    fun item_quality_never_below_zero(name: String, sellIn: Int, quality: Int, expectedQuality: Int)
        = item_test(name, sellIn, quality, expectedQuality)

    @ParameterizedTest
    @CsvSource(
            "1, 2, 0",
            "1, 1, 0",
            "0, 10, 6",
            "-1, 20, 16",
            "-10, 10, 6",
            "-11, 0, 0",
    )
    fun conjured_item_test(sellIn: Int, quality: Int, expectedQuality: Int)
        = item_test("Conjured Mana Cake", sellIn, quality, expectedQuality)

    @ParameterizedTest
    @CsvSource(
            "10, 6, 4",
            "3, 2, 0"
    )
    fun conjured_item_when_ages_quality_drops_faster(sellIn: Int, quality: Int, expectedQuality: Int) = item_test("Conjured Mana Cake", sellIn, quality, expectedQuality)

    @ParameterizedTest
    @CsvSource(
            "0, 10, 6",
            "-1, 20, 16",
            "-10, 10, 6"
    )
    fun conjured_item_when_expired_quality_drops_even_faster(sellIn: Int, quality: Int, expectedQuality: Int)
        = item_test("Conjured Mana Cake", sellIn, quality, expectedQuality)

    @ParameterizedTest
    @CsvSource(
            "12, 0, 1",
            "2, 1, 2",
            "1, 3, 4"
    )
    fun aged_item_when_ages_quality_improves(sellIn: Int, quality: Int, expectedQuality: Int)
        = item_test("Aged Brie", sellIn, quality, expectedQuality)

    @ParameterizedTest
    @CsvSource(
            "0, 10, 12",
            "-1, 20, 22",
            "-10, 49, 50",
    )
    fun aged_item_when_expired_quality_improves_faster(sellIn: Int, quality: Int, expectedQuality: Int)
        = item_test("Aged Brie", sellIn, quality, expectedQuality)


    @ParameterizedTest
    @CsvSource(
            "3, 49, 50",
            "13, 50, 50",
            "12, 1, 2",
            "11, 2, 3",
    )
    fun concert_item_when_ages_improves_in_quality(sellIn: Int, quality: Int, expectedQuality: Int) = item_test("Backstage passes to a TAFKAL80ETC concert", sellIn, quality, expectedQuality)

    @ParameterizedTest
    @CsvSource(
            "10, 3, 5",
            "7, 4, 6",
            "6, 3, 5",
    )
    fun concert_item_when_ages_improves_in_quality_faster_within_10_days(sellIn: Int, quality: Int, expectedQuality: Int) = item_test("Backstage passes to a TAFKAL80ETC concert", sellIn, quality, expectedQuality)

    @ParameterizedTest
    @CsvSource(
            "5, 3, 6",
            "2, 4, 7",
            "1, 3, 6",
    )
    fun concert_item_when_ages_improves_in_quality_faster_within_5_days(sellIn: Int, quality: Int, expectedQuality: Int) = item_test("Backstage passes to a TAFKAL80ETC concert", sellIn, quality, expectedQuality)

    @ParameterizedTest
    @CsvSource(
            "0, 3, 0",
            "-1, 50, 0",
            "-10, 3, 0",
    )
    fun concert_item_when_expired_loses_quality(sellIn: Int, quality: Int, expectedQuality: Int) = item_test("Backstage passes to a TAFKAL80ETC concert", sellIn, quality, expectedQuality)

    @ParameterizedTest
    @CsvSource(
            "'Sulfuras, Hand of Ragnaros', 1, 1, 1",
            "'Sulfuras, Hand of Ragnaros', 0, 10, 10",
            "'Sulfuras, Hand of Ragnaros', -1, 20, 20",
            "'Sulfuras, Hand of Ragnaros', -10, 50, 50",
    )
    fun legendary_item_never_changes(name: String, sellIn: Int, quality: Int, expectedQuality: Int) = item_test(name, sellIn, quality, expectedQuality)
    { app ->
        assertEquals(sellIn, app.items[0].sellIn)
    }

    private fun item_test(name: String, sellIn: Int, quality: Int, expectedQuality: Int, moreTests: ((GildedRose) -> Unit)? = null) {
        val items = arrayOf(Item(name, sellIn, quality))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals(name, app.items[0].name)
        assertEquals(expectedQuality, app.items[0].quality)

        moreTests?.invoke(app)
    }
}


