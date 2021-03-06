package com.harismexis.hiltproject.tests

import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.harismexis.hiltproject.R
import com.harismexis.hiltproject.base.BaseInstrumentedTest
import com.harismexis.hiltproject.core.domain.Hero
import com.harismexis.hiltproject.core.result.HerosResult
import com.harismexis.hiltproject.presentation.screens.home.ui.activity.MainActivity
import com.harismexis.hiltproject.presentation.screens.home.viewmodel.HomeViewModel
import com.harismexis.hiltproject.reader.MockHerosProvider.Companion.EXPECTED_NUM_HEROS_WHEN_3_IDS_INVALID
import com.harismexis.hiltproject.reader.MockHerosProvider.Companion.EXPECTED_NUM_HEROS_WHEN_6_IDS_INVALID
import com.harismexis.hiltproject.reader.MockHerosProvider.Companion.EXPECTED_NUM_HEROS_WHEN_ALL_IDS_VALID
import com.harismexis.hiltproject.reader.MockHerosProvider.Companion.EXPECTED_NUM_HEROS_WHEN_NO_DATA
import com.harismexis.hiltproject.reader.MockHerosProvider.Companion.EXPECTED_NUM_HEROS_WHEN_SOME_EMPTY
import com.harismexis.hiltproject.util.RecyclerCountAssertion
import com.harismexis.hiltproject.util.verifyRecyclerItemAt
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest: BaseInstrumentedTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    var mockViewModel : HomeViewModel = mockk(relaxed = true)
    private var mockHerosResult = MutableLiveData<HerosResult>()
    private lateinit var mockHeros: List<Hero>
    private lateinit var herosSuccess: HerosResult.Success

    @Test
    fun herosFeedHasAllIdsInvalid_listHasExpectedItems() {
        // given
        mockInitialResults(herosParser.getMockHerosWhenJsonHasAllItemsValid())
        // when
        val scenario = launchActivity<MainActivity>()
        // then
        verifyRecycler(EXPECTED_NUM_HEROS_WHEN_ALL_IDS_VALID)
    }

    @Test
    fun herosFeedHas3InvalidIds_listHasExpectedItems() {
        // given
        mockInitialResults(herosParser.getMockHerosWhenJsonHas3InvalidIds())
        // when
        val scenario = launchActivity<MainActivity>()
        // then
        verifyRecycler(EXPECTED_NUM_HEROS_WHEN_3_IDS_INVALID)
    }

    @Test
    fun herosFeedHas6InvalidIds_listHasExpectedItems() {
        // given
        mockInitialResults(herosParser.getMockHerosWhenJsonHas6InvalidIds())
        // when
        val scenario = launchActivity<MainActivity>()
        // then
        verifyRecycler(EXPECTED_NUM_HEROS_WHEN_6_IDS_INVALID)
    }

    @Test
    fun herosFeedHasSomeEmptyHeroJsonItems_listHasExpectedItems() {
        // given
        mockInitialResults(herosParser.getMockHerosWhenJsonHasSomeEmptyItems())
        // when
        val scenario = launchActivity<MainActivity>()
        // then
        verifyRecycler(EXPECTED_NUM_HEROS_WHEN_SOME_EMPTY)
    }

    @Test
    fun herosFeedHasAllIdsInvalid_listHasNoItems() {
        // given
        mockInitialResults(herosParser.getMockHerosWhenJsonHasAllIdsInvalid())
        // when
        val scenario = launchActivity<MainActivity>()
        // then
        verifyRecycler(EXPECTED_NUM_HEROS_WHEN_NO_DATA)
    }

    @Test
    fun herosFeedIsEmptyJson_listHasNoItems() {
        // given
        mockInitialResults(herosParser.getMockHerosWhenJsonIsEmpty())
        // when
        val scenario = launchActivity<MainActivity>()
        // then
        verifyRecycler(EXPECTED_NUM_HEROS_WHEN_NO_DATA)
    }
    
    private fun mockInitialResults(mockData: List<Hero>) {
        mockHeros = mockData
        herosSuccess = HerosResult.Success(mockHeros)
        every { mockViewModel.fetchHeros() } answers {
            mockHerosResult.value = herosSuccess
        }
        every { mockViewModel.herosResult } returns mockHerosResult
    }

    private fun verifyRecycler(expectedNumberOfItems: Int) {
        Espresso.onView(withId(R.id.home_list))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        verifyRecyclerCount(expectedNumberOfItems)
        verifyRecyclerData()
    }

    private fun verifyRecyclerCount(expectedNumberOfItems: Int) {
        Assert.assertEquals(herosSuccess.items.size, expectedNumberOfItems)
        Espresso.onView(withId(R.id.home_list)).check(RecyclerCountAssertion(expectedNumberOfItems))
    }

    private fun verifyRecyclerData() {
        mockHeros.forEachIndexed { index, hero ->
            Espresso.onView(withId(R.id.home_list))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(index))
            verifyRecyclerValue(index, R.id.txt_name, hero.name)
            verifyRecyclerValue(index, R.id.txt_meta, hero.species)
        }
    }

    private fun verifyRecyclerValue(
        index: Int,
        @IdRes viewId: Int,
        value: String?
    ) {
        verifyRecyclerItemAt(R.id.home_list, index, viewId, value)
    }

}