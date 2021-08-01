package com.harismexis.hiltproject.setup.base

import com.harismexis.hiltproject.parser.MockHerosProvider
import com.harismexis.hiltproject.setup.testutil.InstrumentedFileReader

open class BaseInstrumentedTest {
    protected val fileParser = InstrumentedFileReader()
    protected val herosParser = MockHerosProvider(fileParser)
}