package com.harium.etyl.ui.selection;

import com.harium.etyl.commons.layer.Layer;

public interface ResizerListener {
	void onResize(ResizerEvent event, int index, Layer layer, Layer old);
}
