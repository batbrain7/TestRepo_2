package tech.mohitkumar.recylcerexoplayer;

import tech.mohitkumar.recylcerexoplayer.Interface.ItemPositionGetter;
import tech.mohitkumar.recylcerexoplayer.Interface.ListItemsVisibilityCalculator;

/**
 * Created by mohitkumar on 11/06/17.
 */

public class BaseItemsVisibilityCalculator implements ListItemsVisibilityCalculator, ScrollDetectionDetector.OnDetectScrollListener {
    @Override
    public void onScrollStateIdle(ItemPositionGetter itemsPositionGetter, int firstVisiblePosition, int lastVisiblePosition) {

    }

    @Override
    public void onScroll(ItemPositionGetter itemsPositionGetter, int firstVisibleItem, int visibleItemCount, int scrollState) {

    }

    @Override
    public void onScrollDirectionChanged(ScrollDetectionDetector.ScrollDirection scrollDirection) {

    }
}
