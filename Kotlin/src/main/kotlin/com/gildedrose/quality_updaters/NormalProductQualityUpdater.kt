package com.gildedrose.quality_updaters

import com.gildedrose.Item

class NormalProductQualityUpdater(item: Item): ItemQualityUpdater(item) {

    override fun calculateNewQuality(): Int =
            item.quality - if (item.sellIn >= min_quality) 1 else 2
}