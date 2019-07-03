package com.example.rostislav.pdfreader.feature.main

import com.example.rostislav.pdfreader.feature.MVP.MVPPresenter
import com.example.rostislav.pdfreader.feature.MVP.MVPView

interface View : MVPView {

    fun showView(){

    }
}

interface Presenter : MVPPresenter<View> {

    fun downloadView(){

    }
}