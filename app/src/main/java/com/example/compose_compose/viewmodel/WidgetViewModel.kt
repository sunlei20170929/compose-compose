package com.example.compose_compose.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WidgetViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
}