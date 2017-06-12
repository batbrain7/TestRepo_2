package tech.mohitkumar.recylcerexoplayer.Interface;

import android.view.View;

/**
 * Created by mohitkumar on 11/06/17.
 */

public interface ItemPositionGetter {
    View getChildAt(int position);

    int indexOfChild(View view);

    int getChildCount();

    int getLastVisiblePosition();

    int getFirstVisiblePosition();
}
