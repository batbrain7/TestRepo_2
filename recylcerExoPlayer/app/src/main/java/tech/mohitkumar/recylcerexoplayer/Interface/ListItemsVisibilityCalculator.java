package tech.mohitkumar.recylcerexoplayer.Interface;

/**
 * Created by mohitkumar on 11/06/17.
 */

public interface ListItemsVisibilityCalculator {

    void onScrollStateIdle(ItemPositionGetter itemsPositionGetter, int firstVisiblePosition, int lastVisiblePosition);
    void onScroll(ItemPositionGetter itemsPositionGetter, int firstVisibleItem, int visibleItemCount, int scrollState);
}
