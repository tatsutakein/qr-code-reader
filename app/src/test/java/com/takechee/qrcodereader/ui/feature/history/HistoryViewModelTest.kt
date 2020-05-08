package com.takechee.qrcodereader.ui.feature.history

import com.jraska.livedata.test
import com.takechee.qrcodereader.data.repository.ContentRepository
import com.takechee.qrcodereader.ui.DefaultNavigateHelper
import com.takechee.qrcodereader.util.DUMMY_CONTENT_ID1
import com.takechee.qrcodereader.util.DUMMY_CONTENT_ID2
import com.takechee.qrcodereader.util.createDummyContent
import com.takechee.qrcodereader.util.createDummyContents
import com.takechee.qrcodereader.widget.component.MockkRule
import com.takechee.qrcodereader.widget.component.ViewModelTestRule
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class HistoryViewModelTest {

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
    lateinit var contentRepository: ContentRepository

    @MockK(relaxed = true)
    lateinit var navigator: HistoryNavigator


    // =============================================================================================
    //
    // Test
    //
    // =============================================================================================
    @Test
    fun initLoadContents() {
        val dummyContents = createDummyContents()
        coEvery { contentRepository.getContentsAllFlow() } returns flowOf(dummyContents)
        val viewModel = HistoryViewModel(
            navigator = navigator,
            repository = contentRepository
        )

        val testObserver = viewModel
            .contents
            .test()

        val valueHistory = testObserver.valueHistory()
        valueHistory[0] shouldBe dummyContents
    }

    @Test
    fun favoriteFilterEnabledChange() {
        val viewModel = HistoryViewModel(
            navigator = navigator,
            repository = contentRepository
        )

        val testObserver = viewModel
            .favoriteFilterEnabled
            .test()

        viewModel.onFilterEnableChangeClick()

        val valueHistory = testObserver.valueHistory()
        valueHistory[0] shouldBe false
        valueHistory[1] shouldBe true
    }

    @Test
    fun favoriteFilterEnabledChange_FavoriteContents_Empty() {
        val dummyContents = createDummyContents(
            createDummyContent(contentId = DUMMY_CONTENT_ID1, isFavorite = false),
            createDummyContent(contentId = DUMMY_CONTENT_ID2, isFavorite = false)
        )
        coEvery { contentRepository.getContentsAllFlow() } returns flowOf(dummyContents)
        val viewModel = HistoryViewModel(
            navigator = navigator,
            repository = contentRepository
        )

        val testObserver = viewModel
            .contents
            .test()

        viewModel.onFilterEnableChangeClick()

        val valueHistory = testObserver.valueHistory()
        valueHistory[0] shouldBe dummyContents
        valueHistory[1].also { result ->
            result.isEmpty() shouldBe true
        }
    }

    @Test
    fun favoriteFilterEnabledChange_FavoriteContents_Exist() {
        val favoriteContent = createDummyContent(contentId = DUMMY_CONTENT_ID1, isFavorite = true)
        val dummyContents = createDummyContents(
            favoriteContent,
            createDummyContent(contentId = DUMMY_CONTENT_ID2, isFavorite = false)
        )
        coEvery { contentRepository.getContentsAllFlow() } returns flowOf(dummyContents)
        val viewModel = HistoryViewModel(
            navigator = navigator,
            repository = contentRepository
        )

        val testObserver = viewModel
            .contents
            .test()

        viewModel.onFilterEnableChangeClick()

        val valueHistory = testObserver.valueHistory()
        valueHistory[0] shouldBe dummyContents
        valueHistory[1] shouldBe listOf(favoriteContent)
    }

    @Test
    fun searchClick_NavigateToSearch() {
        val viewModel = HistoryViewModel(
            navigator = HistoryNavigateHelper(
                navigateHelper = DefaultNavigateHelper()
            ),
            repository = contentRepository
        )

        val testObserver = viewModel
            .navDirections
            .test()

        viewModel.onSearchClick()

        val valueHistory = testObserver.valueHistory()
        valueHistory[0].peekContent() shouldBe HistoryFragmentDirections.toSearch()
    }

    @Test
    fun historyItemClick_NavigateToDetail() {
        val content = createDummyContent(DUMMY_CONTENT_ID1)
        val dummyContents = createDummyContents(content)
        coEvery { contentRepository.getContentsAllFlow() } returns flowOf(dummyContents)
        val viewModel = HistoryViewModel(
            navigator = HistoryNavigateHelper(
                navigateHelper = DefaultNavigateHelper()
            ),
            repository = contentRepository
        )

        val testObserver = viewModel
            .navDirections
            .test()

        viewModel.onHistoryItemClick(content)

        val valueHistory = testObserver.valueHistory()
        valueHistory[0].peekContent() shouldBe HistoryFragmentDirections.toDetail(content.id)
    }
}