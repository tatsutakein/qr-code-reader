package com.takechee.qrcodereader.widget.component

import io.mockk.MockKAnnotations
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class MockkRule(private val target: Any) : TestWatcher() {
    override fun starting(description: Description?) {
        super.starting(description)
        MockKAnnotations.init(target, relaxUnitFun = true)
    }
}
