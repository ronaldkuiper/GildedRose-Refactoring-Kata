package com.gildedrose.quality_updaters

import com.gildedrose.Item

class AgedQualityUpdater(item: Item): ItemQualityUpdater(item) {
    override fun calculateNewQuality() =
            item.quality + if (item.sellIn >= min_quality) 1 else 2

}