package com.takechee.qrcodereader.misc.ui

import android.content.Context
import com.jraska.livedata.test
import com.takechee.qrcodereader.corecomponent.data.prefs.PreferenceStorage
import com.takechee.qrcodereader.widget.component.MockkRule
import com.takechee.qrcodereader.widget.component.ViewModelTestRule
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MiscViewModelTest {

    // =============================================================================================
    //
    // Rule
    //
    // =============================================================================================
    @get:Rule
    val viewModelTestRule: TestRule = ViewModelTestRule()

    @get:Rule
    val mockkRule = MockkRule(this)


    // =============================================================================================
    //
    // Mock
    //
    // =============================================================================================
    @MockK(relaxed = true)
    lateinit var context: Context

    @MockK(relaxed = true)
    lateinit var prefs: PreferenceStorage


    // =============================================================================================
    //
    // Test
    //
    // =============================================================================================
    @Test
    fun initUiModel_UseBrowserApp_True() {
        coEvery { prefs.useBrowserAppFlow } returns flowOf(true)
        val viewModel = MiscViewModel(
            context = context,
            prefs = prefs
        )

        val testObserver = viewModel
            .uiModel
            .test()

        val valueHistory = testObserver.valueHistory()
        valueHistory[0] shouldBe MiscUiModel.EMPTY.copy(useBrowserApp = true)
    }

    @Test
    fun initUiModel_UseBrowserApp_False() {
        coEvery { prefs.useBrowserAppFlow } returns flowOf(false)
        val viewModel = MiscViewModel(
            context = context,
            prefs = prefs
        )

        val testObserver = viewModel
            .uiModel
            .test()

        val valueHistory = testObserver.valueHistory()
        valueHistory[0] shouldBe MiscUiModel.EMPTY
    }

}