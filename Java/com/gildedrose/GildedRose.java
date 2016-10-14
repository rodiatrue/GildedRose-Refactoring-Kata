package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            initFilters("Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros", 0, 11, 6, 50, i);
        }
    }

    private void incrementQuality(int i)
    {
        items[i].quality = items[i].quality + 1;
    }

    private void decrementQuality(int i)
    {
        items[i].quality = items[i].quality - 1;
    }

    private void decrementSellIn(int i)
    {
        items[i].sellIn = items[i].sellIn - 1;
    }

    private void resetQuality(int i)    {
        items[i].quality = 0;
    }

    private void applyDecrementQuality(String name, int i)
    {
        if (!items[i].name.equals(name)) {
            decrementQuality(i);
        }
    }

    private boolean filterQuality(int i, int filter)
    {
        if (items[i].quality < filter) {
            incrementQuality(i);

            return true;
        }

        return false;
    }

    private void filterSellIn(int i, int filter, int filterForQuality)
    {
        if (items[i].sellIn < filter) {
            filterQuality(i, filterForQuality);
        }
    }

    private void applyNegativeQuality(int filter, String name, int i)
    {
        if (items[i].quality > filter) {
            applyDecrementQuality(name, i);
        }
    }

    private void filterDecrementSellIn(String name, int i)
    {
        if (!items[i].name.equals(name)) {
            decrementSellIn(i);
        }
    }

    private void applyPositiveQualty(String name, int i, int sellInFist, int sellInSecond, int filter)
    {
        if (items[i].name.equals(name)) {
            filterSellIn(i, sellInFist, filter);
            filterSellIn(i, sellInSecond, filter);
        }
    }

    private void buildFilter(String name, int i, int sellInFist, int sellInSecond, int filter)
    {
        if (filterQuality(i, filter)) {
            applyPositiveQualty(name, i, sellInFist, sellInSecond, filter);
        }
    }

    private boolean filterTwoCases(String name, String nameFiltered, String nameParameter, int filter, int i)
    {
        boolean value = false;

        if (!items[i].name.equals(name) && !items[i].name.equals(nameFiltered)) {
            applyNegativeQuality(filter, nameParameter, i);
            value = true;
        }

        return value;
    }

    private void decideByFilter(String name, String nameFiltered, int filter, int i)
    {
        if (!items[i].name.equals(name) && items[i].name.equals(nameFiltered)) {
            resetQuality(i);
        } else {
            filterQuality(i, filter);
        }
    }

    private void buildFilters(String name, String nameFiltered, String nameSecondFiltered, int filter, int secondFilter, int i)
    {
        if (!filterTwoCases(name, nameFiltered, nameSecondFiltered, filter, i)) {
            decideByFilter(name, nameFiltered, secondFilter, i);
        }
    }

    private void createFilters(String name, String nameFiltered, String nameSecondFiltered, int filter, int secondFilter, int thirdFilter, int fourthFilter, int i)
    {
        if (!filterTwoCases(name, nameFiltered, nameSecondFiltered, filter, i)) {
            buildFilter(nameFiltered, i, secondFilter, thirdFilter, fourthFilter);
        }
    }

    private void initFilters(String name, String nameFiltered, String nameSecondFiltered, int filter, int secondFilter, int thirdFilter, int fourthFilter, int i)
    {
        createFilters(name, nameFiltered, nameSecondFiltered, filter, secondFilter, thirdFilter, fourthFilter, i);

        filterDecrementSellIn(nameSecondFiltered, i);

        if (items[i].sellIn < 0) {
            buildFilters(name, nameFiltered, nameSecondFiltered, filter, fourthFilter, i);
        }
    }
}
