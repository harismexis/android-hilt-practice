package com.harismexis.hiltproject.tests

import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.harismexis.hiltproject.R
import com.harismexis.hiltproject.datamodel.domain.Hero
import com.harismexis.hiltproject.parser.MockHerosParser.Companion.EXPECTED_NUM_HEROS_WHEN_ALL_IDS_VALID
import com.harismexis.hiltproject.presentation.result.HerosResult
import com.harismexis.hiltproject.presentation.screens.home.ui.activity.MainActivity
import com.harismexis.hiltproject.setup.base.InstrumentedTestSetup
import com.harismexis.hiltproject.setup.testutil.MockHomeVmProvider
import com.harismexis.hiltproject.setup.testutil.RecyclerCountAssertion
import com.harismexis.hiltproject.setup.testutil.verifyRecyclerItemAt
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class HomeScreenTest: InstrumentedTestSetup() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @BindValue
    @JvmField
    var mockViewModel = MockHomeVmProvider.mockHomeViewModel
    private lateinit var mockHeros: List<Hero>
    private lateinit var herosSuccess: HerosResult.Success

    @Test
    fun actorsFeedHasAllIdsInvalid_listHasNoItems() {
        // given
        mockInitialResults(herosParser.getMockHerosWhenJsonHasAllItemsValid())

        // when
        val scenario = launchActivity<MainActivity>()

        // then
        verifyRecycler(EXPECTED_NUM_HEROS_WHEN_ALL_IDS_VALID)
    }

    private fun mockInitialResults(mockData: List<Hero>) {
        mockHeros = mockData
        herosSuccess = HerosResult.Success(mockHeros)
        every { mockViewModel.fetchInitialHeros() } answers {
            MockHomeVmProvider.fakeHerosResult.value = herosSuccess
        }
        every { mockViewModel.herosResult } returns MockHomeVmProvider.fakeHerosResult
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
        mockHeros.forEachIndexed { index, actor ->
            Espresso.onView(withId(R.id.home_list))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(index))
            verifyRecyclerValue(index, R.id.txt_name, actor.name)
            verifyRecyclerValue(index, R.id.txt_meta, actor.species)
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