package itson.mx.deliveryapp.overrides;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by chapa on 03/10/2015.
 */
public class GridLayoutManager extends android.support.v7.widget.GridLayoutManager {
    private boolean canScroll = false;
    public GridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    public GridLayoutManager(Context context, int spanCount){
        super(context, spanCount);
    }
    public GridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout){
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollHorizontally() {
        return canScroll;
    }

    @Override
    public boolean canScrollVertically() {
        return canScroll;
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }
}
