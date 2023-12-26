package com.example.hanzicounter.viewmodels

import androidx.lifecycle.ViewModel
import com.example.hanzicounter.domain.TextRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TextReadModeViewModel @Inject constructor(
    private val repository: TextRepository
): ViewModel() {

    // get/observe text

    // List of chars should contain unique and valid chars only
    //   fun that removes duplicate chars from a string
    //   fun that filters unwanted chars from a string

    // given a string and list of chars, for each char in the list,
// count how many times does it appear in the string
// and return a list of chars and it's counters in the format of List<Char, Int>()
}