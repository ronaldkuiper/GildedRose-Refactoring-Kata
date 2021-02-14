package com.gildedrose.quality_updaters

import com.gildedrose.Item

class ConcertQualityUpdater(item: Item): ItemQualityUpdater(item) {

    override fun calculateNewQuality(): Int {

        if (item.sellIn < 0) {
            return min_quality
        }

        return when {

            item.sellIn < 5 -> item.quality + 3
            item.sellIn < 10 -> item.quality + 2
            else -> item.quality + 1
        }
    }

}