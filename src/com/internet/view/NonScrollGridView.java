
package com.internet.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;
/**不可以滚动的GridView可以套在其他列表控件中解决滚动冲突*/
public class NonScrollGridView extends GridView {

    public NonScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public NonScrollGridView(Context context) {
        super(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }

}
