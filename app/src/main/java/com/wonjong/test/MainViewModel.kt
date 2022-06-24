package com.wonjong.test

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Created by leewonjong@29cm.co.kr on 2022-06-23
 */
class MainViewModel : ViewModel() {
    private val _url = MutableStateFlow("vYfeuIYQ8Xs") // https://www.youtube.com/watch?v=vYfeuIYQ8Xs
    val url = _url.asStateFlow()
}