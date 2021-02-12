package com.khaledahmedelsayed.mvvmwithstatestarter.ui.platform.bottomsheet

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.andrefrsousa.superbottomsheet.SuperBottomSheetFragment

class SuperBottomSheetFactory : SuperBottomSheetFragment() {

    private var sheetView: View? = null

    private var onDismissListenerLambda: () -> Unit = {}

    override fun getCornerRadius() = requireArguments().getFloat(CORNER_RADIUS)

    override fun getPeekHeight() = requireArguments().getInt(PEEK_HEIGHT)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return sheetView!!
    }

    override fun onDismiss(dialog: DialogInterface) {
        onDismissListenerLambda()
        sheetView = null
        super.dismiss()
    }

    companion object {
        private const val CORNER_RADIUS = "cornerRadius"
        private const val PEEK_HEIGHT = "peekHeight"

        fun newInstance(
            sheetView: View,
            sheetCornerRadius: Float,
            sheetPeekHeight: Int,
            onDismissListener: () -> Unit
        ) = SuperBottomSheetFactory().apply {
            this.sheetView = sheetView
            arguments = bundleOf(
                Pair(CORNER_RADIUS, sheetCornerRadius),
                Pair(PEEK_HEIGHT, sheetPeekHeight),
            )
            onDismissListenerLambda = onDismissListener
        }
    }
}
