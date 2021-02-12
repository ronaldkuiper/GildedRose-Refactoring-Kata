package com.gildedrose

class GildedRose(var items: Array<Item>) {

    companion object {
        private const val item_concert = "Backstage passes to a TAFKAL80ETC concert"
        private const val item_legendary = "Sulfuras, Hand of Ragnaros"
        private const val item_brie = "Aged Brie"
        private const val max_quality = 50
        private const val min_quality = 0
    }
    
    fun updateQuality() {
        for (item in items) {
            if (item.name == item_legendary) {
                continue
            }

            val quality = when {

                item.name == item_concert && item.sellIn < 6 -> item.quality + 3
                item.name == item_concert && item.sellIn < 11 -> item.quality + 2
                item.name == item_brie || item.name == item_concert -> item.quality + 1
                else -> maxOf(min_quality, item.quality - 1)
            }

            item.quality = minOf(max_quality, quality)

            item.sellIn = item.sellIn - 1

            if (item.sellIn < 0) {
                when {
                    item.name == item_brie -> item.quality = minOf(max_quality, item.quality + 1)
                    item.name == item_concert -> item.quality = min_quality
                    item.quality > min_quality -> item.quality--
                }
            }
        }
    }

}

