package com.gildedrose.quality_updaters

import com.gildedrose.Item
 class NormalProductQualityUpdater(item: Item, private val ageFactor: Int = 1): ItemQualityUpdater(item) {

    override fun calculateNewQuality(): Int =
            item.quality - (if (item.sellIn >= min_quality) 1 else 2) * ageFactor
}