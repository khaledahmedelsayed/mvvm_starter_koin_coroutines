package com.ibtikar.mvvm_starter_koin_coroutines.screens

import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.ibtikar.mvvm_starter_koin_coroutines.R

class DetailsScreen : Screen<DetailsScreen>() {
    val countryFlag = KImageView { withId(R.id.countryDetailsFlag) }
    val countryName = KTextView { withId(R.id.countryNameTv) }
    val sendButton = KButton { withId(R.id.sendButton) }
    val bottomSheet = KView { withId(R.id.bottomSheetLayout) }
    val sendReportButton = KButton { withId(R.id.sendReportButton) }
    val dialog = KView { withId(R.id.dialogLayout) }
}
